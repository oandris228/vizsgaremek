package com.example.Teak;

import javafx.application.Application;
import javafx.stage.Stage;
import kong.unirest.Unirest;

import java.nio.file.attribute.UserPrincipal;

public class HelloApplication extends Application {
    public static String getServerUrl() {
        return "http://localhost:3000";
    }

    @Override
    public void start(Stage stage) {
        Unirest.config()
                .connectTimeout(1000)
                .setDefaultHeader("Accept", "application/json")
                .followRedirects(false)
                .enableCookieManagement(false);

        MainMenu mainMenu = new MainMenu();

        UserTable userTable = new UserTable();
        userTable.setBackButtonEventHandler(mainMenu::switchTo);

        UserForm userForm = new UserForm();
        userForm.setBackButtonEventHandler(mainMenu::switchTo);

        TeaTable teaTable = new TeaTable();
        teaTable.setBackButtonEventHandler(mainMenu::switchTo);

        TeaForm teaForm = new TeaForm();
        teaForm.setBackButtonEventHandler(mainMenu::switchTo);

        OtherTable otherTable = new OtherTable();
        otherTable.setBackButtonEventHandler(mainMenu::switchTo);

        OtherForm otherForm = new OtherForm();
        otherForm.setBackButtonEventHandler(mainMenu::switchTo);

        CommissionTable commissionTable = new CommissionTable();
        commissionTable.setBackButtonEventHandler(mainMenu::switchTo);

        CommissionForm commissionForm = new CommissionForm();
        commissionForm.setBackButtonEventHandler(mainMenu::switchTo);

        mainMenu.setUserTableButtonEventHandler(userTable::switchTo);
        mainMenu.setUserFormButtonEventHandler(userForm::switchTo);
        mainMenu.setTeaTableButtonEventHandler(teaTable::switchTo);
        mainMenu.setTeaFormButtonEventHandler(teaForm::switchTo);
        mainMenu.setOtherTableButtonEventHandler(otherTable::switchTo);
        mainMenu.setOtherFormButtonEventHandler(otherForm::switchTo);
        mainMenu.setCommissionTableButtonEventHandler(commissionTable::switchTo);
        mainMenu.setCommissionFormButtonEventHandler(commissionForm::switchTo);

        stage.setScene(mainMenu.getSceneOf());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

