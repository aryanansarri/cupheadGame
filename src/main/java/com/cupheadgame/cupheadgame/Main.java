package com.cupheadgame.cupheadgame;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    private static Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        this.stage = stage;
        Run();
    }

    public void Run() throws IOException {
        goToLoginPage();
    }
    public static void goToLoginPage() throws IOException {
        stage.setTitle("Login Page");
        stage.setScene(Menu.getMenu().getScene());
        stage.show();
    }

    public static void goToRegisterPage() throws IOException {
        stage.setTitle("Register Page");
        Menu.menu = Menu.RegisterMenu;
        stage.setScene(Menu.getMenu().getScene());
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }
}