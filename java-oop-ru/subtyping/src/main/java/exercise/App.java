package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> storageMap = new HashMap<>(storage.toMap());

        for (String key : storageMap.keySet()) {
            storage.unset(key);
        }

        for (Map.Entry<String, String> entry : storageMap.entrySet()) {
            storage.set(entry.getValue(), entry.getKey());
        }
    }
}
// END
