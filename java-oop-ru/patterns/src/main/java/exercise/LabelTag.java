package exercise;

// BEGIN
class LabelTag implements TagInterface {
    private final TagInterface tag;
    private final String label;

    LabelTag(String label, TagInterface tag) {
        this.tag = tag;
        this.label = label;
    }

    @Override
    public String render() {
        return "<label>" + label + tag.render() + "</label>";
    }
}
// END
