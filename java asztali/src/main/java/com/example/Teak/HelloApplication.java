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
    TeaTable teaT;
    OrderTable orderT;
    ProductTable productT;
    UserTable userT;
    OtherTable otherT;
    OrdertoproductTable ordertoproductT;
    OrdertouserTable ordertouserT;
    TokenTable tokenT;


    @Override
    public void start(Stage stage) {
        try {
            String url = "jdbc:mysql://localhost:3306/vizsgaremek"; // Database name
            String user = "root"; // Default XAMPP user
            String password = ""; // Default XAMPP password is empty
            connection = DriverManager.getConnection(url, user, password);
            Tea.setConnection(connection);
            Order.setConnection(connection);
            Product.setConnection(connection);
            User.setConnection(connection);
            Other.setConnection(connection);
            Ordertoproduct.setConnection(connection);
            Ordertouser.setConnection(connection);
            Token.setConnection(connection);



            teaT = new TeaTable();
            teaT.teas.addAll(Tea.getAll());

            orderT = new OrderTable();
            orderT.orders.addAll(Order.getAll());

            productT = new ProductTable();
            productT.products.addAll(Product.getAll());

            userT = new UserTable();
            userT.users.addAll(User.getAll());

            otherT = new OtherTable();
            otherT.others.addAll(Other.getAll());

            ordertoproductT = new OrdertoproductTable();
            ordertoproductT.ordertoproducts.addAll(Ordertoproduct.getAll());

            ordertouserT = new OrdertouserTable();
            ordertouserT.ordertousers.addAll(Ordertouser.getAll());

            tokenT = new TokenTable();
            tokenT.tokens.addAll(Token.getAll());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Button TeaTableButton = new Button("Tea");
        TeaTableButton.setOnAction(e ->{
            stage.setScene(teaT.createScene(be -> {
                stage.setScene(mainMenu);
            }));
        });

        Button OrderTableButton = new Button("Order");
        OrderTableButton.setOnAction(e ->{
            stage.setScene(orderT.createScene(be -> {
                stage.setScene(mainMenu);
            }));
        });

        Button ProductTableButton = new Button("Product");
        ProductTableButton.setOnAction(e ->{
            stage.setScene(productT.createScene(be -> {
                stage.setScene(mainMenu);
            }));
        });

        Button UserTableButton = new Button("User");
        UserTableButton.setOnAction(e ->{
            stage.setScene(userT.createScene(be -> {
                stage.setScene(mainMenu);
            }));
        });

        Button OtherTableButton = new Button("Other");
        OtherTableButton.setOnAction(e ->{
            stage.setScene(otherT.createScene(be -> {
                stage.setScene(mainMenu);
            }));
        });

        Button OrdertoproductTableButton = new Button("Ordertoproduct");
        OrdertoproductTableButton.setOnAction(e ->{
            stage.setScene(ordertoproductT.createScene(be -> {
                stage.setScene(mainMenu);
            }));
        });

        Button OrdertouserTableButton = new Button("Ordertouser");
        OrdertouserTableButton.setOnAction(e ->{
            stage.setScene(ordertouserT.createScene(be -> {
                stage.setScene(mainMenu);
            }));
        });

        Button TokenTableButton = new Button("Token");
        TokenTableButton.setOnAction(e ->{
            stage.setScene(tokenT.createScene(be -> {
                stage.setScene(mainMenu);
            }));
        });

        HBox menuOptions = new HBox(TeaTableButton, OrderTableButton, ProductTableButton, UserTableButton, OtherTableButton, OrdertoproductTableButton, OrdertouserTableButton, TokenTableButton);
        mainMenu = new Scene(menuOptions, 600, 400);
        mainMenu.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setScene(mainMenu);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

