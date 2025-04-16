package com.example.Teak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class UserTable extends GridPane {
    private Button backButton = new Button("Back");
    private Scene scene = new Scene(this);

    private ObservableList<User> users = FXCollections.observableArrayList();

    public UserTable() {
        scene.getStylesheets().add(getClass().getResource("table.css").toExternalForm());
        getStyleClass().add("grid");

        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<User, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("shippingAddress"));

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<User, Void> actionColumn = new TableColumn<>("");
        actionColumn.setCellFactory(param -> new TableCell<User, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    HttpResponse<JsonNode> res = Unirest.delete(HelloApplication.getServerUrl() + "/users/" + user.getUserId()).asJson();
                    users.remove(user);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        TableView<User> tableView = new TableView<>(users);
        tableView.getColumns().addAll(
                idColumn,
                usernameColumn,
                emailColumn,
                passwordColumn,
                addressColumn,
                roleColumn,
                actionColumn
        );
        tableView.setItems(users);

        updateData();

        add(backButton, 0, 0);
        add(tableView, 0,1);
    }

    public void updateData() {
        users.clear();
        HttpResponse<JsonNode> usersResponse = Unirest.get(HelloApplication.getServerUrl() + "/users").asJson();
        for (int i = 0; i < usersResponse.getBody().getArray().length(); i++) {
            JSONObject user = usersResponse.getBody().getArray().optJSONObject(i);
            users.add(new User(
                    user.getInt("id"),
                    user.getString("username"),
                    user.getString("email"),
                    user.getString("password"),
                    user.optString("shipping_address", null),
                    user.getString("role").equals("Admin") ? User.Role.Admin : User.Role.User
            ));
        }
    }

    public void setBackButtonEventHandler(EventHandler<ActionEvent> eventHandler) {
        backButton.setOnAction(eventHandler);
    }

    public void switchTo(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        updateData();
    }
}