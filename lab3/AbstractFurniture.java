package org.example;

import java.util.Date;

public abstract class AbstractFurniture {
    protected int id;
    protected String type;
    protected String model;
    protected double price;
    protected Date releaseDate;

    public AbstractFurniture(int id, String type, String model, double price, Date releaseDate) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public int getId() { return id; }
    public String getType() { return type; }
    public String getModel() { return model; }
    public double getPrice() { return price; }
    public Date getReleaseDate() { return releaseDate; }

    public void setId(int id) { this.id = id; }
    public void setType(String type) { this.type = type; }
    public void setModel(String model) { this.model = model; }
    public void setPrice(double price) { this.price = price; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }

    @Override
    public String toString() {
        return id + ";" + type + ";" + model + ";" + price + ";" +
                new java.text.SimpleDateFormat("dd.MM.yyyy").format(releaseDate);
    }
}
