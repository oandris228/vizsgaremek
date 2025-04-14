package com.example.Teak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;


public class TeaTable {
    private ObservableList<Tea> teas;

    public TeaTable() {
        teas = FXCollections.observableArrayList();
    }

    public void addTeas(List<Tea> teas) {
        this.teas.addAll(teas);
    }

    public TableView<Tea> createVBox() {
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

        TableColumn<Tea, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellFactory(param -> new TableCell<Tea, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Tea tea = getTableView().getItems().get(getIndex());
                    teas.remove(tea);
                    // TODO delete from db
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

        TableView<Tea> table = new TableView<>();
        table.getColumns().addAll(teaIdColumn, productIdColumn, nameColumn, priceColumn, typeColumn, flavorColumn, actionColumn);
        table.setItems(teas);
        return table;
    }
}