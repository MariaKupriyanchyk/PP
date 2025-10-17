package org.example;

import java.text.*;
import java.util.*;
import java.util.regex.*;

public class StringProcessing {

    public String processText(String text, String delimiters) {
        StringBuilder result = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(text, delimiters);
        List<String> tokenList = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            tokenList.add(tokenizer.nextToken());
        }

        StringBuilder modified = new StringBuilder(text);

        if (!tokenList.isEmpty()) {
            String toRemove = tokenList.get(0);

            int pos = modified.indexOf(toRemove);
            if (pos != -1) {
                modified.delete(pos, pos + toRemove.length());
            }

            tokenList.remove(0);
        }

        String[] tokens = tokenList.toArray(new String[0]);

        List<String> latinTemp = new ArrayList<>();
        List<String> datesTemp = new ArrayList<>();
        List<String> numbersTemp = new ArrayList<>();

        Pattern datePattern = Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");
        SimpleDateFormat sdf = new SimpleDateFormat("MM:dd:yy");
        sdf.setLenient(false);

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            if (token.matches("\\d+")) {
                numbersTemp.add(token);
            } else if (token.matches("[A-Za-z]+")) {
                latinTemp.add(token);
            } else {
                Matcher m = datePattern.matcher(token);
                if (m.matches()) {
                    try {
                        sdf.parse(token);
                        datesTemp.add(token);
                    } catch (ParseException e) {
                    }
                }
            }
        }

        String[] latinWords = latinTemp.toArray(new String[0]);
        String[] dates = datesTemp.toArray(new String[0]);
        String[] numbers = numbersTemp.toArray(new String[0]);

        Random rand = new Random();
        boolean inserted = false;

        if (latinWords.length > 0) {
            List<String> present = new ArrayList<>();
            for (String w : latinWords) {
                if (modified.indexOf(w) >= 0) present.add(w);
            }

            if (!present.isEmpty()) {
                String randomWord = present.get(rand.nextInt(present.size()));
                int index = modified.indexOf(randomWord);
                if (index >= 0) {
                    int insertPos = index + randomWord.length();
                    modified.insert(insertPos, rand.nextInt(100));
                    inserted = true;
                }
            }
        }

        if (!inserted) {
            int mid = modified.length() / 2;
            modified.insert(mid, rand.nextInt(100));
        }

        Arrays.sort(dates, (a, b) -> {
            try {
                Date d1 = sdf.parse(a);
                Date d2 = sdf.parse(b);
                return d1.compareTo(d2);
            } catch (ParseException e) {
                return a.compareTo(b);
            }
        });

        result.append("Исходный текст: ").append(text).append("\n");
        result.append("Разделители: ").append(delimiters).append("\n\n");

        result.append("Лексемы:\n");
        for (String t : tokens) {
            result.append(t).append("\n");
        }

        result.append("\nЛатинские слова: ").append(Arrays.toString(latinWords)).append("\n");
        result.append("Числа: ").append(Arrays.toString(numbers)).append("\n");

        result.append("Даты:\n");
        if (dates.length == 0) {
            result.append("— (нет корректных дат)\n");
        } else {
            Formatter fmt = new Formatter();
            DateFormat outFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
            for (String d : dates) {
                try {
                    Date date = sdf.parse(d);
                    fmt.format("%s (%s)%n", d, outFormat.format(date));
                } catch (ParseException ignored) {}
            }
            result.append(fmt.toString());
            fmt.close();
        }

        result.append("\nИзмененная строка:\n").append(modified).append("\n");

        StringBuilder reversed = new StringBuilder(modified).reverse();
        result.append("\nОбратная строка:\n").append(reversed).append("\n");

        return result.toString();
    }
}
