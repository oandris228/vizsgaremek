package com.example.Teak;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TeaForm extends GridPane {
    private Scene scene = new Scene(this);

    private Button backButton = new Button("Back");

    public TeaForm() {
        setHgap(10);
        setVgap(10);
        setPadding(new javafx.geometry.Insets(20, 20, 20, 20));

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

        Label typeLabel = new Label("Type:");
        TextField typeField = new TextField();
        add(typeLabel, 0, 3);
        add(typeField, 1, 3);

        Label flavorLabel = new Label("Flavor:");
        TextField flavorField = new TextField();
        add(flavorLabel, 0, 4);
        add(flavorField, 1, 4);

        Label colorLabel = new Label("Color:");
        TextField colorField = new TextField();
        add(colorLabel, 0, 5);
        add(colorField, 1, 5);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            String name = nameField.getText();
            int price = Integer.parseInt(priceField.getText());
            String type = typeField.getText();
            String flavor = flavorField.getText();
            String color = colorField.getText();

            if (name.isEmpty() || price <= 0 || type.isEmpty() || flavor.isEmpty() || color.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Please fill all fields!");
                a.showAndWait();
                return;
            }

            try {
                new Tea(name, price, type, flavor, color);
            } catch (Exception ex) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Failed to create tea");
                a.showAndWait();
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
