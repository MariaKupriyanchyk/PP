package org.example;

public interface AbstractStorage extends Iterable<Sofa> {
    void add(Sofa s);
    void remove(int id);
    Sofa get(int id);
    void update(int id, Sofa s);
    void clear();
}
