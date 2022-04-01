package com.example.assignment2;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XMLStatWriter extends AirlineData {

    public static void WriteStatToXML(ArrayList<String> headings) throws IOException {
        CSVReader reader = new CSVReader();
        ArrayList<ArrayList<String>> data;

        // Get the CSV data
        data = reader.ReadCSV();

        ArrayList<ArrayList<String>> statData = DataSum.getData(data);

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Root Element of XML File
            Element root = doc.createElement("Summary");
            doc.appendChild(root);

            for (int i = 0; i < statData.get(0).size(); i++) {
                Element stat = doc.createElement("Stat");
                root.appendChild(stat);

                Element name = doc.createElement("Name");
                name.appendChild(doc.createTextNode(headings.get(i + 1)));
                stat.appendChild(name);

                Element min = doc.createElement("Min");
                min.appendChild(doc.createTextNode(statData.get(0).get(i)));
                stat.appendChild(min);

                Element max = doc.createElement("Max");
                max.appendChild(doc.createTextNode(statData.get(1).get(i)));
                stat.appendChild(max);

                Element avg = doc.createElement("Avg");
                avg.appendChild(doc.createTextNode(statData.get(2).get(i)));
                stat.appendChild(avg);

            }

            Element stat2b = doc.createElement("Stat");
            root.appendChild(stat2b);

            Element name2b = doc.createElement("Name");
            name2b.appendChild(doc.createTextNode(headings.get(2)));
            stat2b.appendChild(name2b);

//            Element min2b = doc.createElement("Min");
//            min2b.appendChild(doc.createTextNode(""));
//            stat2b.appendChild(min2b);
//
//            Element max2b = doc.createElement("Max");
//            max2b.appendChild(doc.createTextNode(""));
//            stat2b.appendChild(max2b);

            Element avg2b = doc.createElement("Avg");
            avg2b.appendChild(doc.createTextNode(statData.get(2).get(1)));
            stat2b.appendChild(avg2b);

            Element stat2c = doc.createElement("Stat");
            root.appendChild(stat2c);

            Element name2c = doc.createElement("Name");
            name2c.appendChild(doc.createTextNode(headings.get(5)));
            stat2c.appendChild(name2c);

//            Element min2c = doc.createElement("Min");
//            min2c.appendChild(doc.createTextNode(""));
//            stat2c.appendChild(min2c);
//
//            Element max2c = doc.createElement("Max");
//            max2c.appendChild(doc.createTextNode(""));
//            stat2c.appendChild(max2c);

            Element avg2c = doc.createElement("Avg");
            avg2c.appendChild(doc.createTextNode(statData.get(2).get(4)));
            stat2c.appendChild(avg2c);


//            Element avgIncident85To99 = doc.createElement("AvgIncident85To99");
//            avgIncident85To99.appendChild(doc.createTextNode(statData.get(2).get(1)));
//            root.appendChild(avgIncident85To99);
//
//            Element avgIncident00To14 = doc.createElement("AvgIncident00To14");
//            avgIncident00To14.appendChild(doc.createTextNode(statData.get(2).get(4)));
//            root.appendChild(avgIncident00To14);


            // conversion to XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer tf = transformerFactory.newTransformer();

            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty(OutputKeys.METHOD, "xml");

            DOMSource source = new DOMSource(doc);
            //StreamResult result = new StreamResult(new File(path));
            StreamResult result = new StreamResult(new File(path + "airline_summary_statistic.xml"));
            tf.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
