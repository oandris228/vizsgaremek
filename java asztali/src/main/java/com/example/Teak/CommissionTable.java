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

public class CommissionTable extends GridPane {
    private Button backButton = new Button("Back");
    private Scene scene = new Scene(this);

    private ObservableList<Commission> commissions = FXCollections.observableArrayList();

    public CommissionTable() {

        scene.getStylesheets().add(getClass().getResource("table.css").toExternalForm());
        getStyleClass().add("grid");

        TableColumn<Commission, Integer> commissionIdColumn = new TableColumn<>("Commission ID");
        commissionIdColumn.setCellValueFactory(new PropertyValueFactory<>("commissionId"));

        TableColumn<Commission, Integer> itemIdColumn = new TableColumn<>("Item ID");
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));

        TableColumn<Commission, Integer> userIdColumn = new TableColumn<>("User ID");
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        TableColumn<Commission, Integer> productIdColumn = new TableColumn<>("Product ID");
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));

        TableColumn<Commission, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Commission, String> productNameColumn = new TableColumn<>("Product Name");
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));

        TableColumn<Commission, String> shippingAddressColumn = new TableColumn<>("Shipping Address");
        shippingAddressColumn.setCellValueFactory(new PropertyValueFactory<>("shippingAddress"));

        TableColumn<Commission, String> stateColumn = new TableColumn<>("State");
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

        TableColumn<Commission, String> extratextColumn = new TableColumn<>("Extra Text");
        extratextColumn.setCellValueFactory(new PropertyValueFactory<>("extratext"));

        TableColumn<Commission, Void> actionColumn = new TableColumn<>("");
        actionColumn.setCellFactory(param -> new TableCell<Commission, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Commission commission = getTableView().getItems().get(getIndex());
                    HttpResponse<JsonNode> res2 = Unirest.delete(HelloApplication.getServerUrl() + "/items/" + commission.getItemId()).asJson();
                    HttpResponse<JsonNode> res1 = Unirest.delete(HelloApplication.getServerUrl() + "/commissions/" + commission.getCommissionId()).asJson();
                    commissions.remove(commission);
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

        TableView<Commission> tableView = new TableView<>(commissions);
        tableView.getColumns().addAll(
                commissionIdColumn,
                itemIdColumn,
                userIdColumn,
                productIdColumn,
                quantityColumn,
                productNameColumn,
                shippingAddressColumn,
                stateColumn,
                extratextColumn,
                actionColumn
        );
        tableView.setItems(commissions);

        updateData();

        add(backButton, 0, 0);
        add(tableView, 0,1);
    }

    private void updateData() {
        commissions.clear();
        HttpResponse<JsonNode> items = Unirest.get(HelloApplication.getServerUrl() + "/items").asJson();
        for (int i = 0; i < items.getBody().getArray().length(); i++) {
            JSONObject item = items.getBody().getArray().optJSONObject(i);
            JSONObject commission = Unirest.get(HelloApplication.getServerUrl() + "/commissions/" + item.getInt("commissionId")).asJson().getBody().getObject();
            commissions.add(new Commission(
                    item.getInt("commissionId"),
                    commission.getInt("user_id"),
                    item.getInt("productId"),
                    item.getInt("id"),
                    item.getInt("quantity"),
                    item.getString("productName"),
                    commission.getString("shipping_address"),
                    Commission.State.valueOf(commission.getString("commissionState")),
                    commission.getString("extratext")
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