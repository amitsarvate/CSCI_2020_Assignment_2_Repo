package com.example.lab8;

import java.util.ArrayList;

public class DataSum {

    public static ArrayList<ArrayList<String>> getData(ArrayList<ArrayList<String>> data) {

        ArrayList<ArrayList<String>> dataSum = new ArrayList<>();

        // contains minimum values from each of the numeric columns (1 to 8)
        ArrayList<String> minimum = new ArrayList<>();
        ArrayList<String> maximum = new ArrayList<>();
        ArrayList<String> average = new ArrayList<>();

        for (int i = 1; i < data.size(); i++) {
            ArrayList<String> currentArray = data.get(i);

            Long min = Long.MAX_VALUE;
            Long max = Long.MIN_VALUE;
            Float sum = 0f;

            for (int j = 0; j < data.get(i).size(); j++) {
                Long currentVal = Long.parseLong(currentArray.get(j));

                sum = sum + (float)currentVal;

                if(currentVal > max) {
                    max = currentVal;
                }
                if(currentVal < min) {
                    min = currentVal;
                }

            }

            float avg = sum / data.get(i).size();

            String minConv = String.valueOf(min);
            String maxConv = String.valueOf(max);
            String avgConv = String.valueOf(avg);

            minimum.add(minConv);
            maximum.add(maxConv);
            average.add(avgConv);

        }

        dataSum.add(minimum);
        dataSum.add(maximum);
        dataSum.add(average);

        return dataSum;

    }

}
