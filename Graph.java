package com.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Graph {

    /*Daten werden von der Quelle gelesen und als Buffer zur Weiterverarbeitung weitergeben.*/
    /****************************************************************************************/

    public BufferedReader readFromSource() throws IOException {

        URL url = new URL("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv");
        URLConnection connection = url.openConnection();
        return new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }

    /*Hier wird eine Arrayliste mit allen Staaten generiert.*/
    /********************************************************/

    public ArrayList<String> getCountryNameFromBuffer() throws IOException {
        String line;
        String csvSplitBy = ",";
        BufferedReader buffer = readFromSource();
        ArrayList<String> countryNames = new ArrayList<>();
        while ((line = buffer.readLine()) != null) {
            String[] room = line.split(csvSplitBy);
            countryNames.add(room[1]);
        }
        buffer.close();
        return countryNames;
    }

    /* Hier werden die Infektionszahlen für einen vorgegebenen Staat in eine ArrayList gespeichert*/
    /**********************************************************************************************/

    public ArrayList<Integer> getDailyInfectionsFromBuffer(String countryName) throws IOException {
        String line;
        String csvSplitBy = ",";
        BufferedReader buffer = readFromSource();
        ArrayList<Integer> dailyInfections = new ArrayList<>();
        while ((line = buffer.readLine()) != null){
            String[] room = line.split(csvSplitBy);
            if(room[1].equals(countryName)){
                for(int i = 4; i <= room.length-1;i++){
                    dailyInfections.add(Integer.parseInt(room[i]));
                }
            }
        }
        buffer.close();
        return dailyInfections;
    }

    /*Hier werden die generierten ArrayListen im Terminal ausgegeben*/
    /****************************************************************/

    public void outputData(String countryName) throws IOException {
        ArrayList<String> countryNames = getCountryNameFromBuffer();
        ArrayList<Integer> dailyInfections = getDailyInfectionsFromBuffer(countryName);
        countryNames.forEach(System.out::println);
        dailyInfections.forEach(System.out::println);
    }
}
