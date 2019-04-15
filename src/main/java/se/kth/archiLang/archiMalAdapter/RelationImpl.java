package se.kth.archiLang.archiMalAdapter;

import org.apache.commons.text.WordUtils;

public class RelationImpl implements se.kth.archiLang.malInterface.metaElements.Relation {
    private String sink;
    private String source;

    private String sinkCardinality;
    private String sourceCarindality;

    public RelationImpl(String sink, String source, String sinkCardinality, String sourceCarindality) {
        this.sink = sink;
        this.source = source;
        this.sinkCardinality = sinkCardinality;
        this.sourceCarindality = sourceCarindality;
    }

    public RelationImpl(String source, String sink) {
        this.sink = sink;
        this.source = source;
    }

    @Override
    public String getSinkCardinality() {
        return sinkCardinality;
    }

    public void setSinkCardinality(String sinkCardinality) {
        this.sinkCardinality = sinkCardinality;
    }

    @Override
    public String getSourceCarindality() {
        return sourceCarindality;
    }

    public void setSourceCarindality(String sourceCarindality) {
        this.sourceCarindality = sourceCarindality;
    }

    @Override
    public String getSink() {
        return sink;
    }

    public void setSink(String sink) {
        this.sink = sink;
    }

    @Override
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String getSourceLabel() {
        return WordUtils.uncapitalize(source + sink);
    }

    @Override
    public String getSinkLabel() {
        return WordUtils.uncapitalize(sink + source);
    }

    @Override
    public String getName() {
        return source + sink;
    }
}
