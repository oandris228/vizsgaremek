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

public class UserTable {
    public ObservableList<User> users;

    public UserTable() {
        users = FXCollections.observableArrayList();
    }

    public Scene createScene(EventHandler backEventHandler) {
        // Table columns
        TableColumn<User, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> emailColumn = new TableColumn<>("email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User, String> passwordColumn = new TableColumn<>("password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<User, String> shipping_addressColumn = new TableColumn<>("shipping_address");
        shipping_addressColumn.setCellValueFactory(new PropertyValueFactory<>("shipping_address"));

        TableColumn<User, User.Role> roleColumn = new TableColumn<>("role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableView<User> table = new TableView<>();
        table.getColumns().addAll(idColumn, usernameColumn, emailColumn, passwordColumn, shipping_addressColumn, roleColumn);
        table.setItems(users);

        // Input fields for adding a new user
        TextField idInput = new TextField();
        idInput.setPromptText("id");

        TextField usernameInput = new TextField();
        usernameInput.setPromptText("username");

        TextField emailInput = new TextField();
        emailInput.setPromptText("email");

        TextField passwordInput = new TextField();
        passwordInput.setPromptText("password");

        TextField shipping_addressInput = new TextField();
        shipping_addressInput.setPromptText("shipping_address");

        TextField roleInput = new TextField();
        roleInput.setPromptText("role");

        Button addButton = new Button("Add User");
        addButton.setOnAction(e -> {
            int id;
            String username;
            String email;
            String password;
            String shipping_address;
            User.Role role;

            try {
                id = Integer.parseInt(idInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Failed to convert id to a number!");
                a.showAndWait();
                return;
            }

            username = usernameInput.getText();
            if (username.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("The username field cannot be empty!");
                a.showAndWait();
                return;
            }

            password = passwordInput.getText();
            if (password.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("The password field cannot be empty!");
                a.showAndWait();
                return;
            }

            email = emailInput.getText();
            if (email.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("The email field cannot be empty!");
                a.showAndWait();
                return;
            }

            shipping_address = shipping_addressInput.getText();
            if (shipping_address.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("The shipping address field cannot be empty!");
                a.showAndWait();
                return;
            }

            try {
                role = User.Role.valueOf(roleInput.getText().toUpperCase());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Failed to convert role to a valid value!");
                a.showAndWait();
                return;
            }

            User newUser = new User(id, username, email, password, shipping_address, role);

            try {
                newUser.upload();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Database Error");
                a.setContentText("Failed to upload the new user due to a database error!");
                a.showAndWait();
                return;
            } catch (Exception exception) {
                exception.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("An unexpected error occurred while uploading the new user!");
                a.showAndWait();
                return;
            }

            users.add(newUser);

            idInput.clear();
            usernameInput.clear();
            emailInput.clear();
            passwordInput.clear();
            shipping_addressInput.clear();
            roleInput.clear();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(backEventHandler);

        HBox addLayout = new HBox(idInput, usernameInput, emailInput, passwordInput, shipping_addressInput, roleInput, addButton);
        VBox vbox = new VBox(backButton, table, addLayout);
        Scene scene = new Scene(vbox, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        return scene;
    }
}