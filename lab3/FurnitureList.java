package org.example;

import java.util.*;

public class FurnitureList implements AbstractStorage {

    private final List<Sofa> list = new ArrayList<>();

    public List<Sofa> getList() { return list; }

    @Override
    public void add(Sofa s) { list.add(s); }

    @Override
    public void remove(int id) { list.removeIf(s -> s.getId() == id); }

    @Override
    public Sofa get(int id) {
        return list.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void update(int id, Sofa s) {
        remove(id);
        add(s);
    }

    @Override
    public Iterator<Sofa> iterator() {
        return list.iterator();
    }

    @Override
    public void clear() {
        list.clear();
    }
}
