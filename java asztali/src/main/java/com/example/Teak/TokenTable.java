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

public class TokenTable {
    public ObservableList<Token> tokens;

    public TokenTable() {
        tokens = FXCollections.observableArrayList();
    }

    public Scene createScene(EventHandler backEventHandler) {
        // Table setup
        TableColumn<Token, String> tokenColumn = new TableColumn<>("token");
        tokenColumn.setCellValueFactory(new PropertyValueFactory<>("token"));

        TableColumn<Token, Integer> userIdColumn = new TableColumn<>("userId");
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        TableView<Token> table = new TableView<>();
        table.getColumns().addAll(tokenColumn, userIdColumn);
        table.setItems(tokens);

        // Input fields
        TextField tokenInput = new TextField();
        tokenInput.setPromptText("token");

        TextField userIdInput = new TextField();
        userIdInput.setPromptText("userId");

        Button addButton = new Button("Add Token");
        addButton.setOnAction(e -> {
            String token;
            int userId;

            token = tokenInput.getText();
            if (token.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("The token field cannot be empty!");
                a.showAndWait();
                return;
            }

            try {
                userId = Integer.parseInt(userIdInput.getText());
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Failed to convert userId to a number!");
                a.showAndWait();
                return;
            }

            Token newToken = new Token(token, userId);

            try {
                newToken.upload();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Database Error");
                a.setContentText("Failed to upload the new token due to a database error!");
                a.showAndWait();
                return;
            } catch (Exception exception) {
                exception.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("An unexpected error occurred while uploading the new token!");
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
        Scene scene = new Scene(vbox, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        return scene;
    }
}