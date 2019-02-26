package se.kth.archiLang.malInterface.metaElements;

import java.util.List;

public interface Attack extends MetaElement {
    List<Attack> getFollowUpAttack();

    Class getRelatedClass();

    RelationEnum getRelation();
}
