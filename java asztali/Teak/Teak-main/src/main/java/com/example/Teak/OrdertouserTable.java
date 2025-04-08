package com.example.Teak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class OrdertouserTable {
    public ObservableList<Ordertouser> ordertousers;

    public OrdertouserTable() {
        ordertousers = FXCollections.observableArrayList();
    }

    public Scene createScene(EventHandler backEventHandler) {
        // táblázat
        TableColumn<Ordertouser, Integer> AColumn = new TableColumn<>("A");
        AColumn.setCellValueFactory(new PropertyValueFactory<>("A"));

        TableColumn<Ordertouser, Integer> BColumn = new TableColumn<>("B");
        BColumn.setCellValueFactory(new PropertyValueFactory<>("B"));

        TableView<Ordertouser> table = new TableView<>();
        table.getColumns().addAll(AColumn, BColumn);
        table.setItems(ordertousers);

        // új tea hozzáadás sor
        TextField AInput = new TextField();
        AInput.setPromptText("A");

        TextField BInput = new TextField();
        BInput.setPromptText("B");

        Button addButton = new Button("Add Ordertouser");
        addButton.setOnAction(e -> {
            int A;
            int B;

            try {
                A = Integer.parseInt(AInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani az A-t számmá!");
                a.showAndWait();
                return;
            }

            try {
                B = Integer.parseInt(BInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani az B-t számmá!");
                a.showAndWait();
                return;
            }

            Ordertouser newOrdertouser = new Ordertouser(A,B);

            try {
                newOrdertouser.upload();
            } catch (Exception exception) {
                exception.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült feltölteni az új teát!");
                a.showAndWait();
                return;
            }

            ordertousers.add(newOrdertouser);

            AInput.clear();
            BInput.clear();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(backEventHandler);

        HBox addLayout = new HBox(AInput, BInput, addButton);
        VBox vbox = new VBox(backButton, table, addLayout);
        return new Scene(vbox, 600, 400);
    }
}
