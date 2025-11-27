package org.example;

import java.text.ParseException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {

        String filename = "input.txt";
        List<Sofa> list = FileReaderTxt.read(filename);

        AbstractStorage storage = new FurnitureList();
        for (Sofa s : list) storage.add(s);

        Menu.show(storage, filename);
    }
}
