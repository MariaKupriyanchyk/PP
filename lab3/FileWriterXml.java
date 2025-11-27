package org.example;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;

public class FileWriterXml {

    public static void write(String filename, AbstractStorage storage) {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.newDocument();
            Element root = doc.createElement("Sofas");
            doc.appendChild(root);

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            for (Sofa s : storage) {
                Element sofaTag = doc.createElement("Sofa");

                Element id = doc.createElement("id");
                id.setTextContent(String.valueOf(s.getId()));
                sofaTag.appendChild(id);

                Element type = doc.createElement("type");
                type.setTextContent(s.getType());
                sofaTag.appendChild(type);

                Element model = doc.createElement("model");
                model.setTextContent(s.getModel());
                sofaTag.appendChild(model);

                Element price = doc.createElement("price");
                price.setTextContent(String.valueOf(s.getPrice()));
                sofaTag.appendChild(price);

                Element date = doc.createElement("releaseDate");
                date.setTextContent(sdf.format(s.getReleaseDate()));
                sofaTag.appendChild(date);

                root.appendChild(sofaTag);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            t.transform(new DOMSource(doc), new StreamResult(new File(filename)));

            System.out.println("XML успешно записан в " + filename);

        } catch (Exception e) {
            System.err.println("Ошибка записи XML: " + e.getMessage());
        }
    }
}
