package se.kth.archiLang.malInterface.instanceElements;

import java.util.List;

public interface Object {
    String getName();

    Class getClassType();

    List<Object> getLinkedObjects();
}
