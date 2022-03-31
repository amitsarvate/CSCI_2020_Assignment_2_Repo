package com.example.lab8;

import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class CSVReader extends AirlineData{
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

    ASSUMPTION - We are only adding up the 2 incident columns. Assuming that the fatal accidents are included within
    the incident counts

     */

    public ArrayList<ArrayList<String>> ReadCSV() throws IOException {
        ArrayList<ArrayList<String>> readData = new ArrayList<>();

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
            String route = this.path + "airline_safety.csv";
            reader = new BufferedReader(new FileReader(route));
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
                }

                count++;

            }

            readData.add(airline);
            readData.add(avail_seat_km_week);
            readData.add(incidents_85_99);
            readData.add(fatal_acc_85_99);
            readData.add(fatalities_85_99);
            readData.add(incidents_00_14);
            readData.add(fatal_acc_00_14);
            readData.add(fatalities_00_14);
            readData.add(total_incid);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(readData);
        Writer csvWriter = null;

        try {
            System.out.println(readData);
            String route2 = this.path + "airline_safety.csv";
            File file = new File(route2);
            csvWriter = new BufferedWriter((new FileWriter(file)));

            // headers
            String line2 = "airline" + "," + "avail_seat_km_per_week" + "," + "incidents_85_99" + "," + "fatal_accidents_85_99" + "," + "fatalities_85_99" + "," + "incidents_00_14" + "," + "fatal_accidents_00_14" + "," + "fatalities_00_14" + "," + "total_incidents" + "\n";
            csvWriter.write(line2);

            for (int i = 0; i < readData.get(0).size(); i++) {
                line2 = readData.get(0).get(i) + "," + readData.get(1).get(i) + "," + readData.get(2).get(i) + "," + readData.get(3).get(i) + "," + readData.get(4).get(i) + "," + readData.get(5).get(i) + "," + readData.get(6).get(i) + "," + readData.get(7).get(i) + "," + readData.get(8).get(i) + "\n";
                csvWriter.write(line2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            csvWriter.flush();
            csvWriter.close();
        }


        return readData;
    }

}
