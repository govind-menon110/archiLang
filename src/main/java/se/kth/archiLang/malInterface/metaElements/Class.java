package se.kth.archiLang.malInterface.metaElements;

import java.util.List;

public interface Class extends MetaElement {
    List<Class> getExtends();

    List<Attack> getAttacks();

    List<Defense> getDefenses();
}
