package com.cupheadgame.cupheadgame.Controller;

import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Menu;
import com.cupheadgame.cupheadgame.Models.Database;
import com.cupheadgame.cupheadgame.Models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class ProfilePage extends Application {
    public ImageView avatar;

    public Button random;
    public PasswordField password;
    public TextField username;
    public Button backbtn;
    public Button save;

    private String imageName;

    public void setRandom(MouseEvent mouseEvent) {
        Random rnd = new Random();
        int id = rnd.nextInt(8) + 1;
        imageName = id + ".jpg";
        try {
            Image image = new Image(Main.class.getResourceAsStream("Pic/Avatars/"+id+".jpg"));
            avatar.setImage(image);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        Menu.menu = Menu.GamePane;
        new GamePanel().start(Main.getStage());
    }

    public void saveChanges(MouseEvent mouseEvent) {
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        if (username.equals("") || password.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("all field must be filled");
            alert.showAndWait();
            return;
        }
        if (!username.equals(Database.getInstance().getLoggedInUser().getUsername()) && Database.getInstance().getUser(username) != null) {
            Alert errormess = new Alert(Alert.AlertType.ERROR);
            errormess.setHeaderText(null);
            errormess.setContentText("already " + username + " is exist");
            errormess.showAndWait();
            return;
        }
        Database.getInstance().getLoggedInUser().setAvatar(imageName);
        Database.getInstance().getLoggedInUser().setUsername(username);
        Database.getInstance().getLoggedInUser().setPassword(password);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("changes saved successfully");
        alert.showAndWait();
        Database.getInstance().saveData();
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(
                Main.class.getResource("ProfilePage.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void initialize() {
        imageName = Database.getInstance().getLoggedInUser().getAvatar();
        avatar.setImage(
                new Image(
                        Main.class.getResource("Pic/Avatars/" + imageName).toExternalForm()
                )
        );
        username.setText(
                Database.getInstance().getLoggedInUser().getUsername()
        );
        password.setText(
                Database.getInstance().getLoggedInUser().getPassword()
        );
    }

    public void removeAccount(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("account removed successfully");
        alert.showAndWait();
        Database.getInstance().getUsers().remove(
                Database.getInstance().getLoggedInUser()
        );
        Database.getInstance().saveData();
        Menu.menu = Menu.LoginMenu;
        Main.goToLoginPage();
    }
}
