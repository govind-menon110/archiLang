package se.kth.archiLang.malInterface.metaElements;

import java.util.List;

public interface Defense {
    String getName();

    List<Attack> getParentedAttacks();
}
