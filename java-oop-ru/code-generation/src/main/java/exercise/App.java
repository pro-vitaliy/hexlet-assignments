package exercise;

import lombok.SneakyThrows;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    @SneakyThrows
    public static void save(Path filePath, Car car) {
        String jsonCar = car.serialize();
        Files.writeString(filePath, jsonCar, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

    @SneakyThrows
    public static Car extract(Path filePath) {
        String jsonCar = Files.readString(filePath);
        return Car.deserialize(jsonCar);
    }
}
// END
