package com.example.Teak;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import kong.unirest.HttpRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    Scene mainMenu;

    TeaTable teaTable;
    TableView<Tea> teaTableView;

    @Override
    public void start(Stage stage) {
        Unirest.config()
                .connectTimeout(1000)
                .setDefaultHeader("Accept", "application/json")
                .followRedirects(false)
                .enableCookieManagement(false);

//        Button TeaTableButton = new Button("Tea");
//        TeaTableButton.setOnAction(e ->{
//            stage.setScene(teaT.createScene(be -> {
//                stage.setScene(mainMenu);
//            }));
//        });
//
//        Button OrderTableButton = new Button("Order");
//        OrderTableButton.setOnAction(e ->{
//            stage.setScene(orderT.createScene(be -> {
//                stage.setScene(mainMenu);
//            }));
//        });

//        Button ProductTableButton = new Button("Product");
//        ProductTableButton.setOnAction(e ->{
//            stage.setScene(productT.createScene(be -> {
//                stage.setScene(mainMenu);
//            }));
//        });

//        Button UserTableButton = new Button("User");
//        UserTableButton.setOnAction(e ->{
//            stage.setScene(userT.createScene(be -> {
//                stage.setScene(mainMenu);
//            }));
//        });
//
//        Button OtherTableButton = new Button("Other");
//        OtherTableButton.setOnAction(e ->{
//            stage.setScene(otherT.createScene(be -> {
//                stage.setScene(mainMenu);
//            }));
//        });
//
//        Button OrdertoproductTableButton = new Button("Ordertoproduct");
//        OrdertoproductTableButton.setOnAction(e ->{
//            stage.setScene(ordertoproductT.createScene(be -> {
//                stage.setScene(mainMenu);
//            }));
//        });
//
//        Button OrdertouserTableButton = new Button("Ordertouser");
//        OrdertouserTableButton.setOnAction(e ->{
//            stage.setScene(ordertouserT.createScene(be -> {
//                stage.setScene(mainMenu);
//            }));
//        });
//
//        Button TokenTableButton = new Button("Token");
//        TokenTableButton.setOnAction(e ->{
//            stage.setScene(tokenT.createScene(be -> {
//                stage.setScene(mainMenu);
//            }));
//        });

//        HBox menuOptions = new HBox(ProductTableButton);

        List<Tea> teas = new ArrayList<>();



//        HttpResponse<JsonNode> products = Unirest.get("http://localhost:3000/products").asJson();
//        System.out.println("Products:\n" + products.getBody());

        HttpResponse<JsonNode> products1 = Unirest.post("http://localhost:3000/products")
                .header("Content-Type", "application/json")
                .body(
                "{" +
                        "\"id\":0," +
                        "\"name\":\"tea 1\"," +
                        "\"price\":1000," +
                        "\"category\":\"Tea\"," +
                        "\"tea_type\":\"filteres\"," +
                        "\"tea_flavor\":\"strawberry\""+
                    "}"
                ).asJson();
        HttpResponse<JsonNode> products2 = Unirest.post("http://localhost:3000/products")
                .header("Content-Type", "application/json")
                .body(
                        "{" +
                                "\"id\":0," +
                                "\"name\":\"tea 2\"," +
                                "\"price\":1000," +
                                "\"category\":\"Tea\"," +
                                "\"tea_type\":\"asd\"," +
                                "\"tea_flavor\":\"strawberry\""+
                                "}"
                ).asJson();

        HttpResponse<JsonNode> products3 = Unirest.post("http://localhost:3000/products")
                .header("Content-Type", "application/json")
                .body(
                        "{" +
                                "\"id\":0," +
                                "\"name\":\"tea 3\"," +
                                "\"price\":1000," +
                                "\"category\":\"Tea\"," +
                                "\"tea_type\":\"filteres\"," +
                                "\"tea_flavor\":\"citrom\""+
                                "}"
                ).asJson();


        teaTable = new TeaTable();
        teaTableView = teaTable.createVBox();

        mainMenu = new Scene(teaTableView, 600, 400);
//        mainMenu.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setScene(mainMenu);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

