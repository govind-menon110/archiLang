package se.kth.archiLang;


import se.kth.archiLang.archiMalAdapter.MetaElements;
import se.kth.archiLang.generated.archimate3.ModelType;
import se.kth.archiLang.malGenerator.Generator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(ModelType.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            ModelType model = ((JAXBElement<ModelType>) unmarshaller.unmarshal(new File("Power Grid.xml"))).getValue();

            MetaElements metaElements = new MetaElements(model);

            String malMeta = Generator.generateMeta(metaElements, "MainCategory");

            BufferedWriter writer = new BufferedWriter(new FileWriter("powerGridLang_generated.mal"));
            writer.write(malMeta);
            writer.close();

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
