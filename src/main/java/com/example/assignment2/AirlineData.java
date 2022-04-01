package com.example.assignment2;

import java.io.IOException;
import java.util.ArrayList;

public class AirlineData {
//    public ArrayList<ArrayList<String>> data = new ArrayList<>();
    public int numRecords = 0;

    public static String path = "/Users/amit.sar21/University/Second Year/Semester 2 /Systems Development and Integration - CSCI 2020/assignment/assignment-2-amitsarvate/src/main/resources/com/example/assignment2/";
//    public static String path = "C:\\Users\\amand\\IdeaProjects\\lab8\\src\\main\\resources\\com\\example\\lab8\\";
    public ArrayList<String> headings = new ArrayList<>();

    public void assignment2() throws IOException {
        // Add the csv file name to path
        //String csv = this.path + "airline_safety.csv";

        CSVReader reader = new CSVReader();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        // Get the CSV data
        data = reader.ReadCSV();

        // Add column to CSV
        //reader.addColumn();

        // Add data to the headings array
        this.headings.add("name");
        this.headings.add("avail_seat_km_week");
        this.headings.add("incident_85_99");
        this.headings.add("fatal_acc_85_99");
        this.headings.add("fatalities_85_99");
        this.headings.add("incident_00_14");
        this.headings.add("fatal_acc_00_14");
        this.headings.add("fatalities_00_14");
        this.headings.add("total_incidents");

        // Convert CSV file to XML
        // XMLConverter convert = new XMLConverter();
        // convert.createXML();
        XMLConverter.createXML(this.headings);

        // Output Stats to XML file
        //XMLStatWriter stat = new XMLStatWriter();
        XMLStatWriter.WriteStatToXML(this.headings);
    }

    public static void main(String[] args) throws IOException {
        AirlineData airline2 = new AirlineData();
        airline2.assignment2();
    }
}
