package com.example.Teak;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.time.Year;
import java.util.*;

public class HelloApplication extends Application {
    ObservableList<Tea> teak = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) throws IOException {
        // tábla létrehozása
        TableView<Tea> table = new TableView();

        TableColumn<Tea, String> modelColumn = new TableColumn<>("termek");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("termek"));

        TableColumn<Tea, String> manufacturerColumn = new TableColumn<>("leiras");
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("leiras"));

        TableColumn<Tea, Integer> priceColumn = new TableColumn<>("ar");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("ar"));

        table.getColumns().addAll(modelColumn, manufacturerColumn, priceColumn);

        try {
            teak = Tea.readFromFile("teak.csv");
        } catch (Exception e) {}
        table.setItems(teak);

        // új autó hozzáadása
        TextField termekInput = new TextField();
        termekInput.setPromptText("Termek");

        TextField leirasInput = new TextField();
        leirasInput.setPromptText("Leiras");

        TextField arInput = new TextField();
        arInput.setPromptText("Ar");

        Button addButton = new Button("Add Tea");
        addButton.setOnAction(e -> {
            String termek = termekInput.getText();
            String leiras = leirasInput.getText();
            int ar;

            try {
                ar = Integer.parseInt(arInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani az árat számmá!");
                a.showAndWait();
                return;
            }



            teak.add(new Tea(termek, leiras, ar));

            termekInput.clear();
            leirasInput.clear();
            arInput.clear();
        });

        // mentés betöltés
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Biztos?");
            a.setContentText("Biztos vagy benne? Felül fogod írni a teak.csv fájlt");
            Optional<ButtonType> result = a.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    Tea.saveToFile("teak.csv", teak);
                } catch (Exception exception) {
                    Alert ea = new Alert(Alert.AlertType.ERROR);
                    ea.setContentText("Nem sikerült a fájlba írás");
                    ea.showAndWait();
                }
            }
        });

        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Biztos?");
            a.setContentText("Biztos vagy benne? A nem elmentett teák el fognak veszni");
            Optional<ButtonType> result = a.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    ObservableList<Tea> teak1 = Tea.readFromFile("teak.csv");
                    teak.clear();
                    teak.addAll(teak1);
                } catch (Exception exception) {
                    Alert ea = new Alert(Alert.AlertType.ERROR);
                    ea.setContentText("Nem sikerült a fájlból olvasás");
                    ea.showAndWait();
                }
            }
        });

        HBox inputLayout = new HBox(termekInput, leirasInput, arInput, addButton);
        HBox saveLoadLayout = new HBox(saveButton, loadButton);
        VBox vbox = new VBox(table, inputLayout, saveLoadLayout);
        Scene scene = new Scene(vbox, 600, 400);
        stage.setScene(scene);

        stage.setTitle("Hello!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}