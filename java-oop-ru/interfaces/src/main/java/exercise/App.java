package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int n) {
        return apartments.stream()
                .sorted()
                .limit(n)
                .map(Home::toString)
                .collect(Collectors.toList());
    }
}
// END
