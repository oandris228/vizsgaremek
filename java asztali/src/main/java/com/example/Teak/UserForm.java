package com.example.Teak;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UserForm extends GridPane {
    private Scene scene = new Scene(this);

    private Button backButton = new Button("Back");

    public UserForm() {
        scene.getStylesheets().add(getClass().getResource("form.css").toExternalForm());
        getStyleClass().add("grid");

        add(backButton, 0, 0);
        setColumnSpan(backButton, 2);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        add(usernameLabel, 0, 1);
        add(usernameField, 1, 1);

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        add(emailLabel, 0, 2);
        add(emailField, 1, 2);

        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();
        add(passwordLabel, 0, 3);
        add(passwordField, 1, 3);

        Label shippingAddressLabel = new Label("Shipping Address:");
        TextField shippingAddressField = new TextField();
        add(shippingAddressLabel, 0, 4);
        add(shippingAddressField, 1, 4);

        Label roleLabel = new Label("Role:");
        ComboBox<User.Role> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().setAll(User.Role.values());
        add(roleLabel, 0, 5);
        add(roleComboBox, 1, 5);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String shippingAddress = shippingAddressField.getText();
            User.Role role = roleComboBox.getValue();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || shippingAddress.isEmpty() || role == null) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Please fill all fields!");
                a.showAndWait();
                return;
            }

            try {
                new User(username, email, password, shippingAddress, role);
            } catch (Exception ex) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Failed to create user");
                a.showAndWait();
                return;
            }
        });
        add(saveButton, 0, 6);
        setColumnSpan(saveButton, 2);
    }

    public void setBackButtonEventHandler(EventHandler<ActionEvent> eventHandler) {
        backButton.setOnAction(eventHandler);
    }

    public void switchTo(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
