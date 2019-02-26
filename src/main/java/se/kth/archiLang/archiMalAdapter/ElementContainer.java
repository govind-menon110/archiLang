package se.kth.archiLang.archiMalAdapter;

import se.kth.archiLang.generated.archimate3.ElementType;
import se.kth.archiLang.generated.archimate3.ElementsType;
import se.kth.archiLang.malInterface.metaElements.Attack;
import se.kth.archiLang.malInterface.metaElements.Class;
import se.kth.archiLang.malInterface.metaElements.Defense;

import java.util.LinkedList;
import java.util.List;

public class ElementContainer {
    private ElementsType elementsType;

    public ElementContainer(ElementsType elementsType) {
        this.elementsType = elementsType;
    }

    public ElementType getConcreteElement(String elementIdentifier) {
        for (ElementType elementType : elementsType.getElement()) {
            if (elementType.getIdentifier().equals(elementIdentifier))
                return elementType;
        }
        return null;
    }

    public List<ElementType> getConcreteElements(List<String> elementsIdentifier) {
        List<ElementType> elements = new LinkedList<ElementType>();

        for (String elementIdentifier : elementsIdentifier) {
            elements.add(this.<ElementType>getConcreteElement(elementIdentifier));
        }
        return elements;
    }

    public List<Class> transformToClass(List<ElementType> elementTypes) {
        List<Class> product = new LinkedList<Class>();

        for (ElementType elementType : elementTypes) {
            product.add(AdapterFactory.createNewClass(elementType));
        }

        return product;
    }

    public List<Attack> transformToAttack(List<ElementType> elementTypes) {
        List<Attack> product = new LinkedList<Attack>();

        for (ElementType elementType : elementTypes) {
            product.add(AdapterFactory.createNewAttack(elementType));
        }

        return product;
    }

    public List<Defense> transformToDefense(List<ElementType> elementTypes) {
        List<Defense> product = new LinkedList<Defense>();

        for (ElementType elementType : elementTypes) {
            product.add(AdapterFactory.createNewDefense(elementType));
        }

        return product;
    }
}
