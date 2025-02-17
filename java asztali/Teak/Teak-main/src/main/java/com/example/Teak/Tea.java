package com.example.carproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tea {
    private String termek;
    private String leiras;
    private int ar;

    public Tea(String termek, String leiras, int ar){
        this.termek= termek;
        this.leiras= leiras;
        this.ar= ar;
    }

    public String getTermek() {
        return termek;
    }

    public int getAr() {
        return ar;
    }

    public String getLeiras() {
        return leiras;
    }

    public static void saveToFile(String fileName, ObservableList<Tea> teak) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for (Tea tea : teak) {
            //System.out.println(car.licensePlateNumber+","+car.name+","+ car.year);
            bw.write(tea.termek+","+tea.leiras+","+ tea.ar);
            bw.newLine();
        }
        bw.close();
    }

    public static ObservableList<Tea> readFromFile(String fileName) throws IOException {
        Scanner s = new Scanner(new File(fileName));
        ObservableList<Tea> teak = FXCollections.observableArrayList();
        while(s.hasNextLine()){
            String[] parts= s.nextLine().split(",");
            teak.add(new Tea(parts[0],parts[1],Integer.parseInt(parts[2])));
        }
        s.close();
        return teak;
    }
}
