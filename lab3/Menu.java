package org.example;

import java.text.ParseException;
import java.util.*;

public class Menu {

    public static void show(AbstractStorage storage, String inputFile) throws ParseException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- МЕНЮ ---");
            System.out.println("1. Показать все");
            System.out.println("2. Добавить");
            System.out.println("3. Удалить");
            System.out.println("4. Обновить");
            System.out.println("5. Сортировать");
            System.out.println("6. Запись в TXT");
            System.out.println("7. Чтение из XML");
            System.out.println("8. Запись в XML");
            System.out.println("9. Чтение из JSON");
            System.out.println("10. Запись в JSON");
            System.out.println("11. Зашифровать файл");
            System.out.println("12. Расшифровать файл");
            System.out.println("13. Упаковать в ZIP");
            System.out.println("14. Распаковать ZIP");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");

            String c = sc.nextLine();

            switch (c) {
                case "1" -> showAll(storage);
                case "2" -> addElement(storage, sc);
                case "3" -> removeElement(storage, sc);
                case "4" -> updateElement(storage, sc);
                case "5" -> sortMenu(storage, sc);
                case "6" -> saveTXT(storage, sc);
                case "7" -> loadXML(storage, sc);
                case "8" -> saveXML(storage, sc);
                case "9" -> loadJSON(storage, sc);
                case "10" -> saveJSON(storage, sc);
                case "11" -> encryptFile(sc);
                case "12" -> decryptFile(sc);
                case "13" -> zipFile(sc);
                case "14" -> unzipFile(sc);
                case "0" -> { return; }
                default -> System.out.println("Неверный ввод");
            }
        }
    }

    private static void showAll(AbstractStorage storage) {
        for (Sofa s : storage) {
            System.out.println(s);
        }
    }

    private static void addElement(AbstractStorage storage, Scanner sc) throws ParseException {
        System.out.print("ID: ");
        Integer id = Validators.parseIntStrict(sc.nextLine().trim());
        if (id == null) { System.out.println("Ошибка ID"); return; }

        System.out.print("Тип: ");
        String type = sc.nextLine().trim();
        if (type.isEmpty()) { System.out.println("Ошибка типа"); return; }

        System.out.print("Модель: ");
        String model = sc.nextLine().trim();
        if (model.isEmpty()) { System.out.println("Ошибка модели"); return; }

        System.out.print("Цена: ");
        Double price = Validators.parseDoubleStrict(sc.nextLine().trim());
        if (price == null) { System.out.println("Ошибка цены"); return; }

        System.out.print("Дата (dd.MM.yyyy): ");
        Date date = Validators.parseDateStrict(sc.nextLine().trim());
        if (date == null) { System.out.println("Ошибка даты"); return; }

        storage.add(new Sofa(id, type, model, price, date));
        System.out.println("Добавлено");
    }

    private static void removeElement(AbstractStorage storage, Scanner sc) {
        System.out.print("ID для удаления: ");
        Integer id = Validators.parseIntStrict(sc.nextLine());
        if (id == null) { System.out.println("Ошибка ID"); return; }

        storage.remove(id);
        System.out.println("Удалено");
    }

    private static void updateElement(AbstractStorage storage, Scanner sc) throws ParseException {
        System.out.print("ID для обновления: ");
        Integer id = Validators.parseIntStrict(sc.nextLine());
        if (id == null) { System.out.println("Ошибка ID"); return; }

        Sofa s = storage.get(id);
        if (s == null) { System.out.println("Не найдено"); return; }

        System.out.println("Текущий: " + s);

        System.out.print("Новый тип (пусто = без изменения): ");
        String type = sc.nextLine();
        if (!type.isBlank()) s.setType(type);

        System.out.print("Новая модель: ");
        String model = sc.nextLine();
        if (!model.isBlank()) s.setModel(model);

        System.out.print("Новая цена: ");
        String priceStr = sc.nextLine();
        if (!priceStr.isBlank()) {
            Double price = Validators.parseDoubleStrict(priceStr);
            if (price != null) s.setPrice(price);
            else System.out.println("Ошибка цены – пропуск");
        }

        System.out.print("Новая дата: ");
        String dateStr = sc.nextLine();
        if (!dateStr.isBlank()) {
            Date d = Validators.parseDateStrict(dateStr);
            if (d != null) s.setReleaseDate(d);
            else System.out.println("Ошибка даты – пропуск");
        }

        storage.update(id, s);
        System.out.println("Обновлено");
    }

    private static void sortMenu(AbstractStorage storage, Scanner sc) {
        List<Sofa> list = new ArrayList<>();
        for (Sofa s : storage) list.add(s);

        System.out.println("""
                1. По ID
                2. По типу
                3. По модели
                4. По цене
                5. По дате""");

        String c = sc.nextLine();

        switch (c) {
            case "1" -> list.sort(Comparators.byId);
            case "2" -> list.sort(Comparators.byType);
            case "3" -> list.sort(Comparators.byModel);
            case "4" -> list.sort(Comparators.byPrice);
            case "5" -> list.sort(Comparators.byDate);
        }

        System.out.println("--- Результат сортировки ---");
        list.forEach(System.out::println);
    }

    private static void saveTXT(AbstractStorage storage, Scanner sc) {
        System.out.print("Введите имя файла для записи: ");
        String f = sc.nextLine().trim();

        if (!f.endsWith(".txt")) f = f + ".txt";

        FileWriterTxt.write(f, storage);
    }

    private static void loadXML(AbstractStorage storage, Scanner sc) {
        System.out.print("XML файл: ");
        String f = sc.nextLine();
        List<Sofa> list = FileReaderXml.read(f);

        storage.clear();

        for (Sofa s : list) storage.add(s);
        System.out.println("XML загружен");
    }

    private static void saveXML(AbstractStorage storage, Scanner sc) {
        System.out.print("XML файл для сохранения: ");
        String f = sc.nextLine();
        FileWriterXml.write(f, storage);
    }

    private static void loadJSON(AbstractStorage storage, Scanner sc) {
        System.out.print("JSON файл: ");
        String f = sc.nextLine();
        List<Sofa> list = FileReaderJson.read(f);

        storage.clear();

        for (Sofa s : list) storage.add(s);
        System.out.println("JSON загружен");
    }

    private static void saveJSON(AbstractStorage storage, Scanner sc) {
        System.out.print("JSON файл для сохранения: ");
        String f = sc.nextLine();
        FileWriterJson.write(f, storage);
    }

    private static void encryptFile(Scanner sc) {
        System.out.print("Файл для шифрования: ");
        String in = sc.nextLine();
        System.out.print("Выходной файл: ");
        String out = sc.nextLine();
        CryptoUtils.encrypt(in, out);
    }

    private static void decryptFile(Scanner sc) {
        System.out.print("Файл для расшифровки: ");
        String in = sc.nextLine();
        System.out.print("Выходной файл: ");
        String out = sc.nextLine();
        CryptoUtils.decrypt(in, out);
    }

    private static void zipFile(Scanner sc) {
        System.out.print("Файл для упаковки: ");
        String in = sc.nextLine();
        System.out.print("Имя ZIP: ");
        String out = sc.nextLine();
        ZipUtils.zip(in, out);
    }

    private static void unzipFile(Scanner sc) {
        System.out.print("ZIP файл: ");
        String in = sc.nextLine();
        System.out.print("Файл для сохранения: ");
        String out = sc.nextLine();
        ZipUtils.unzip(in, out);
    }
}
