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

public class XMLConverter {

    public static void createXML() throws IOException {

        CSVReader reader = new CSVReader();
        reader.addColumn();

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Root Element of XML File
            Element root = doc.createElement("airlineData");
            doc.appendChild(root);

            for (int i = 0; i < reader.data.get(0).size(); i++) {
                Element airline = doc.createElement("Airline");
                root.appendChild(airline);

                Attr attr = doc.createAttribute("name");
                attr.setValue(reader.data.get(0).get(i));
                airline.setAttributeNode(attr);

                Element avail_km_per_week = doc.createElement("availKmPerWeek");
                avail_km_per_week.appendChild(doc.createTextNode(reader.data.get(1).get(i)));
                airline.appendChild(avail_km_per_week);

                Element incidents85To99 = doc.createElement("incidents85To99");
                incidents85To99.appendChild(doc.createTextNode(reader.data.get(2).get(i)));
                airline.appendChild(incidents85To99);

                Element fatalAcc85To99 = doc.createElement("fatalAcc85To99");
                fatalAcc85To99.appendChild(doc.createTextNode(reader.data.get(3).get(i)));
                airline.appendChild(fatalAcc85To99);

                Element fatalities85To99 = doc.createElement("fatalities85To99");
                fatalities85To99.appendChild(doc.createTextNode(reader.data.get(4).get(i)));
                airline.appendChild(fatalities85To99);

                Element incidents00To14 = doc.createElement("incidents00To14");
                incidents00To14.appendChild(doc.createTextNode(reader.data.get(5).get(i)));
                airline.appendChild(incidents00To14);

                Element fatalAcc00To14 = doc.createElement("fatalAcc00To14");
                fatalAcc00To14.appendChild(doc.createTextNode(reader.data.get(6).get(i)));
                airline.appendChild(fatalAcc00To14);

                Element fatalities00To14 = doc.createElement("fatalities00To14");
                fatalities00To14.appendChild(doc.createTextNode(reader.data.get(7).get(i)));
                airline.appendChild(fatalities00To14);

                Element totalIncidents = doc.createElement("totalIncidents");
                totalIncidents.appendChild(doc.createTextNode(reader.data.get(8).get(i)));
                airline.appendChild(totalIncidents);
            }


            // conversion to XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer tf = transformerFactory.newTransformer();

            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty(OutputKeys.METHOD, "xml");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("/Users/amit.sar21/University/Second Year/Semester 2 /Systems Development and Integration - CSCI 2020/assignment/assignment-2-amitsarvate/src/main/resources/com/example/assignment2/converted_airline_safety.xml"));
            tf.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        XMLConverter.createXML();

    }








}
