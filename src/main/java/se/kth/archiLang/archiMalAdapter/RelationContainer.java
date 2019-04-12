package se.kth.archiLang.archiMalAdapter;

import se.kth.archiLang.malInterface.metaElements.Relation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class RelationContainer {
    private HashMap<String, HashMap<String, Relation>> relations = new HashMap<>();

    public List<Relation> getList() {
        List<Relation> list = new LinkedList<>();

        for (HashMap<String, Relation> map : relations.values()) {
            list.addAll(map.values());
        }

        return list;
    }

    public void add(Relation relation) {
        if (!(relations.containsKey(relation.getSink())
                && relations.get(relation.getSink()).containsKey(relation.getSource()))) {
            if (!relations.containsKey(relation.getSource())) {
                relations.put(relation.getSource(), new HashMap<>());
            }
            relations.get(relation.getSource()).put(relation.getSink(), relation);
        }
    }
}
