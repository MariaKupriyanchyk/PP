package org.example;

import javax.json.*;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileReaderJson {

    public static List<Sofa> read(String filename) {
        List<Sofa> list = new ArrayList<>();

        try (JsonReader reader = Json.createReader(new FileInputStream(filename))) {

            JsonArray arr = reader.readArray();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            for (JsonValue value : arr) {
                JsonObject obj = value.asJsonObject();

                int id = obj.getInt("id");
                String type = obj.getString("type");
                String model = obj.getString("model");
                double price = obj.getJsonNumber("price").doubleValue();
                Date date = sdf.parse(obj.getString("releaseDate"));

                list.add(new Sofa(id, type, model, price, date));
            }

        } catch (Exception e) {
            System.err.println("Ошибка чтения JSON: " + e.getMessage());
        }

        return list;
    }
}
