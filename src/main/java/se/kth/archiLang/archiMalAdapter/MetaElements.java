package se.kth.archiLang.archiMalAdapter;

import org.apache.commons.text.WordUtils;
import se.kth.archiLang.generated.archimate3.*;
import se.kth.archiLang.malInterface.metaElements.Attack;
import se.kth.archiLang.malInterface.metaElements.Class;
import se.kth.archiLang.malInterface.metaElements.Defense;
import se.kth.archiLang.malInterface.metaElements.Relation;

import java.util.LinkedList;
import java.util.List;

public class MetaElements {
    private List<Class> classes = new LinkedList<>();
    private ElementContainer elementContainer;
    private List<Relation> relations = new LinkedList<>();

    public MetaElements(ModelType exchangeModel) {
        elementContainer = new ElementContainer(exchangeModel.getRelationships().getRelationship(),
                exchangeModel.getElements().getElement());
        PropertyDefContainer container = new PropertyDefContainer(exchangeModel.getPropertyDefinitions());
        PropertyExtractor extractor = new PropertyExtractor(container);

        getRelations(exchangeModel, extractor);

        getClasses(exchangeModel, extractor);
    }

    private void getRelations(ModelType exchangeModel, PropertyExtractor extractor) {
        for (RelationshipType relationshipType : exchangeModel.getRelationships().getRelationship()) {
            String sourceIdentifier = ((ReferenceableType) relationshipType.getSource()).getIdentifier();
            String source = format(elementContainer.getNameOfElement(sourceIdentifier));
            ElementType sourceElement = elementContainer.get(sourceIdentifier);
            String sinkIdentifier = ((ReferenceableType) relationshipType.getTarget()).getIdentifier();
            String sink = format(elementContainer.getNameOfElement(sinkIdentifier));
            ElementType sinkElement = elementContainer.get(sinkIdentifier);
            String sourceCardinality = "*";
            String sinkCardinality = "*";
            Boolean set = false;

            if (extractor.get("Modelelementtype", sourceElement).equals("Meta") &&
                    extractor.get("Modelelementtype", sinkElement).equals("Meta")) {
                if (relationshipType.getClass().getSimpleName().equals(RelationshipTypeEnum.AGGREGATION.value()) ||
                        relationshipType.getClass().getSimpleName().equals(RelationshipTypeEnum.COMPOSITION.value())) {
                    set = true;

                    sourceCardinality = "1";
                } else if (relationshipType.getClass().getSimpleName().equals(RelationshipTypeEnum.TRIGGERING.value()) ||
                        relationshipType.getClass().getSimpleName().equals(RelationshipTypeEnum.SERVING.value())) {
                    set = true;
                } else if (relationshipType.getClass().getSimpleName().equals(RelationshipTypeEnum.ASSOCIATION.value())) {
                    if (!sourceElement.getClass().getSimpleName().equals(ElementTypeEnum.ASSESSMENT.value()) &&
                            !sinkElement.getClass().getSimpleName().equals(ElementTypeEnum.ASSESSMENT.value())) {
                        set = true;
                    }
                }
                if (set) {
                    relations.add(new RelationImpl(sink, source, sinkCardinality, sourceCardinality));
                }
            }
        }
    }

    private void getClasses(ModelType exchangeModel, PropertyExtractor extractor) {
        for (ElementType elementType : exchangeModel.getElements().getElement()) {
            if (extractor.get("Modelelementtype", elementType).equals("Meta")) {
                List<String> extended = new LinkedList<>();
                List<Attack> attacks = new LinkedList<>();
                List<Defense> defense = new LinkedList<>();
                String name = format(elementType.getNameGroup().get(0).getValue());

                getExtends(elementType, extended);
                getAttacks(elementType, attacks);

                ClassImpl classImpl = new ClassImpl(extended, attacks, defense, name);

                classes.add(classImpl);
            }
        }
    }

    private void getAttacks(ElementType elementType, List<Attack> attacks) {
        for (RelationImpl relation : elementContainer.getRelation(
                elementType.getIdentifier(),
                RelationshipTypeEnum.ASSOCIATION,
                true, false)) {
            addAttack(attacks, relation);
        }
        for (RelationImpl relation : elementContainer.getRelation(
                elementType.getIdentifier(),
                RelationshipTypeEnum.ASSOCIATION,
                false, false)) {
            addAttack(attacks, relation);
        }
    }

    private void addAttack(List<Attack> attacks, RelationImpl relation) {
        ElementType element = elementContainer.get(relation.getSink());
        if (element != null && element.getClass().equals(Assessment.class)) {
            attacks.add(new AttackImpl(relation.getSink(), elementContainer, relations));
        }
    }

    public static String format(String name) {
        String capitalized = WordUtils.capitalize(name);
        String justLetters = capitalized.replaceAll("\\W", "");
        return justLetters;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    private void getExtends(ElementType elementType, List<String> extended) {
        for (RelationImpl relation : elementContainer.getRelation(
                elementType.getIdentifier(),
                RelationshipTypeEnum.SPECIALIZATION,
                true, true)) {
            extended.add(format(relation.getSink()));
        }
        if (extended.isEmpty()) {
            extended.add(elementType.getClass().getSimpleName());
        }
    }
}
