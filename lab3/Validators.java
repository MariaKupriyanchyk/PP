package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validators {

    public static int parseIntStrict(String s) throws NumberFormatException {
        return Integer.parseInt(s.trim());
    }

    public static double parseDoubleStrict(String s) throws NumberFormatException {
        return Double.parseDouble(s.trim());
    }

    public static Date parseDateStrict(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(false);
        return sdf.parse(s.trim());
    }
}
