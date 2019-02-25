package se.kth.archiLang.malInterface.metaElements;

import java.util.List;

public interface Attack {
    String getName();

    List<Attack> getFollowUpAttack();

    Class getRelatedClass();

    RelationEnum getRelation();
}
