package org.example;

import java.util.Comparator;

public class Comparators {
    public static Comparator<Sofa> byId = Comparator.comparingInt(Sofa::getId);
    public static Comparator<Sofa> byType = Comparator.comparing(Sofa::getType);
    public static Comparator<Sofa> byModel = Comparator.comparing(Sofa::getModel);
    public static Comparator<Sofa> byPrice = Comparator.comparingDouble(Sofa::getPrice);
    public static Comparator<Sofa> byDate = Comparator.comparing(Sofa::getReleaseDate);
}
