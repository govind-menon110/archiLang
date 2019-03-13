package se.kth.archiLang.archiMalAdapter;

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

    public MetaElements(ModelType exchangeModel) {
        PropertyDefContainer container = new PropertyDefContainer(exchangeModel.getPropertyDefinitions());
        PropertyExtractor extractor = new PropertyExtractor(container);
        RelationContainer relationContainer = new RelationContainer(exchangeModel.getRelationships().getRelationship(),
                exchangeModel.getElements().getElement());

        for (ElementType elementType : exchangeModel.getElements().getElement()) {
            if (extractor.get("Modelelementtype", elementType).equals("Meta")) {
                List<String> extended = new LinkedList<>();
                List<Attack> attacks = new LinkedList<>();
                List<Defense> defense = new LinkedList<>();
                String name = elementType.getNameGroup().get(0).getValue();

                getExtends(relationContainer, elementType, extended);

                ClassImpl classImpl = new ClassImpl(extended, attacks, defense, name);

                classes.add(classImpl);
            }
        }
    }

    public void getExtends(RelationContainer relationContainer, ElementType elementType, List<String> extended) {
        for (Relation relation : relationContainer.getRelation(
                elementType.getIdentifier(),
                RelationshipTypeEnum.SPECIALIZATION,
                true)) {
            extended.add(relation.getSink());
        }
    }

    public List<Class> getClasses() {
        return classes;
    }
}
