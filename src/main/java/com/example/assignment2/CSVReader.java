package com.example.assignment2;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CSVReader {

    private ArrayList<ArrayList<String>> data = new ArrayList<>();
    private int numRecords = 0;

    /*
    Create an array list of array list

    0. airline
    1. avail_seat_km_week
    2. incidents (from 85-99)
    3. fatal accidents (from 85-89)
    4. fatalities (from 85-99)
    5. incidents (from 00-14)
    6. fatal accidents (from 00-14)
    7. fatalities (from 00-14)

    8. sum of incidents over 30 years


     */

    public CSVReader() {
        this.data = getData();
    }

    public void ReadCSV() {
        String file = "/Users/amit.sar21/University/Second Year/Semester 2 /Systems Development and Integration - CSCI 2020/assignment/assignment-2-amitsarvate/src/main/resources/com/example/assignment2/airline_safety.csv";
        BufferedReader reader = null;
        String line = "";
        int count = 0;

        ArrayList<String> airline = new ArrayList<String>();
        ArrayList<String> avail_seat_km_week = new ArrayList<String>();
        ArrayList<String> incidents_85_99 = new ArrayList<String>();
        ArrayList<String> fatal_acc_85_99 = new ArrayList<String>();
        ArrayList<String> fatalities_85_99 = new ArrayList<String>();
        ArrayList<String> incidents_00_14 = new ArrayList<String>();
        ArrayList<String> fatal_acc_00_14 = new ArrayList<String>();
        ArrayList<String> fatalities_00_14 = new ArrayList<String>();
        ArrayList<String> total_incid = new ArrayList<String>();

        try {
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null) {
                String row[] = line.split(",");

                if (count > 0) {
                    airline.add(row[0]);
                    avail_seat_km_week.add(row[1]);
                    incidents_85_99.add(row[2]);
                    fatal_acc_85_99.add(row[3]);
                    fatalities_85_99.add(row[4]);
                    incidents_00_14.add(row[5]);
                    fatal_acc_00_14.add(row[6]);
                    fatalities_00_14.add(row[7]);
                    int total = (Integer.parseInt(row[2]) + Integer.parseInt(row[5]));
                    total_incid.add(Integer.toString(total));

                    this.numRecords++;
                }

                count++;

            }

            this.data.add(airline);
            this.data.add(avail_seat_km_week);
            this.data.add(incidents_85_99);
            this.data.add(fatal_acc_85_99);
            this.data.add(fatalities_85_99);
            this.data.add(incidents_00_14);
            this.data.add(fatal_acc_00_14);
            this.data.add(fatalities_00_14);
            this.data.add(total_incid);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<ArrayList<String>> getData() {
        /*
        1. Read columns from the CSV (ReadCSV())
        2. Add The 2 and 5th index to get Total Incidents Column

         */
        this.ReadCSV();
        return this.data;
    }

    // FUNCTION TO WRITE NEW COL TO CSV 
//    public void addCol() throws Exception {
//        Writer csvWriter = null;
//        try {
//            File file = new File("/Users/amit.sar21/University/Second Year/Semester 2 /Systems Development and Integration - CSCI 2020/assignment/assignment-2-amitsarvate/src/main/resources/com/example/assignment2/airline_safety.csv");
//            csvWriter = new BufferedWriter((new FileWriter(file)));
//
////            String line = student
//
////            for(String sum : this.data.get(8)) {
////            }
//        }
//
//    }

    public static void main(String[] args) {
        CSVReader reader = new CSVReader();

        ArrayList<String> in1 = reader.getData().get(8);

        for (String index : in1) {
            System.out.println(index);
        }


    }


}
