package exercise.dto;

// BEGIN
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GuestCreateDTO {
    @NotBlank(message = "Имя не должно быть пустым")
    private String name;

    @Email(message = "Некорректный адрес электронной почты")
    private String email;

    @Pattern(
            regexp = "\\+\\d{11,13}",
            message = "Номер телефона должен начинаться с символа + и содержать от 11 до 13 цифр"
    )
    private String phoneNumber;

    @Pattern(
            regexp = "\\d{4}",
            message = "Номер клубной карты должен состоять ровно из четырех цифр"
    )
    private String clubCard;

    @Future(message = "Срок действия карты истек")
    private LocalDate cardValidUntil;
}
// END
