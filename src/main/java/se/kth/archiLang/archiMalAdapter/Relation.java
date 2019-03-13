package se.kth.archiLang.archiMalAdapter;

public class Relation {
    private String sink;
    private String source;

    public Relation(String source, String sink) {
        this.sink = sink;
        this.source = source;
    }

    public String getSink() {
        return sink;
    }

    public void setSink(String sink) {
        this.sink = sink;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
