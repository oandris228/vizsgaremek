package com.example.Teak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class OtherTable {
    public ObservableList<Other> others;

    public OtherTable() {
        others = FXCollections.observableArrayList();
    }

    public Scene createScene(EventHandler backEventHandler) {
        // táblázat
        TableColumn<Other, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Other, String> descriptionColumn = new TableColumn<>("description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Other, String> imgColumn = new TableColumn<>("img");
        imgColumn.setCellValueFactory(new PropertyValueFactory<>("img"));

        TableColumn<Other, Integer> productIdColumn = new TableColumn<>("productId");
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));

        TableView<Other> table = new TableView<>();
        table.getColumns().addAll(idColumn, descriptionColumn, imgColumn,productIdColumn);
        table.setItems(others);

        // új other hozzáadás sor
        TextField idInput = new TextField();
        idInput.setPromptText("id");

        TextField descriptionInput = new TextField();
        descriptionInput.setPromptText("description");

        TextField imgInput = new TextField();
        imgInput.setPromptText("img");

        TextField productIdInput = new TextField();
        productIdInput.setPromptText("productId");

        Button addButton = new Button("Add Other");
        addButton.setOnAction(e -> {
            int id;
            String description;
            String img;
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
            description = descriptionInput.getText();
            img = imgInput.getText();
            try {
                productId = Integer.parseInt(productIdInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani az productId-t számmá!");
                a.showAndWait();
                return;
            }

            Other newTea = new Other(id, description, img, productId);

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

            others.add(newTea);

            idInput.clear();
            descriptionInput.clear();
            imgInput.clear();
            productIdInput.clear();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(backEventHandler);

        HBox addLayout = new HBox(idInput, descriptionInput, imgInput, productIdInput, addButton);
        VBox vbox = new VBox(backButton, table, addLayout);
        return new Scene(vbox, 600, 400);
    }
}
