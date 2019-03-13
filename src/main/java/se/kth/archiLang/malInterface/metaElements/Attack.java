package se.kth.archiLang.malInterface.metaElements;

import java.util.List;

public interface Attack extends MetaElement {
    List<Attack> getFollowUpAttack();

    String getRelatedClass();

    RelationEnum getRelation();
}
