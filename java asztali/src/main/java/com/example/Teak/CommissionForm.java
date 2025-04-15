package com.example.Teak;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CommissionForm extends GridPane {
    private Scene scene = new Scene(this);

    private Button backButton = new Button("Back");

    public CommissionForm() {
        setHgap(10);
        setVgap(10);
        setPadding(new javafx.geometry.Insets(20, 20, 20, 20));

        add(backButton, 0, 0);
        setColumnSpan(backButton, 2);

        Label userIdLabel = new Label("User ID:");
        TextField userIdField = new TextField();
        userIdField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                userIdField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        add(userIdLabel, 0, 1);
        add(userIdField, 1, 1);

        Label productIdLabel = new Label("Product ID:");
        TextField productIdField = new TextField();
        productIdField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                productIdField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        add(productIdLabel, 0, 2);
        add(productIdField, 1, 2);

        Label quantityLabel = new Label("Quantity:");
        TextField quantityField = new TextField();
        quantityField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                quantityField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        add(quantityLabel, 0, 3);
        add(quantityField, 1, 3);

        Label extratextLabel = new Label("Extra Text:");
        TextField extratextField = new TextField();
        add(extratextLabel, 0, 4);
        add(extratextField, 1, 4);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            int userId;
            int productId;
            int quantity;
            String extratext;
            try {
                userId = Integer.parseInt(userIdField.getText());
                productId = Integer.parseInt(productIdField.getText());
                quantity = Integer.parseInt(quantityField.getText());
                extratext = extratextField.getText();
                if (userId <= 0 || productId <= 0 || quantity <= 0 || extratext.isEmpty())
                    throw new Exception();

            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Please fill out the form correctly!");
                a.showAndWait();
                return;
            }

            try {
                new Commission(userId, productId, quantity, extratext);
            } catch (Exception ex) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Failed to create commission");
                a.showAndWait();
            }
        });
        add(saveButton, 0, 5);
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
