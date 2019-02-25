package se.kth.archiLang.malInterface;

import java.util.List;

public interface Class {
    String getName();

    List<Class> getExtends();

    List<Attack> getAttacks();

    List<Defense> getDefenses();
}
