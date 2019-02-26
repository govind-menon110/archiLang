package se.kth.archiLang.archiMalAdapter;

import se.kth.archiLang.generated.archimate3.ElementType;
import se.kth.archiLang.generated.archimate3.RelationshipTypeEnum;
import se.kth.archiLang.malInterface.metaElements.Attack;
import se.kth.archiLang.malInterface.metaElements.Class;
import se.kth.archiLang.malInterface.metaElements.Defense;

import java.util.Iterator;
import java.util.List;

public class ClassAdapter implements Class {
    private ElementType archiElement;
    private RelationContainer relationContainer;
    private ElementContainer elementContainer;
    private PropertyExtractor propertyExtractor;
    private String identifier;

    public ClassAdapter(ElementType archiElement, RelationContainer relationContainer, ElementContainer elementContainer, PropertyDefContainer propertyDefContainer) {
        this.archiElement = archiElement;
        this.relationContainer = relationContainer;
        this.elementContainer = elementContainer;
        identifier = archiElement.getIdentifier();
        this.propertyExtractor = new PropertyExtractor(propertyDefContainer);
    }

    public String getName() {
        return archiElement.getNameGroup().get(0).getValue();
    }

    public List<Class> getExtends() {
        List<String> extendedElementsIdentifier = relationContainer.getOtherElements(RelationshipTypeEnum.SPECIALIZATION, identifier);
        return elementContainer.transformToClass(elementContainer.getConcreteElements(extendedElementsIdentifier));
    }


    public List<Attack> getAttacks() {
        List<String> extendedElementsIdentifier = relationContainer.getOtherElements(identifier);
        List<ElementType> elements = elementContainer.getConcreteElements(extendedElementsIdentifier);

        for (Iterator<ElementType> iterator = elements.iterator(); iterator.hasNext(); ) {
            ElementType element = iterator.next();
            propertyExtractor.get("Type", element).equals("Threat");
        }

        return elementContainer.transformToAttack(elements);
    }

    public List<Defense> getDefenses() {
        List<String> extendedElementsIdentifier = relationContainer.getOtherElements(identifier);
        List<ElementType> elements = elementContainer.getConcreteElements(extendedElementsIdentifier);

        for (Iterator<ElementType> iterator = elements.iterator(); iterator.hasNext(); ) {
            ElementType element = iterator.next();
            propertyExtractor.get("Type", element).equals("Defense");
        }

        return elementContainer.transformToDefense(elements);
    }
}
