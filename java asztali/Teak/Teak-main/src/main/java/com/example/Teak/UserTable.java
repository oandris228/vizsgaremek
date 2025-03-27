package com.example.Teak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserTable {
    public ObservableList<User> users;

    public UserTable() {
        users = FXCollections.observableArrayList();
    }

    public Scene createScene(EventHandler backEventHandler) {
        // táblázat
        TableColumn<User, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, Integer> emailColumn = new TableColumn<>("email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User, Enum> passwordColumn = new TableColumn<>("password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<User, Integer> shipping_addressColumn = new TableColumn<>("shipping_address");
        shipping_addressColumn.setCellValueFactory(new PropertyValueFactory<>("shipping_address"));

        TableColumn<User, Integer> adminColumn = new TableColumn<>("admin");
        adminColumn.setCellValueFactory(new PropertyValueFactory<>("admin"));

        TableView<User> table = new TableView<>();
        table.getColumns().addAll(idColumn, usernameColumn, emailColumn, passwordColumn, shipping_addressColumn, adminColumn);
        table.setItems(users);

        // új tea hozzáadás sor
        TextField idInput = new TextField();
        idInput.setPromptText("id");

        TextField usernameiInput = new TextField();
        usernameiInput.setPromptText("username");

        TextField emailInput = new TextField();
        emailInput.setPromptText("email");

        TextField passwordInput = new TextField();
        passwordInput.setPromptText("password");

        TextField shipping_addressInput = new TextField();
        shipping_addressInput.setPromptText("shipping_address");

        TextField adminInput = new TextField();
        adminInput.setPromptText("admin");

        Button addButton = new Button("Add User");
        addButton.setOnAction(e -> {
            int id;
            String username;
            int email;
            enum password;
            int shipping_address;
            int admin;

            try {
                id = Integer.parseInt(idInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani az id-t számmá!");
                a.showAndWait();
                return;
            }
            username = usernameiInput.getText();
            password = passwordInput.getText();

            try {
                email = Integer.parseInt(emailInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani a email-t számmá!");
                a.showAndWait();
                return;
            }

            try {
                shipping_address = Integer.parseInt(shipping_addressInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani az shipping_address-t számmá!");
                a.showAndWait();
                return;
            }

            try {
                admin = Integer.parseInt(adminInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani a admin-t számmá!");
                a.showAndWait();
                return;
            }

            User newUser = new User(id, username, email, password, shipping_address, admin);

            try {
                newUser.upload();
            } catch (Exception exception) {
                exception.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült feltölteni az új termeket!");
                a.showAndWait();
                return;
            }

            users.add(newUser);

            idInput.clear();
            usernameiInput.clear();
            emailInput.clear();
            passwordInput.clear();
            shipping_addressInput.clear();
            adminInput.clear();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(backEventHandler);

        HBox addLayout = new HBox(idInput, usernameiInput, emailInput, passwordInput, shipping_addressInput, adminInput, addButton);
        VBox vbox = new VBox(backButton, table, addLayout);
        return new Scene(vbox, 600, 400);
    }
}
