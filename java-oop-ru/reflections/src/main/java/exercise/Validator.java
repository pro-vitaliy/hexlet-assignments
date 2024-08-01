package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
class Validator {
    public static List<String> validate(Address address) {
        Field[] fields = address.getClass().getDeclaredFields();
        List<String> result = new ArrayList<>();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                if (f.getAnnotation(NotNull.class) != null && f.get(address) == null) {
                    result.add(f.getName());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        var res = new HashMap<String, List<String>>();
        Field[] fields = address.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                List<String> errorsList = new ArrayList<>();
                if (f.getAnnotation(NotNull.class) != null && f.get(address) == null) {
                    errorsList.add("can not be null");
                    res.put(f.getName(), errorsList);
                    continue;
                }
                if (f.getAnnotation(MinLength.class) != null
                        && f.get(address).toString().length() < f.getAnnotation(MinLength.class).minLength()) {
                    errorsList.add("length less than " + f.getAnnotation(MinLength.class).minLength());
                }
                if (!errorsList.isEmpty()) {
                    res.put(f.getName(), errorsList);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }
}
// END
