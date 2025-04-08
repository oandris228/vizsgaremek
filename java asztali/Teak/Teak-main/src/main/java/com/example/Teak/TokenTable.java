package com.example.Teak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class TokenTable {
    public ObservableList<Token> tokens;

    public TokenTable() {
        tokens = FXCollections.observableArrayList();
    }

    public Scene createScene(EventHandler backEventHandler) {
        // táblázat

        TableColumn<Token, String> tokenColumn = new TableColumn<>("token");
        tokenColumn.setCellValueFactory(new PropertyValueFactory<>("token"));

        TableColumn<Token, Integer> userIdColumn = new TableColumn<>("userId");
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        TableView<Token> table = new TableView<>();
        table.getColumns().addAll(tokenColumn,userIdColumn);
        table.setItems(tokens);

        // új token hozzáadás sor

        TextField tokenInput = new TextField();
        tokenInput.setPromptText("token");

        TextField userIdInput = new TextField();
        userIdInput.setPromptText("userId");

        Button addButton = new Button("Add Token");
        addButton.setOnAction(e -> {

            String token;
            int userId;

            token = tokenInput.getText();
            try {
                userId = Integer.parseInt(userIdInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült átalakítani az userId-t számmá!");
                a.showAndWait();
                return;
            }

            Token newToken = new Token(token, userId);

            try {
                newToken.upload();
            } catch (Exception exception) {
                exception.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hiba");
                a.setContentText("Nem sikerült feltölteni az új teát!");
                a.showAndWait();
                return;
            }

            tokens.add(newToken);

            tokenInput.clear();
            userIdInput.clear();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(backEventHandler);

        HBox addLayout = new HBox(tokenInput, userIdInput, addButton);
        VBox vbox = new VBox(backButton, table, addLayout);
        return new Scene(vbox, 600, 400);
    }
}
