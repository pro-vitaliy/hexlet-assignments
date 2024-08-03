package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
class Validator {
    public static List<String> validate(Object instance) {
        Field[] fields = instance.getClass().getDeclaredFields();
        return Arrays.stream(fields)
                .filter(f -> f.isAnnotationPresent(NotNull.class))
                .peek(f -> f.setAccessible(true))
                .filter(f -> {
                    try {
                        return f.get(instance) == null;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(Field::getName)
                .toList();
    }

    public static Map<String, List<String>> advancedValidate(Object instance) {
        Map<String, List<String>> result = new HashMap<>();
        Field[] fields = instance.getClass().getDeclaredFields();
        Arrays.stream(fields)
                .filter(f -> f.isAnnotationPresent(NotNull.class) || f.isAnnotationPresent(MinLength.class))
                .forEach(f -> {
                    try {
                        var errors = Validator.generateErrorsList(f, instance);
                        if (!errors.isEmpty()) {
                            result.put(f.getName(), errors);
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
        return result;
    }

    private static List<String> generateErrorsList(Field f, Object instance) throws IllegalAccessException {
        List<String> result = new ArrayList<>();
        f.setAccessible(true);
        if (f.isAnnotationPresent(NotNull.class) && f.get(instance) == null) {
            result.add("can not be null");
            return result;
        }
        if (f.isAnnotationPresent(MinLength.class)
                && f.get(instance).toString().length() < f.getAnnotation(MinLength.class).minLength()) {
            result.add("length less than " + f.getAnnotation(MinLength.class).minLength());
        }
        return result;
    }
}
// END
