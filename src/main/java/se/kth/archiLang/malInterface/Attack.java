package se.kth.archiLang.malInterface;

import java.util.List;

public interface Attack {
    String getName();

    List<Attack> getFollowUpAttack();

    Class getRelatedClass();

    RelationEnum getRelation();
}
