package se.kth.archiLang.archiMalAdapter;

import se.kth.archiLang.generated.archimate3.ElementType;
import se.kth.archiLang.generated.archimate3.PropertyType;

public class PropertyExtractor {
    private PropertyDefContainer propertyDefContainer;

    public PropertyExtractor(PropertyDefContainer propertyDefContainer) {
        this.propertyDefContainer = propertyDefContainer;
    }

    public String get(String type, ElementType element) {
        for (PropertyType property : element.getProperties().getProperty()) {
            if (property.getPropertyDefinitionRef().toString().equals(propertyDefContainer.getPropertyDefinitionRef(type)))
                return property.getValue().get(0).getValue();
        }
        return null;
    }
}
