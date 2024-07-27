package exercise;

import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage {
    private final String filePath;

    FileKV(String filePath, Map<String, String> data) {
        this.filePath = filePath;
        String content = Utils.serialize(data);
        Utils.writeFile(filePath, content);
    }

    @Override
    public void set(String key, String value) {
        String content = Utils.readFile(filePath);
        Map<String, String> contentMap = Utils.deserialize(content);
        contentMap.put(key, value);
        content = Utils.serialize(contentMap);
        Utils.writeFile(filePath, content);
    }

    @Override
    public void unset(String key) {
        String content = Utils.readFile(filePath);
        Map<String, String> contentMap = Utils.deserialize(content);
        contentMap.remove(key);
        content = Utils.serialize(contentMap);
        Utils.writeFile(filePath, content);
    }

    @Override
    public String get(String key, String defaultValue) {
        String content = Utils.readFile(filePath);
        Map<String, String> contentMap = Utils.deserialize(content);
        return contentMap.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        String content = Utils.readFile(filePath);
        return Utils.deserialize(content);
    }
}
// END
