package se.kth.archiLang.malInterface.metaElements;

public enum RelationEnum {
    AND {
        @Override
        public String toString() {
            return "&";
        }
    },
    OR {
        @Override
        public String toString() {
            return "|";
        }
    };
}
