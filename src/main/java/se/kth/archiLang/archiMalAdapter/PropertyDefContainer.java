package se.kth.archiLang.archiMalAdapter;

import se.kth.archiLang.generated.archimate3.PropertyDefinitionType;
import se.kth.archiLang.generated.archimate3.PropertyDefinitionsType;

public class PropertyDefContainer {
    PropertyDefinitionsType propertyDefinitionsType;

    public PropertyDefContainer(PropertyDefinitionsType propertyDefinitionsType) {
        this.propertyDefinitionsType = propertyDefinitionsType;
    }

    public String getPropertyDefinitionRef(String type) {
        for (PropertyDefinitionType propertyDefinitionType : propertyDefinitionsType.getPropertyDefinition()) {
            if (propertyDefinitionType.getNameGroup().get(0).getValue().equals(type))
                return propertyDefinitionType.getIdentifier();
        }
        return null;
    }
}
