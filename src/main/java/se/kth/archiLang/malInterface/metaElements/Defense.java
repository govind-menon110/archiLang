package se.kth.archiLang.malInterface.metaElements;

import java.util.List;

public interface Defense extends MetaElement {
    List<Attack> getParentedAttacks();
}
