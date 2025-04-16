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

public class OtherTable extends GridPane {
    private Button backButton = new Button("Back");
    private Scene scene = new Scene(this);

    private ObservableList<Other> others = FXCollections.observableArrayList();

    public OtherTable() {

        scene.getStylesheets().add(getClass().getResource("table.css").toExternalForm());
        getStyleClass().add("grid");

        TableColumn<Other, Integer> otherIdColumn = new TableColumn<>("otherId");
        otherIdColumn.setCellValueFactory(new PropertyValueFactory<>("otherId"));

        TableColumn<Other, Integer> productIdColumn = new TableColumn<>("productId");
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));

        TableColumn<Other, String> nameColumn = new TableColumn<>("name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Other, Integer> priceColumn = new TableColumn<>("price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Other, String> descriptionColumn = new TableColumn<>("description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Other, String> imgColumn = new TableColumn<>("img");
        imgColumn.setCellValueFactory(new PropertyValueFactory<>("img"));

        TableColumn<Other, Void> actionColumn = new TableColumn<>("");
        actionColumn.setCellFactory(param -> new TableCell<Other, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Other other = getTableView().getItems().get(getIndex());
                    HttpResponse<JsonNode> res = Unirest.delete(HelloApplication.getServerUrl() + "/products/" + other.getProductId()).asJson();
                    others.remove(other);
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
        TableView<Other> tableView = new TableView<>(others);
        tableView.getColumns().addAll(otherIdColumn, productIdColumn, nameColumn, priceColumn, descriptionColumn, imgColumn, actionColumn);
        tableView.setItems(others);
        updateData();

        add(backButton, 0, 0);
        add(tableView, 0,1);
    }

    public void updateData() {
        others.clear();
        HttpResponse<JsonNode> products = Unirest.get(HelloApplication.getServerUrl() + "/products").asJson();
        for (int i = 0; i < products.getBody().getArray().length(); i++) {
            JSONObject product = products.getBody().getArray().optJSONObject(i);
            if (product.getString("category").equals("Other")) {
                others.add(new Other(
                        product.getInt("other_id"),
                        product.getInt("id"),
                        product.getString("name"),
                        product.getInt("price"),
                        product.getJSONArray("Other").optJSONObject(0).getString("description"),
                        product.getJSONArray("Other").optJSONObject(0).getString("img")
                ));
            }
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