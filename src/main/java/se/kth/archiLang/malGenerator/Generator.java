package se.kth.archiLang.malGenerator;

import se.kth.archiLang.archiMalAdapter.MetaElements;
import se.kth.archiLang.malInterface.metaElements.Attack;
import se.kth.archiLang.malInterface.metaElements.Class;
import se.kth.archiLang.malInterface.metaElements.Defense;

public class Generator {
    public static String generateMeta(MetaElements elements, String category) {
        StringBuilder builder = new StringBuilder();

        builder.append("category " + category + " {" + System.lineSeparator());

        for (Class malClass : elements.getClasses()) {
            builder.append("asset " + malClass.getName());

            if (malClass.getExtends().size() > 0) {
                builder.append(" extends ");
                for (int i = 0; i < malClass.getExtends().size(); i++) {
                    builder.append(malClass.getExtends().get(i));
                    builder.append(i == malClass.getExtends().size() - 1 ? " " : ", ");
                }
            }

            builder.append("{" + System.lineSeparator());

            for (Attack attack : malClass.getAttacks()) {
                builder.append(attack.getRelation().toString() + " ");
                builder.append(attack.getName() + System.lineSeparator());

                for (Attack followAttack : attack.getFollowUpAttack()) {
                    builder.append("-> " + followAttack.getRelatedClass() + "." + followAttack.getName() + System.lineSeparator());
                }
            }

            for (Defense defense : malClass.getDefenses()) {
                //TODO
            }

            builder.append("}" + System.lineSeparator());
        }

        builder.append("}" + System.lineSeparator());

        return builder.toString();
    }
}
