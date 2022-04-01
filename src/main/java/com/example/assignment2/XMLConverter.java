package com.example.assignment2;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XMLConverter extends AirlineData{

    public static void createXML(ArrayList<String> headings) throws IOException {

        CSVReader reader = new CSVReader();
        ArrayList<ArrayList<String>> data;

        // Get the CSV data
        data = reader.ReadCSV();

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Root Element of XML File
            Element root = doc.createElement("airlineData");
            doc.appendChild(root);

            for (int i = 0; i < data.get(0).size(); i++) {
                Element airline = doc.createElement("Airline");
                root.appendChild(airline);

                Attr attr = doc.createAttribute(headings.get(0));
                attr.setValue(data.get(0).get(i));
                airline.setAttributeNode(attr);

                for (int j = 1; j < headings.size(); j++){
                    Element ele = doc.createElement(headings.get(j));
                    ele.appendChild(doc.createTextNode(data.get(j).get(i)));
                    airline.appendChild(ele);
                }

            }


            // conversion to XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer tf = transformerFactory.newTransformer();

            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty(OutputKeys.METHOD, "xml");

            DOMSource source = new DOMSource(doc);
            //StreamResult result = new StreamResult(new File(path));
            StreamResult result = new StreamResult(new File(path + "converted_airline_safety.xml"));
            tf.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
