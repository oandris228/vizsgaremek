package com.example.Teak;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class MainMenu extends GridPane {
    private Scene scene = new Scene(this);

    private Button userTableButton = new Button("Users");
    private Button userFormButton = new Button("User Form");
    private Button teaTableButton = new Button("Teas");
    private Button teaFormButton = new Button("Tea Form");
    private Button otherTableButton = new Button("Others");
    private Button otherFormButton = new Button("Other Form");
    private Button commissionTableButton = new Button("Commissions");
    private Button commissionFormButton = new Button("Commission Form");

    public MainMenu() {
        scene.getStylesheets().add(this.getClass().getResource("mainMenu.css").toExternalForm());

        getStyleClass().add("grid");

        // Set column constraints for responsiveness
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS); // Allow column 1 to grow
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS); // Allow column 2 to grow
        getColumnConstraints().addAll(col1, col2);

        // Set buttons to grow and fill available space
        userTableButton.setMaxWidth(Double.MAX_VALUE);
        userFormButton.setMaxWidth(Double.MAX_VALUE);
        teaTableButton.setMaxWidth(Double.MAX_VALUE);
        teaFormButton.setMaxWidth(Double.MAX_VALUE);
        otherTableButton.setMaxWidth(Double.MAX_VALUE);
        otherFormButton.setMaxWidth(Double.MAX_VALUE);
        commissionTableButton.setMaxWidth(Double.MAX_VALUE);
        commissionFormButton.setMaxWidth(Double.MAX_VALUE);

        // Add buttons to the grid
        add(userTableButton, 0, 0);
        add(userFormButton, 1, 0);
        add(teaTableButton, 0, 1);
        add(teaFormButton, 1, 1);
        add(otherTableButton, 0, 2);
        add(otherFormButton, 1, 2);
        add(commissionTableButton, 0, 3);
        add(commissionFormButton, 1, 3);
    }

    public Scene getSceneOf() {
        return scene;
    }

    public void setUserTableButtonEventHandler(EventHandler<ActionEvent> eventHandler) {
        userTableButton.setOnAction(eventHandler);
    }

    public void setUserFormButtonEventHandler(EventHandler<ActionEvent> eventHandler) {
        userFormButton.setOnAction(eventHandler);
    }

    public void setTeaTableButtonEventHandler(EventHandler<ActionEvent> eventHandler) {
        teaTableButton.setOnAction(eventHandler);
    }

    public void setTeaFormButtonEventHandler(EventHandler<ActionEvent> eventHandler) {
        teaFormButton.setOnAction(eventHandler);
    }

    public void setOtherTableButtonEventHandler(EventHandler<ActionEvent> eventHandler) {
        otherTableButton.setOnAction(eventHandler);
    }

    public void setOtherFormButtonEventHandler(EventHandler<ActionEvent> eventHandler) {
        otherFormButton.setOnAction(eventHandler);
    }

    public void setCommissionTableButtonEventHandler(EventHandler<ActionEvent> eventHandler) {
        commissionTableButton.setOnAction(eventHandler);
    }

    public void setCommissionFormButtonEventHandler(EventHandler<ActionEvent> eventHandler) {
        commissionFormButton.setOnAction(eventHandler);
    }

    public void switchTo(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}