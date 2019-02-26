package se.kth.archiLang.archiMalAdapter;

import se.kth.archiLang.generated.archimate3.RelationshipType;
import se.kth.archiLang.generated.archimate3.RelationshipTypeEnum;
import se.kth.archiLang.generated.archimate3.RelationshipsType;

import java.util.LinkedList;
import java.util.List;

public class RelationContainer {
    private RelationshipsType relationshipsType;

    public RelationContainer(RelationshipsType relationshipsType) {
        this.relationshipsType = relationshipsType;
    }

    public List<String> getOtherElements(RelationshipTypeEnum relationshipTypeEnum, String identifier) {
        List<String> otherElements = new LinkedList<>();

        for (RelationshipType relationshipType : relationshipsType.getRelationship()) {
            if (relationshipType.getSource().toString().equals(identifier) && isRightRelationType(relationshipType, relationshipTypeEnum)) {
                otherElements.add(relationshipType.getTarget().toString());
            }
        }

        return otherElements;
    }

    private boolean isRightRelationType(RelationshipType relationshipType, RelationshipTypeEnum relationshipTypeEnum) {
        return RelationshipTypeEnum.fromValue(relationshipType.getClass().toString()) == relationshipTypeEnum;
    }

    public List<String> getOtherElements(String identifier) {
        List<String> otherElements = new LinkedList<>();

        for (RelationshipType relationshipType : relationshipsType.getRelationship()) {
            if (relationshipType.getSource().toString().equals(identifier)) {
                otherElements.add(relationshipType.getTarget().toString());
            } else if (relationshipType.getTarget().toString().equals(identifier)) {
                otherElements.add(relationshipType.getSource().toString());
            }
        }

        return otherElements;
    }
}
