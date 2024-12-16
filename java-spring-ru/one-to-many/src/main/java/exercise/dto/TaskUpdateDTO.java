package exercise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TaskUpdateDTO {
    @NotNull
    private Long assigneeId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;
}
