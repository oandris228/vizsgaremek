package com.example.Teak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OrderTable {
    public ObservableList<Order> orders;

    public OrderTable() {
        orders = FXCollections.observableArrayList();
    }

    public Scene createScene(EventHandler backEventHandler) {
        // táblázat
        TableColumn<Order, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Order, Integer> user_idColumn = new TableColumn<>("user_id");
        user_idColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));

        TableColumn<Order, String> shipping_addressColumn = new TableColumn<>("shipping_address");
        shipping_addressColumn.setCellValueFactory(new PropertyValueFactory<>("shipping_address"));

        TableColumn<Order, String> cartColumn = new TableColumn<>("cart");
        cartColumn.setCellValueFactory(new PropertyValueFactory<>("cart"));

        TableView<Order> table = new TableView<>();
        table.getColumns().addAll(idColumn, user_idColumn, shipping_addressColumn,cartColumn);
        table.setItems(orders);

        // új tea hozzáadás sor
        TextField idInput = new TextField();
        idInput.setPromptText("id");

        TextField user_idiInput = new TextField();
        user_idiInput.setPromptText("user_id");

        TextField shipping_addressInput = new TextField();
        shipping_addressInput.setPromptText("shipping_address");

        TextField cartInput = new TextField();
        cartInput.setPromptText("cart");

        Button addButton = new Button("Add Order");
        addButton.setOnAction(e -> {
            int id;
            int user_id;
            String shipping_address;
            String cart;

            try {
                id = Integer.parseInt(idInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani az id-t számmá!");
                a.showAndWait();
                return;
            }
            cart = cartInput.getText();
            shipping_address = shipping_addressInput.getText();
            try {
                user_id = Integer.parseInt(user_idiInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani az user_id-t számmá!");
                a.showAndWait();
                return;
            }

            Order newOrder = new Order(id, user_id, shipping_address, cart);

            try {
                newOrder.upload();
            } catch (Exception exception) {
                exception.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült feltölteni az új rendelest!");
                a.showAndWait();
                return;
            }

            orders.add(newOrder);

            idInput.clear();
            user_idiInput.clear();
            shipping_addressInput.clear();
            cartInput.clear();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(backEventHandler);

        HBox addLayout = new HBox(idInput, user_idiInput, shipping_addressInput, cartInput, addButton);
        VBox vbox = new VBox(backButton, table, addLayout);
        return new Scene(vbox, 600, 400);
    }
}
