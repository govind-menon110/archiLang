package se.kth.archiLang.archiMalAdapter;

import se.kth.archiLang.malInterface.metaElements.Attack;
import se.kth.archiLang.malInterface.metaElements.Class;
import se.kth.archiLang.malInterface.metaElements.Defense;

import java.util.List;

public class ClassImpl implements Class {
    private List<String> extended;
    private List<Attack> attacks;
    private List<Defense> defense;
    private String name;

    public ClassImpl(List<String> extended, List<Attack> attacks, List<Defense> defense, String name) {
        this.extended = extended;
        this.attacks = attacks;
        this.defense = defense;
        this.name = name;
    }

    @Override
    public List<String> getExtends() {
        return extended;
    }

    @Override
    public List<Attack> getAttacks() {
        return attacks;
    }

    @Override
    public List<Defense> getDefenses() {
        return defense;
    }

    @Override
    public String getName() {
        return name;
    }
}
