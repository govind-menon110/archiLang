package se.kth.archiLang;


import se.kth.archiLang.archiMalAdapter.MetaElements;
import se.kth.archiLang.generated.archimate3.ModelType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(ModelType.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            ModelType model = ((JAXBElement<ModelType>) unmarshaller.unmarshal(new File("Power Plant.xml"))).getValue();

            MetaElements metaElements = new MetaElements(model);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
