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

public class TeaTable {
    public ObservableList<Tea> teas;

    public TeaTable() {
        teas = FXCollections.observableArrayList();
    }

    public Scene createScene(EventHandler backEventHandler) {
        // Table setup
        TableColumn<Tea, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Tea, String> typeColumn = new TableColumn<>("type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Tea, String> flavorColumn = new TableColumn<>("flavor");
        flavorColumn.setCellValueFactory(new PropertyValueFactory<>("flavor"));

        TableColumn<Tea, Integer> productIdColumn = new TableColumn<>("productId");
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));

        TableColumn<Tea, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellFactory(param -> new TableCell<Tea, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Tea tea = getTableView().getItems().get(getIndex());
                    teas.remove(tea);
                    // You can also add logic here to delete from file/db
                    System.out.println("Deleted: " + tea);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        TableView<Tea> table = new TableView<>();
        table.getColumns().addAll(idColumn, typeColumn, flavorColumn, productIdColumn, actionColumn);
        table.setItems(teas);

        // Input fields
        TextField idInput = new TextField();
        idInput.setPromptText("id");

        TextField typeInput = new TextField();
        typeInput.setPromptText("type");

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
                a.setTitle("Error");
                a.setContentText("Failed to convert id to a number!");
                a.showAndWait();
                return;
            }

            type = typeInput.getText();
            if (type.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("The type field cannot be empty!");
                a.showAndWait();
                return;
            }

            flavor = flavorInput.getText();
            if (flavor.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("The flavor field cannot be empty!");
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

            Tea newTea = new Tea(id, type, flavor, productId);

            try {
                newTea.upload();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Database Error");
                a.setContentText("Failed to upload the new tea due to a database error!");
                a.showAndWait();
                return;
            } catch (Exception exception) {
                exception.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("An unexpected error occurred while uploading the new tea!");
                a.showAndWait();
                return;
            }

            teas.add(newTea);

            idInput.clear();
            typeInput.clear();
            flavorInput.clear();
            productIdInput.clear();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(backEventHandler);

        HBox addLayout = new HBox(idInput, typeInput, flavorInput, productIdInput, addButton);
        VBox vbox = new VBox(backButton, table, addLayout);
        Scene scene = new Scene(vbox, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        return scene;
    }
}