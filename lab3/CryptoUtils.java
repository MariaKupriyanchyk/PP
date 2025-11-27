package org.example;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CryptoUtils {

    private static final String ALGO = "AES";
    private static final byte[] KEY = "MySecretKey12345".getBytes(); // 16 bytes — OK for AES

    public static void encrypt(String input, String output) {
        doCrypto(Cipher.ENCRYPT_MODE, input, output);
    }

    public static void decrypt(String input, String output) {
        doCrypto(Cipher.DECRYPT_MODE, input, output);
    }

    private static void doCrypto(int cipherMode, String input, String output) {
        try {
            if (input.equals(output)) {
                System.err.println("Ошибка: входной и выходной файлы не могут совпадать");
                return;
            }

            File inFile = new File(input);

            if (!inFile.exists()) {
                System.err.println("Ошибка: входной файл не существует: " + input);
                return;
            }

            if (!inFile.isFile()) {
                System.err.println("Ошибка: указанный входной путь не является файлом: " + input);
                return;
            }

            if (inFile.length() == 0) {
                System.err.println("Ошибка: входной файл пустой: " + input);
                return;
            }

            File outFile = new File(output);

            try {
                if (outFile.exists()) {
                    if (!outFile.canWrite()) {
                        System.err.println("Ошибка: нет доступа на запись в файл: " + output);
                        return;
                    }
                } else {
                    if (!outFile.createNewFile()) {
                        System.err.println("Ошибка: невозможно создать выходной файл: " + output);
                        return;
                    }
                    outFile.delete();
                }
            } catch (Exception ex) {
                System.err.println("Ошибка доступа к выходному файлу: " + ex.getMessage());
                return;
            }

            SecretKeySpec secretKey = new SecretKeySpec(KEY, ALGO);
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(cipherMode, secretKey);

            byte[] buffer;
            try (FileInputStream fis = new FileInputStream(inFile)) {
                buffer = fis.readAllBytes();
            }

            byte[] result;
            try {
                result = cipher.doFinal(buffer);
            } catch (Exception cryptoEx) {
                System.err.println("Ошибка криптографической операции: "
                        + cryptoEx.getMessage()
                        + "\nВероятно, Вы пытаетесь расшифровать НЕ зашифрованный файл.");
                return;
            }

            try (FileOutputStream fos = new FileOutputStream(outFile)) {
                fos.write(result);
            }

            System.out.println((cipherMode == Cipher.ENCRYPT_MODE
                    ? "Файл успешно зашифрован: "
                    : "Файл успешно расшифрован: ") + output);

        } catch (Exception e) {
            System.err.println("Системная ошибка: " + e.getMessage());
        }
    }
}
