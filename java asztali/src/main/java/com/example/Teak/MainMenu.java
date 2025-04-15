package com.example.Teak;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(20, 20, 20, 20));

        add(userTableButton,0,0);
        add(userFormButton,0,1);
        add(teaTableButton,0,2);
        add(teaFormButton,0,3);
        add(otherTableButton, 0, 4);
        add(otherFormButton, 0, 5);
        add(commissionTableButton, 0, 6);
        add(commissionFormButton, 0, 7);
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
