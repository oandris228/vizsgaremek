package com.example.Teak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProductTable {
    public ObservableList<Product> products;

    public ProductTable() {
        products = FXCollections.observableArrayList();
    }

    public Scene createScene(EventHandler backEventHandler) {
        // táblázat
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

        // új tea hozzáadás sor
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
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani az id-t számmá!");
                a.showAndWait();
                return;
            }
            name = nameiInput.getText();

            try {
                category = Product.Category.valueOf(categoryInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani a category-t!");
                a.showAndWait();
                return;
            }

            try {
                price = Integer.parseInt(priceInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani a price-t számmá!");
                a.showAndWait();
                return;
            }

            try {
                other_id = Integer.parseInt(other_idInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani az other_id-t számmá!");
                a.showAndWait();
                return;
            }

            try {
                tea_id = Integer.parseInt(tea_idInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani a tea_id-t számmá!");
                a.showAndWait();
                return;
            }

            Product newProduct = new Product(id, name, price, category, other_id, tea_id);

            try {
                newProduct.upload();
            } catch (Exception exception) {
                exception.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült feltölteni az új termeket!");
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
        return new Scene(vbox, 600, 400);
    }
}
