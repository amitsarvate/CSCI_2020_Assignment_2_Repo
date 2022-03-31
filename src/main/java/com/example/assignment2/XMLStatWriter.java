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

public class XMLStatWriter {

    public static void WriteStatToXML() {

        ArrayList<ArrayList<String>> data = DataSum.getData();
        ArrayList<String> headings = new ArrayList<>();

        headings.add("avail_seat_km_week");
        headings.add("incident_85_99");
        headings.add("fatal_acc_85_99");
        headings.add("fatalities_85_99");
        headings.add("incident_00_14");
        headings.add("fatal_acc_00_14");
        headings.add("fatalities_00_14");
        headings.add("total_incidents");

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Root Element of XML File
            Element root = doc.createElement("Summary");
            doc.appendChild(root);

            for (int i = 0; i < data.get(0).size(); i++) {
                Element stat = doc.createElement("Stat");
                root.appendChild(stat);

                Element name = doc.createElement("Name");
                name.appendChild(doc.createTextNode(headings.get(i)));
                stat.appendChild(name);

                Element min = doc.createElement("Min");
                min.appendChild(doc.createTextNode(data.get(0).get(i)));
                stat.appendChild(min);

                Element max = doc.createElement("Max");
                max.appendChild(doc.createTextNode(data.get(1).get(i)));
                stat.appendChild(max);

                Element avg = doc.createElement("Avg");
                avg.appendChild(doc.createTextNode(data.get(2).get(i)));
                stat.appendChild(avg);

            }

            Element avgIncident85To99 = doc.createElement("AvgIncident85To99");
            avgIncident85To99.appendChild(doc.createTextNode(data.get(2).get(1)));
            root.appendChild(avgIncident85To99);

            Element avgIncident00To14 = doc.createElement("AvgIncident00To14");
            avgIncident00To14.appendChild(doc.createTextNode(data.get(2).get(4)));
            root.appendChild(avgIncident00To14);

            // conversion to XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer tf = transformerFactory.newTransformer();

            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty(OutputKeys.METHOD, "xml");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("/Users/amit.sar21/University/Second Year/Semester 2 /Systems Development and Integration - CSCI 2020/assignment/assignment-2-amitsarvate/src/main/resources/com/example/assignment2/airline_summary_statistic.xml"));
            tf.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        XMLStatWriter.WriteStatToXML();
    }

}
