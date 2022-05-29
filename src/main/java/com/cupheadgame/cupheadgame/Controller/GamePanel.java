package com.cupheadgame.cupheadgame.Controller;

import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Menu;
import com.cupheadgame.cupheadgame.Models.Database;
import com.cupheadgame.cupheadgame.Models.User;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
public class GamePanel extends Application {

    public Button playGame;
    public Button logout;
    public Button continueGame;
    public Button Profile;
    public Button setting;


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Game Panel");
        stage.setScene(Menu.getMenu().getScene());
        Menu.getMenu().getParent().getChildrenUnmodifiable().get(0).requestFocus();
        stage.show();
    }

    public void initialize() {

    }

    public void Lougout(MouseEvent mouseEvent) throws IOException {
        Database.getInstance().setLoggedInUser(null);
        Menu.menu = Menu.LoginMenu;
        Main.goToLoginPage();
    }

    public void newGame(MouseEvent mouseEvent) throws Exception {
        new GameController().start(Main.getStage());
    }

    public void continueGame(MouseEvent mouseEvent) {
    }

    public void profileView(MouseEvent mouseEvent) {
    }

    public void viewScoreBoard(MouseEvent mouseEvent) {
    }

    public void Setting(MouseEvent mouseEvent) {
    }
}
