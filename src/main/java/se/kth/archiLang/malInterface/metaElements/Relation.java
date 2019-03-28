package se.kth.archiLang.malInterface.metaElements;

public interface Relation extends MetaElement {
    String getSinkCardinality();

    String getSourceCarindality();

    String getSink();

    String getSource();

    String getSourceLabel();

    String getSinkLabel();
}
