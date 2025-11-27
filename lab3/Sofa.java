package org.example;

import java.util.Date;

public class Sofa extends AbstractFurniture {

    public Sofa(int id, String type, String model, double price, Date releaseDate) {
        super(id, type, model, price, releaseDate);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
