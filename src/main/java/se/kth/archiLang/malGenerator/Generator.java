package se.kth.archiLang.malGenerator;

import se.kth.archiLang.archiMalAdapter.MetaElements;
import se.kth.archiLang.malInterface.metaElements.Attack;
import se.kth.archiLang.malInterface.metaElements.Class;
import se.kth.archiLang.malInterface.metaElements.Defense;
import se.kth.archiLang.malInterface.metaElements.Relation;

public class Generator {
    public static String generateMeta(MetaElements elements, String category) {
        StringBuilder builder = new StringBuilder();

        builder.append("include archiLang.mal" + System.lineSeparator() + System.lineSeparator());

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

                String attackStepName = attack.getName();
                builder.append(attackStepName + System.lineSeparator());

                int followUpAttackSteps = 0;
                for (Attack followAttack : attack.getFollowUpAttack()) {
                    Boolean sameClass = !followAttack.getRelatedClass().equals(attack.getRelatedClass());
                    String followUpAttackStepName = followAttack.getQualifiedName(sameClass);
                    if (followUpAttackSteps == 0)
                        builder.append("-> " + followUpAttackStepName + System.lineSeparator());
                    else
                        builder.append(", " + followUpAttackStepName + System.lineSeparator());
                    followUpAttackSteps++;
                }
            }

            for (Defense defense : malClass.getDefenses()) {
                //TODO
            }

            builder.append("}" + System.lineSeparator());
        }

        builder.append("}" + System.lineSeparator());

        //add Relations
        builder.append("associations {" + System.lineSeparator());

        int counter = 0;

        for (Relation relation : elements.getRelations()) {
            builder.append(relation.getSource() + " [" + relation.getSourceLabel() + counter++ + "] ");
            builder.append(relation.getSourceCarindality() + " <-- " + relation.getName() + " --> " + relation.getSinkCardinality() + " ");
            builder.append("[" + relation.getSinkLabel() + counter++ + "] " + relation.getSink() + System.lineSeparator());
        }

        builder.append("}" + System.lineSeparator());

        return builder.toString();
    }
}
