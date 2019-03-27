package se.kth.archiLang.archiMalAdapter;

import se.kth.archiLang.generated.archimate3.ElementType;
import se.kth.archiLang.generated.archimate3.ReferenceableType;
import se.kth.archiLang.generated.archimate3.RelationshipType;
import se.kth.archiLang.generated.archimate3.RelationshipTypeEnum;

import java.util.LinkedList;
import java.util.List;

public class ElementContainer {
    private List<ElementType> elements;
    private List<RelationshipType> relations;

    public ElementContainer(List<RelationshipType> relations, List<ElementType> elements) {
        this.relations = relations;
        this.elements = elements;
    }

    public List<RelationImpl> getRelation(String identifier, RelationshipTypeEnum typeEnum, Boolean source, Boolean getName) {
        List<RelationImpl> result = new LinkedList<>();

        for (RelationshipType relation : relations) {
            if (typeEnum.value().equals(relation.getClass().getSimpleName())) {
                if ((source && ((ReferenceableType) relation.getSource()).getIdentifier().equals(identifier)) ||
                        (!source && ((ReferenceableType) relation.getTarget()).getIdentifier().equals(identifier))) {
                    result.add(
                            getName ?
                                    new RelationImpl(
                                            getNameOfElement(((ReferenceableType) relation.getSource()).getIdentifier()),
                                            getNameOfElement(((ReferenceableType) relation.getTarget()).getIdentifier())
                                    ) :
                                    new RelationImpl(
                                            ((ReferenceableType) relation.getSource()).getIdentifier(),
                                            ((ReferenceableType) relation.getTarget()).getIdentifier()
                                    )
                    );
                }
            }
        }

        return result;
    }

    public String getNameOfElement(String identifier) {
        for (ElementType element : elements) {
            if (element.getIdentifier().equals(identifier))
                return element.getNameGroup().get(0).getValue();
        }

        return "";
    }

    public ElementType get(String identifier) {
        for (ElementType element : elements) {
            if (element.getIdentifier().equals(identifier)) {
                return element;
            }
        }
        return null;
    }
}
