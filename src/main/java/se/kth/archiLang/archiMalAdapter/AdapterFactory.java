package se.kth.archiLang.archiMalAdapter;

import se.kth.archiLang.generated.archimate3.ElementType;
import se.kth.archiLang.malInterface.metaElements.Attack;
import se.kth.archiLang.malInterface.metaElements.Defense;

public class AdapterFactory {
    private static RelationContainer relationContainer;
    private static ElementContainer elementContainer;
    private static PropertyDefContainer propertyDefContainer;

    public static ClassAdapter createNewClass(ElementType elementType) {
        return new ClassAdapter(elementType, relationContainer, elementContainer, propertyDefContainer);
    }

    public static Attack createNewAttack(ElementType elementType) {
        return null;
    }

    public static Defense createNewDefense(ElementType elementType) {
        return null;
    }
}
