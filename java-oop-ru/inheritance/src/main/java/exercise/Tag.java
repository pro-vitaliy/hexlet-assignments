package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
class Tag {
    private final String tagName;
    private final Map<String, String> attributes;

    Tag(String tagName, Map<String, String> attributes) {
        this.tagName = tagName;
        this.attributes = attributes;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public String toString() {
        return attributes.entrySet()
                .stream()
                .map(e -> " %s=\"%s\"".formatted(e.getKey(), e.getValue()))
                .collect(Collectors.joining("", "<" + tagName, ">"));

    }
}
// END
