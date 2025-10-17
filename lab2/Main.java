//8. Разбить первую строку на лексемы (используя разделители из  второй строки).
//Лексемы записать в новый отдельные массив.
//Среди лексем не являющихся числами, найти лексемы состоящие только из символов латинского алфавита.
//Среди лексем не являющихся числами, найти даты (ММ:ДД:ГГ).
//Добавить в строку случайное число после лексемы на латинском или в середину строки(если нет) .
//Подстроку (с любой лексемой) - удалить из строки.
//Все результаты сформировать в строки и вывести.

package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            String inputFile = "src/main/resources/input.txt";
            String outputFile = "src/main/resources/output.txt";

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String text = br.readLine();
            String delimiters = br.readLine();
            br.close();

            StringProcessing processor = new StringProcessing();
            String result = processor.processText(text, delimiters);

            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
            bw.write(result);
            bw.close();

            System.out.println("Результат в output.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}