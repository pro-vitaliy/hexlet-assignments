package exercise;

import lombok.Getter;
import lombok.Value;

// BEGIN
@Value
@Getter
// END
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
