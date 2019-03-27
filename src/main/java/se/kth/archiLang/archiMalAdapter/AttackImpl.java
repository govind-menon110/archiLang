package se.kth.archiLang.archiMalAdapter;

import se.kth.archiLang.generated.archimate3.AndJunction;
import se.kth.archiLang.generated.archimate3.Assessment;
import se.kth.archiLang.generated.archimate3.ElementType;
import se.kth.archiLang.generated.archimate3.RelationshipTypeEnum;
import se.kth.archiLang.malInterface.metaElements.Attack;
import se.kth.archiLang.malInterface.metaElements.RelationEnum;

import java.util.LinkedList;
import java.util.List;

public class AttackImpl implements Attack {
    private List<String> attacks = new LinkedList<>();
    private String relatedClass;
    private RelationEnum relation;
    private String name;
    private ElementContainer elementContainer;

    public AttackImpl(String identifier, ElementContainer elementContainer) {
        name = MetaElements.format(elementContainer.get(identifier).getNameGroup().get(0).getValue());
        this.elementContainer = elementContainer;
        setAttacks(identifier, elementContainer);
        setRelatedClass(identifier, elementContainer);
        setRelation(identifier, elementContainer);
    }

    private void setAttacks(String identifier, ElementContainer elementContainer) {
        ElementType elementType = elementContainer.get(identifier);
        for (Relation relation : elementContainer.getRelation(
                elementType.getIdentifier(),
                RelationshipTypeEnum.INFLUENCE,
                true, false)) {
            if (elementContainer.get(relation.getSink()).getClass().equals(Assessment.class)) {
                attacks.add(relation.getSink());
            } else if (elementContainer.get(relation.getSink()).getClass().equals(AndJunction.class)) { //Follow Junctions
                for (Relation relation2 : elementContainer.getRelation(
                        relation.getSink(),
                        RelationshipTypeEnum.INFLUENCE,
                        true, false)) {
                    if (elementContainer.get(relation2.getSink()).getClass().equals(Assessment.class)) {
                        attacks.add(relation2.getSink());
                    }
                }
            }
        }
    }

    private void setRelatedClass(String identifier, ElementContainer elementContainer) {
        ElementType elementType = elementContainer.get(identifier);
        for (Relation relation : elementContainer.getRelation(
                elementType.getIdentifier(),
                RelationshipTypeEnum.ASSOCIATION,
                false, false)) {
            if (!elementContainer.get(relation.getSource()).getClass().equals(Assessment.class)) {
                relatedClass = MetaElements.format(elementContainer.getNameOfElement(relation.getSource()));
            }
        }
    }

    private void setRelation(String identifier, ElementContainer elementContainer) {
        ElementType elementType = elementContainer.get(identifier);
        Boolean junction = false;
        for (Relation relation : elementContainer.getRelation(
                elementType.getIdentifier(),
                RelationshipTypeEnum.INFLUENCE,
                false, false)) {
            if (elementContainer.get(relation.getSource()).getClass().equals(AndJunction.class)) {
                junction = true;
            }
        }

        relation = junction ? RelationEnum.AND : RelationEnum.OR;
    }

    @Override
    public List<Attack> getFollowUpAttack() {
        List<Attack> attacks = new LinkedList<>();

        for (String identifier : this.attacks) {
            attacks.add(new AttackImpl(identifier, elementContainer));
        }

        return attacks;
    }

    @Override
    public String getRelatedClass() {
        return relatedClass;
    }

    @Override
    public RelationEnum getRelation() {
        return relation;
    }

    @Override
    public String getName() {
        return name;
    }
}
