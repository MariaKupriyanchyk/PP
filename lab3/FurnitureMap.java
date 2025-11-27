package org.example;

import java.util.*;

public class FurnitureMap implements AbstractStorage {

    private final Map<Integer, Sofa> map = new TreeMap<>();

    public Map<Integer, Sofa> getMap() { return map; }

    @Override
    public void add(Sofa s) { map.put(s.getId(), s); }

    @Override
    public void remove(int id) { map.remove(id); }

    @Override
    public Sofa get(int id) { return map.get(id); }

    @Override
    public void update(int id, Sofa s) { map.put(id, s); }

    @Override
    public Iterator<Sofa> iterator() {
        return map.values().iterator();
    }

    @Override
    public void clear() {
        map.clear();
    }
}
