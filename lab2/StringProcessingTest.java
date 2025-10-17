package org.example;

import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class StringProcessingTest {
    private final StringProcessing processor = new StringProcessing();

    @Test
    void testTokenizationWithDifferentDelimiters() {
        String text = "dnc,1943;qw:44:77;hi";
        String delimiters = ",;:";
        String result = processor.processText(text, delimiters);

        assertTrue(result.contains("dnc"));
        assertTrue(result.contains("hi"));
        assertTrue(result.contains("1943"));
    }

    @Test
    void testFindLatinWords() {
        String text = "222 12:19:06 kuku,ваап 88";
        String delimiters = " ,:";
        String result = processor.processText(text, delimiters);

        assertTrue(result.contains("Латинские слова: [kuku]"));
    }

    @Test
    void testFindDates() {
        String text = "11:14:06 kdfk 99:22:66 02:20:09";
        String delimiters = " ";
        String result = processor.processText(text, delimiters);

        assertTrue(result.contains("11:14:06"));
        assertTrue(result.contains("02:20:09"));
        assertTrue(result.contains("Даты:"));
    }

    @Test
    void testRandomNumberInsertion() {
        String text = "qwerty, bvcxz, 990";
        String delimiters = ", ";
        String result = processor.processText(text, delimiters);

        assertTrue(Pattern.compile("qwerty\\d+|qwerty\\d+,|bvcxz\\d+|bvcxz\\d+,")
                .matcher(result).find());
    }

    @Test
    void testStringModificationAndReverse() {
        String text = "idc 333 blblbl";
        String delimiters = " ";
        String result = processor.processText(text, delimiters);

        assertTrue(result.contains("Измененная строка:"));
        assertTrue(result.contains("Обратная строка:"));
    }
}
