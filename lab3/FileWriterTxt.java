package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTxt {

    public static void write(String filename, AbstractStorage storage) {
        try (FileWriter fw = new FileWriter(filename)) {

            for (Sofa s : storage) {
                fw.write(s.toString() + "\n");
            }

            System.out.println("Данные успешно сохранены в файл: " + filename);

        } catch (IOException e) {
            System.out.println("Ошибка записи файла!");
        }
    }
}
