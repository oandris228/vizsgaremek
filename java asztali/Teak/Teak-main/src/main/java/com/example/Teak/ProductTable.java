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

public class ProductTable {
    public ObservableList<Product> products;

    public ProductTable() {
        products = FXCollections.observableArrayList();
    }

    public Scene createScene(EventHandler backEventHandler) {
        // Table setup
        TableColumn<Product, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Product, String> nameColumn = new TableColumn<>("name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Integer> priceColumn = new TableColumn<>("price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Product.Category> categoryColumn = new TableColumn<>("category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Product, Integer> other_idColumn = new TableColumn<>("other_id");
        other_idColumn.setCellValueFactory(new PropertyValueFactory<>("other_id"));

        TableColumn<Product, Integer> tea_idColumn = new TableColumn<>("tea_id");
        tea_idColumn.setCellValueFactory(new PropertyValueFactory<>("tea_id"));

        TableView<Product> table = new TableView<>();
        table.getColumns().addAll(idColumn, nameColumn, priceColumn, categoryColumn, other_idColumn, tea_idColumn);
        table.setItems(products);

        // Input fields
        TextField idInput = new TextField();
        idInput.setPromptText("id");

        TextField nameiInput = new TextField();
        nameiInput.setPromptText("name");

        TextField priceInput = new TextField();
        priceInput.setPromptText("price");

        TextField categoryInput = new TextField();
        categoryInput.setPromptText("category");

        TextField other_idInput = new TextField();
        other_idInput.setPromptText("other_id");

        TextField tea_idInput = new TextField();
        tea_idInput.setPromptText("tea_id");

        Button addButton = new Button("Add Product");
        addButton.setOnAction(e -> {
            int id;
            String name;
            int price;
            Product.Category category;
            int other_id;
            int tea_id;

            try {
                id = Integer.parseInt(idInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Failed to convert id to a number!");
                a.showAndWait();
                return;
            }

            name = nameiInput.getText();
            if (name.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("The name field cannot be empty!");
                a.showAndWait();
                return;
            }

            try {
                category = Product.Category.valueOf(categoryInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Failed to convert category to a valid value!");
                a.showAndWait();
                return;
            }

            try {
                price = Integer.parseInt(priceInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Failed to convert price to a number!");
                a.showAndWait();
                return;
            }

            try {
                other_id = Integer.parseInt(other_idInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Failed to convert other_id to a number!");
                a.showAndWait();
                return;
            }

            try {
                tea_id = Integer.parseInt(tea_idInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Failed to convert tea_id to a number!");
                a.showAndWait();
                return;
            }

            Product newProduct = new Product(id, name, price, category, other_id, tea_id);

            try {
                newProduct.upload();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Database Error");
                a.setContentText("Failed to upload the new product due to a database error!");
                a.showAndWait();
                return;
            } catch (Exception exception) {
                exception.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("An unexpected error occurred while uploading the new product!");
                a.showAndWait();
                return;
            }

            products.add(newProduct);

            idInput.clear();
            nameiInput.clear();
            priceInput.clear();
            categoryInput.clear();
            other_idInput.clear();
            tea_idInput.clear();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(backEventHandler);

        HBox addLayout = new HBox(idInput, nameiInput, priceInput, categoryInput, other_idInput, tea_idInput, addButton);
        VBox vbox = new VBox(backButton, table, addLayout);
        Scene scene = new Scene(vbox, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        return scene;
    }
}