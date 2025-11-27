package org.example;

import java.io.*;
import java.util.zip.*;

public class ZipUtils {

    public static void zip(String inputFile, String zipFile) {
        try (
                FileInputStream fis = new FileInputStream(inputFile);
                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))
        ) {
            ZipEntry entry = new ZipEntry(new File(inputFile).getName());
            zos.putNextEntry(entry);

            zos.write(fis.readAllBytes());
            zos.closeEntry();

            System.out.println("Файл упакован в ZIP: " + zipFile);

        } catch (Exception e) {
            System.err.println("Ошибка ZIP архивации: " + e.getMessage());
        }
    }

    public static void unzip(String zipFile, String outputFile) {
        try (
                ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
                FileOutputStream fos = new FileOutputStream(outputFile)
        ) {
            ZipEntry entry = zis.getNextEntry();
            if (entry == null) throw new IOException("ZIP пуст");

            fos.write(zis.readAllBytes());
            zis.closeEntry();

            System.out.println("ZIP успешно распакован в: " + outputFile);

        } catch (Exception e) {
            System.err.println("Ошибка распаковки ZIP: " + e.getMessage());
        }
    }
}
