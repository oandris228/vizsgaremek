package com.example.Teak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class OtherTable {
    public ObservableList<Other> others;

    public OtherTable() {
        others = FXCollections.observableArrayList();
    }

    public Scene createScene(EventHandler backEventHandler) {
        // Table setup
        TableColumn<Other, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Other, String> descriptionColumn = new TableColumn<>("description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Other, String> imgColumn = new TableColumn<>("img");
        imgColumn.setCellValueFactory(new PropertyValueFactory<>("img"));

        TableColumn<Other, Integer> productIdColumn = new TableColumn<>("productId");
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));

        TableView<Other> table = new TableView<>();
        table.getColumns().addAll(idColumn, descriptionColumn, imgColumn, productIdColumn);
        table.setItems(others);

        // Input fields
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
                a.setTitle("Error");
                a.setContentText("Failed to convert id to a number!");
                a.showAndWait();
                return;
            }

            description = descriptionInput.getText();
            if (description.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("The description field cannot be empty!");
                a.showAndWait();
                return;
            }

            img = imgInput.getText();
            if (img.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("The img field cannot be empty!");
                a.showAndWait();
                return;
            }

            try {
                productId = Integer.parseInt(productIdInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Failed to convert productId to a number!");
                a.showAndWait();
                return;
            }

            Other newOther = new Other(id, description, img, productId);

            try {
                newOther.upload();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Database Error");
                a.setContentText("Failed to upload the new other due to a database error!");
                a.showAndWait();
                return;
            } catch (Exception exception) {
                exception.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("An unexpected error occurred while uploading the new other!");
                a.showAndWait();
                return;
            }

            others.add(newOther);

            idInput.clear();
            descriptionInput.clear();
            imgInput.clear();
            productIdInput.clear();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(backEventHandler);

        HBox addLayout = new HBox(idInput, descriptionInput, imgInput, productIdInput, addButton);
        VBox vbox = new VBox(backButton, table, addLayout);

        Scene scene = new Scene(vbox, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        return scene;
    }
}