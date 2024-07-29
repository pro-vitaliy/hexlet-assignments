package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class PairedTag extends Tag {
    private String text;
    private List<Tag> childs;

    PairedTag(String tagName, Map<String, String> attributes, String text, List<Tag> childs) {
        super(tagName, attributes);
        this.text = text;
        this.childs = childs;
    }

    @Override
    public String toString() {
        var res = childs.stream()
                .map(Tag::toString)
                .collect(Collectors.joining(""));
        return super.toString() + res + text + "</%s>".formatted(super.getTagName());
    }
}
// END
