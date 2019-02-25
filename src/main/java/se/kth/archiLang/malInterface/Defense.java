package se.kth.archiLang.malInterface;

import java.util.List;

public interface Defense {
    String getName();

    List<Attack> getParentedAttacks();
}
