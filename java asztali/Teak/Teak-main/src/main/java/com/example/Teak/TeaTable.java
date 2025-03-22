package com.example.Teak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class TeaTable {
    public ObservableList<Tea> teas;

    public TeaTable() {
        teas = FXCollections.observableArrayList();
    }

    public Scene createScene(EventHandler backEventHandler) {
        // táblázat
        TableColumn<Tea, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Tea, String> typeColumn = new TableColumn<>("type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Tea, String> flavorColumn = new TableColumn<>("flavor");
        flavorColumn.setCellValueFactory(new PropertyValueFactory<>("flavor"));

        TableColumn<Tea, Integer> productIdColumn = new TableColumn<>("productId");
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));

        TableView<Tea> table = new TableView<>();
        table.getColumns().addAll(idColumn, typeColumn, flavorColumn,productIdColumn);
        table.setItems(teas);

        // új tea hozzáadás sor
        TextField idInput = new TextField();
        idInput.setPromptText("id");

        TextField typeiInput = new TextField();
        typeiInput.setPromptText("type");

        TextField flavorInput = new TextField();
        flavorInput.setPromptText("flavor");

        TextField productIdInput = new TextField();
        productIdInput.setPromptText("productId");

        Button addButton = new Button("Add Tea");
        addButton.setOnAction(e -> {
            int id;
            String type;
            String flavor;
            int productId;

            try {
                id = Integer.parseInt(idInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani az id-t számmá!");
                a.showAndWait();
                return;
            }
            type = typeiInput.getText();
            flavor = flavorInput.getText();
            try {
                productId = Integer.parseInt(productIdInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani az productId-t számmá!");
                a.showAndWait();
                return;
            }

            Tea newTea = new Tea(id, type, flavor, productId);

            try {
                newTea.upload();
            } catch (Exception exception) {
                exception.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült feltölteni az új teát!");
                a.showAndWait();
                return;
            }

            teas.add(newTea);

            idInput.clear();
            typeiInput.clear();
            flavorInput.clear();
            productIdInput.clear();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(backEventHandler);

        HBox addLayout = new HBox(idInput, typeiInput, flavorInput, productIdInput, addButton);
        VBox vbox = new VBox(backButton, table, addLayout);
        return new Scene(vbox, 600, 400);
    }
}
