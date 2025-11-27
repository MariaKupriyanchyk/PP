package org.example;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class FileReaderTxt {

    public static List<Sofa> read(String filename) {
        List<Sofa> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length != 5) {
                    System.err.println("Ошибка формата строки: " + line);
                    continue;
                }

                try {
                    int id = Validators.parseIntStrict(parts[0]);
                    String type = parts[1];
                    String model = parts[2];
                    double price = Validators.parseDoubleStrict(parts[3]);
                    Date date = Validators.parseDateStrict(parts[4]);

                    result.add(new Sofa(id, type, model, price, date));

                } catch (NumberFormatException | ParseException e) {
                    System.err.println("Ошибка данных: " + line);
                }
            }

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + filename);
        }

        return result;
    }
}
