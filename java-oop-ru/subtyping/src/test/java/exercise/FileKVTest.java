package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
// BEGIN
import static org.assertj.core.api.Assertions.assertThat;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // BEGIN
    @Test
    public void testFileKV() {
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("key", "value"));
        Map<String, String> expected1 = Map.of("key", "value");
        Map<String, String> actual1 = storage.toMap();
        assertThat(actual1).isEqualTo(expected1);

        storage.set("other key", "other value");
        Map<String, String> expected2 = Map.of("key", "value", "other key", "other value");
        Map<String, String> actual2 = storage.toMap();
        assertThat(actual2).isEqualTo(expected2);

        storage.unset("other key");
        Map<String, String> expected3 = Map.of("key", "value");
        Map<String, String> actual3 = storage.toMap();
        assertThat(actual3).isEqualTo(expected3);

        String actual4 = storage.get("key", "default");
        String expected4 = "value";
        assertThat(actual4).isEqualTo(expected4);
    }
    // END
}
