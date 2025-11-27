package org.example;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileReaderXml {

    public static List<Sofa> read(String filename) {
        List<Sofa> list = new ArrayList<>();

        try {
            File file = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nl = doc.getElementsByTagName("Sofa");
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);

                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) n;

                    int id = Integer.parseInt(e.getElementsByTagName("id").item(0).getTextContent());
                    String type = e.getElementsByTagName("type").item(0).getTextContent();
                    String model = e.getElementsByTagName("model").item(0).getTextContent();
                    double price = Double.parseDouble(e.getElementsByTagName("price").item(0).getTextContent());
                    Date date = sdf.parse(e.getElementsByTagName("releaseDate").item(0).getTextContent());

                    list.add(new Sofa(id, type, model, price, date));
                }
            }

        } catch (Exception e) {
            System.err.println("Ошибка чтения XML: " + e.getMessage());
        }

        return list;
    }
}
