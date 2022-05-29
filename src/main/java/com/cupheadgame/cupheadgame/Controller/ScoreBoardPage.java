package com.cupheadgame.cupheadgame.Controller;

import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Menu;
import com.cupheadgame.cupheadgame.Models.Database;
import com.cupheadgame.cupheadgame.Models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collections;

public class ScoreBoardPage extends Application {
    public VBox vbox;

    public void Back(MouseEvent mouseEvent) throws Exception {
        Menu.menu = Menu.GamePane;
        new GamePanel().start(Main.getStage());
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(
                Main.class.getResource("ScoreBoardPage.fxml")
        );
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public void initialize() {
        Collections.sort(
                Database.getInstance().getUsers()
        );
        int count = 1;
        for (User user : Database.getInstance().getUsers()) {
            Label label = new Label(count + " " + user.getUsername() + " " + user.getScore());
            vbox.getChildren().add(label);
            count += 1;
            if (count > 10) break;
        }
    }
}
