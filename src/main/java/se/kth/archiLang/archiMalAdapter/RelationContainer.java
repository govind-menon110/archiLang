package se.kth.archiLang.archiMalAdapter;

import se.kth.archiLang.generated.archimate3.ElementType;
import se.kth.archiLang.generated.archimate3.RelationshipType;
import se.kth.archiLang.generated.archimate3.RelationshipTypeEnum;

import java.util.LinkedList;
import java.util.List;

public class RelationContainer {
    private List<RelationshipType> relations;
    private List<ElementType> elements;

    public RelationContainer(List<RelationshipType> relations, List<ElementType> elements) {
        this.relations = relations;
        this.elements = elements;
    }

    public List<Relation> getRelation(String identifier, RelationshipTypeEnum typeEnum, Boolean source) {
        List<Relation> result = new LinkedList<>();

        for (RelationshipType relation : relations) {
            if (typeEnum.value().equals(relation.getClass().getSimpleName())) {
                if ((source && relation.getSource().equals(identifier)) ||
                        (!source && relation.getTarget().equals(identifier))) {
                    result.add(
                            new Relation(
                                    getNameOfElement(relation.getSource().toString()),
                                    getNameOfElement(relation.getTarget().toString())
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
}
