package com.example.Teak;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

public class HelloApplication extends Application {
    Connection connection;
    Scene mainMenu;
    TeaTable tt;
    OrderTable ot;


    @Override
    public void start(Stage stage) {
        try {
            String url = "jdbc:mysql://localhost:3306/vizsgaremek"; // Database name
            String user = "root"; // Default XAMPP user
            String password = ""; // Default XAMPP password is empty
            connection = DriverManager.getConnection(url, user, password);
            Tea.setConnection(connection);
            Order.setConnection(connection);


            tt = new TeaTable();
            tt.teas.addAll(Tea.getAll());

            ot = new OrderTable();
            ot.orders.addAll(Order.getAll());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Button TeaTableButton = new Button("Tea");
        TeaTableButton.setOnAction(e ->{
            stage.setScene(tt.createScene(be -> {
                stage.setScene(mainMenu);
            }));
        });

        Button OrderTableButton = new Button("Order");
        OrderTableButton.setOnAction(e ->{
            stage.setScene(ot.createScene(be -> {
                stage.setScene(mainMenu);
            }));
        });

        HBox menuOptions = new HBox(TeaTableButton, OrderTableButton);
        mainMenu = new Scene(menuOptions, 600, 400);

        stage.setScene(mainMenu);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

