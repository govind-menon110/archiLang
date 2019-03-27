package se.kth.archiLang.malInterface.metaElements;

public interface Relation {
    String getSinkCardinality();

    String getSourceCarindality();

    String getName();

    String getSink();

    String getSource();

    String getSourceLabel();

    String getSinkLabel();
}
