package se.kth.archiLang.archiMalAdapter;

import org.apache.commons.text.WordUtils;
import se.kth.archiLang.generated.archimate3.AndJunction;
import se.kth.archiLang.generated.archimate3.Assessment;
import se.kth.archiLang.generated.archimate3.ElementType;
import se.kth.archiLang.generated.archimate3.RelationshipTypeEnum;
import se.kth.archiLang.malInterface.metaElements.Attack;
import se.kth.archiLang.malInterface.metaElements.Relation;
import se.kth.archiLang.malInterface.metaElements.RelationEnum;

import java.util.LinkedList;
import java.util.List;

public class AttackImpl implements Attack {
    private List<String> attacks = new LinkedList<>();
    private String relatedClass;
    private RelationEnum relation;
    private String name;
    private ElementContainer elementContainer;
    private String relatedRelation;
    private List<Relation> relations;

    public AttackImpl(String identifier, ElementContainer elementContainer, List<Relation> relations) {
        name = MetaElements.format(elementContainer.get(identifier).getNameGroup().get(0).getValue());
        this.elementContainer = elementContainer;
        this.relations = relations;
        setAttacks(identifier, elementContainer);
        setRelatedClass(identifier, elementContainer);
        setRelation(identifier, elementContainer);
    }

    private void setRelatedClass(String identifier, ElementContainer elementContainer) {
        ElementType elementType = elementContainer.get(identifier);
        for (RelationImpl relation : elementContainer.getRelation(
                elementType.getIdentifier(),
                RelationshipTypeEnum.ASSOCIATION,
                false, false)) {
            if (!elementContainer.get(relation.getSource()).getClass().equals(Assessment.class)) {
                relatedClass = relation.getSource();
            }
        }
        for (RelationImpl relation : elementContainer.getRelation(
                elementType.getIdentifier(),
                RelationshipTypeEnum.ASSOCIATION,
                true, false)) {
            if (!elementContainer.get(relation.getSink()).getClass().equals(Assessment.class)) {
                relatedClass = relation.getSink();
            }
        }
    }

    private void setAttacks(String identifier, ElementContainer elementContainer) {
        ElementType elementType = elementContainer.get(identifier);
        for (RelationImpl relation : elementContainer.getRelation(
                elementType.getIdentifier(),
                RelationshipTypeEnum.INFLUENCE,
                true, false)) {
            addAttack(elementContainer, relation);
        }
    }

    private void addAttack(ElementContainer elementContainer, RelationImpl relation) {
        if (elementContainer.get(relation.getSink()).getClass().equals(Assessment.class)) {
            attacks.add(relation.getSink());
        } else if (elementContainer.get(relation.getSink()).getClass().equals(AndJunction.class)) { //Follow Junctions
            for (RelationImpl relation2 : elementContainer.getRelation(
                    relation.getSink(),
                    RelationshipTypeEnum.INFLUENCE,
                    true, false)) {
                if (elementContainer.get(relation2.getSink()).getClass().equals(Assessment.class)) {
                    attacks.add(relation2.getSink());
                }
            }
        }
    }

    @Override
    public List<Attack> getFollowUpAttack() {
        List<Attack> attacks = new LinkedList<>();

        for (String identifier : this.attacks) {
            AttackImpl attack = new AttackImpl(identifier, elementContainer, relations);

            String source = this.relatedClass;
            String sink = attack.getRelatedClass();

            if (attack.getName().equals("StopElectricityProduction")) {
                System.out.println();
            }

            String sourceName = MetaElements.format(elementContainer.getNameOfElement(source));
            String sinkName = MetaElements.format(elementContainer.getNameOfElement(sink));

            Relation rel = new RelationImpl(sinkName, sourceName, "*", "*");

            String targetRelatedLabel = rel.getSinkLabel();

            if (!elementContainer.relationExists(source, sink)) {
                relations.add(rel);
            }

            attack.setRelatedRelation(targetRelatedLabel);

            attacks.add(attack);
        }

        return attacks;
    }

    private void setRelation(String identifier, ElementContainer elementContainer) {
        ElementType elementType = elementContainer.get(identifier);
        Boolean junction = false;
        for (RelationImpl relation : elementContainer.getRelation(
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
    public String getRelatedLabel() {
        return relatedRelation;
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
        return WordUtils.uncapitalize(name);
    }

    public void setRelatedRelation(String relatedRelation) {
        this.relatedRelation = relatedRelation;
    }

    @Override
    public String getQualifiedName(Boolean extension) {
        StringBuilder qualifiedName = new StringBuilder();

        if (extension) {
            qualifiedName.append(WordUtils.uncapitalize(MetaElements.format(elementContainer.getNameOfElement(getRelatedClass()))) + ".");
        }

        qualifiedName.append(getName());

        return qualifiedName.toString();
    }
}
