package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN

    private Task prepareTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .ignore(Select.field(Task::getUpdatedAt))
                .ignore(Select.field(Task::getCreatedAt))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence())
                .create();
    }

    @Test
    public void showTest() throws Exception {
        var task = prepareTask();
        task = taskRepository.save(task);

        var result = mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();
        var body = result.getResponse().getContentAsString();
        System.out.println(body);
        System.out.println(om.writeValueAsString(task));
        assertThatJson(body).isEqualTo(om.writeValueAsString(task));
    }

    @Test
    public void createTest() throws Exception {
        var data = new HashMap<String, String>();
        data.put("title", faker.lorem().word());
        data.put("description", faker.lorem().sentence());

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        var result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        var taskFromResponse = om.readValue(body, Task.class);
        var taskFromRepo = taskRepository.findById(taskFromResponse.getId()).get();
        assertThat(taskFromResponse.getTitle()).isEqualTo(taskFromRepo.getTitle());
        assertThat(taskFromResponse.getDescription()).isEqualTo(taskFromRepo.getDescription());
        assertThat(taskFromResponse.getCreatedAt()).isEqualTo(taskFromRepo.getCreatedAt());
    }

    @Test
    public void updateTest() throws Exception {
        var task = prepareTask();
        task = taskRepository.save(task);

        var data = new HashMap<String, String>();
        var newDescription = faker.lorem().sentence();
        data.put("description", newDescription);

        var request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        var result = mockMvc.perform(request)
                .andExpect(status().isOk());

        task = taskRepository.findById(task.getId()).get();
        assertThat(task.getDescription()).isEqualTo(newDescription);
    }

    @Test
    public void deleteTest() throws Exception {
        var task = prepareTask();
        task = taskRepository.save(task);

        mockMvc.perform(delete("/tasks/" + task.getId()));
        assertThat(taskRepository.findById(task.getId())).isEmpty();
    }
    // END
}
