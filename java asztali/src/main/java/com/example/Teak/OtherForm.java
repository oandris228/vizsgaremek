package com.example.Teak;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OtherForm extends GridPane {
    private Scene scene = new Scene(this);

    private Button backButton = new Button("Back");

    public OtherForm() {
        scene.getStylesheets().add(getClass().getResource("form.css").toExternalForm());
        getStyleClass().add("grid");

        add(backButton, 0, 0);
        setColumnSpan(backButton, 2);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        add(nameLabel, 0, 1);
        add(nameField, 1, 1);

        Label priceLabel = new Label("Price:");
        TextField priceField = new TextField();
        priceField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                priceField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        add(priceLabel, 0, 2);
        add(priceField, 1, 2);

        Label descriptionLabel = new Label("Description:");
        TextField descriptionField = new TextField();
        add(descriptionLabel, 0, 3);
        add(descriptionField, 1, 3);

        Label imgLabel = new Label("Image URL:");
        TextField imgField = new TextField();
        add(imgLabel, 0, 4);
        add(imgField, 1, 4);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            String name = nameField.getText();
            int price = Integer.parseInt(priceField.getText());
            String description = descriptionField.getText();
            String img = imgField.getText();

            if (name.isEmpty() || price <= 0 || description.isEmpty() || img.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Please fill all fields!");
                a.showAndWait();
                return;
            }

            try {
                new Other(name, price, description, img);
                Alert successAlert = new Alert(Alert.AlertType.CONFIRMATION);
                successAlert.setTitle("Success");
                successAlert.setContentText("User saved successfully!");
                successAlert.showAndWait();
            } catch (Exception ex) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Failed to create other");
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
