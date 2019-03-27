package se.kth.archiLang.archiMalAdapter;

import org.apache.commons.text.WordUtils;
import se.kth.archiLang.generated.archimate3.Assessment;
import se.kth.archiLang.generated.archimate3.ElementType;
import se.kth.archiLang.generated.archimate3.ModelType;
import se.kth.archiLang.generated.archimate3.RelationshipTypeEnum;
import se.kth.archiLang.malInterface.metaElements.Attack;
import se.kth.archiLang.malInterface.metaElements.Class;
import se.kth.archiLang.malInterface.metaElements.Defense;

import java.util.LinkedList;
import java.util.List;

public class MetaElements {
    private List<Class> classes = new LinkedList<>();
    private ElementContainer elementContainer;

    public MetaElements(ModelType exchangeModel) {
        elementContainer = new ElementContainer(exchangeModel.getRelationships().getRelationship(),
                exchangeModel.getElements().getElement());
        PropertyDefContainer container = new PropertyDefContainer(exchangeModel.getPropertyDefinitions());
        PropertyExtractor extractor = new PropertyExtractor(container);


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
        for (Relation relation : elementContainer.getRelation(
                elementType.getIdentifier(),
                RelationshipTypeEnum.ASSOCIATION,
                true, false)) {
            ElementType element = elementContainer.get(relation.getSink());
            if (element != null && element.getClass().equals(Assessment.class)) {
                attacks.add(new AttackImpl(relation.getSink(), elementContainer));
            }
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

    private void getExtends(ElementType elementType, List<String> extended) {
        for (Relation relation : elementContainer.getRelation(
                elementType.getIdentifier(),
                RelationshipTypeEnum.SPECIALIZATION,
                true, true)) {
            extended.add(format(relation.getSink()));
        }
    }
}
