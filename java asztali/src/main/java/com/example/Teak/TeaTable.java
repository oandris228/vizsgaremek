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


public class TeaTable extends GridPane {
    private Button backButton = new Button("Back");
    private Scene scene = new Scene(new VBox(backButton, this));

    private ObservableList<Tea> teas = FXCollections.observableArrayList();

    public TeaTable() {

        scene.getStylesheets().add(getClass().getResource("table.css").toExternalForm());
        getStyleClass().add("grid");

        TableColumn<Tea, Integer> teaIdColumn = new TableColumn<>("teaId");
        teaIdColumn.setCellValueFactory(new PropertyValueFactory<>("teaId"));

        TableColumn<Tea, Integer> productIdColumn = new TableColumn<>("productId");
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));

        TableColumn<Tea, String> nameColumn = new TableColumn<>("name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Tea, Integer> priceColumn = new TableColumn<>("price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Tea, String> typeColumn = new TableColumn<>("type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Tea, String> flavorColumn = new TableColumn<>("flavor");
        flavorColumn.setCellValueFactory(new PropertyValueFactory<>("flavor"));

        TableColumn<Tea, Void> actionColumn = new TableColumn<>("");
        actionColumn.setCellFactory(param -> new TableCell<Tea, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Tea tea = getTableView().getItems().get(getIndex());
                    HttpResponse<JsonNode> res = Unirest.delete(HelloApplication.getServerUrl() + "/products/" + tea.getProductId()).asJson();
                    teas.remove(tea);
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

        TableView<Tea> tableView = new TableView<>(teas);
        tableView.getColumns().addAll(teaIdColumn, productIdColumn, nameColumn, priceColumn, typeColumn, flavorColumn, actionColumn);
        tableView.setItems(teas);
        updateData();

        add(backButton, 0, 0);
        add(tableView, 0,1);
    }

    public void updateData() {
        teas.clear();
        HttpResponse<JsonNode> products = Unirest.get(HelloApplication.getServerUrl() + "/products").asJson();
        for (int i = 0; i < products.getBody().getArray().length(); i++) {
            JSONObject product = products.getBody().getArray().optJSONObject(i);
            if (product.getString("category").equals("Tea")) {
                teas.add(new Tea(
                        product.getInt("tea_id"),
                        product.getInt("id"),
                        product.getString("name"),
                        product.getInt("price"),
                        product.getJSONArray("Tea").optJSONObject(0).getString("type"),
                        product.getJSONArray("Tea").optJSONObject(0).getString("flavor"),
                        product.getJSONArray("Tea").optJSONObject(0).getString("color")
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
    }
}