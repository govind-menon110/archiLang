package se.kth.archiLang.malInterface;

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
