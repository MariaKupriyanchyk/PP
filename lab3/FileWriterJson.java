package org.example;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class FileWriterJson {

    public static void write(String filename, AbstractStorage storage) {
        Map<String, Boolean> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);

        JsonWriterFactory writerFactory = Json.createWriterFactory(config);

        try (FileOutputStream fos = new FileOutputStream(filename);
             JsonWriter writer = writerFactory.createWriter(fos)) {

            JsonArrayBuilder arr = Json.createArrayBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            for (Sofa s : storage) {
                arr.add(Json.createObjectBuilder()
                        .add("id", s.getId())
                        .add("type", s.getType())
                        .add("model", s.getModel())
                        .add("price", s.getPrice())
                        .add("releaseDate", sdf.format(s.getReleaseDate()))
                );
            }

            writer.writeArray(arr.build());
            System.out.println("JSON успешно записан в " + filename);

        } catch (Exception e) {
            System.err.println("Ошибка записи JSON: " + e.getMessage());
        }
    }
}
