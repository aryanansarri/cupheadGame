package com.cupheadgame.cupheadgame.Controller;

import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Menu;
import com.cupheadgame.cupheadgame.Models.Database;
import com.cupheadgame.cupheadgame.Models.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Random;

public class RegisterController {
    @FXML
    private ImageView avatar;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button submit;
    @FXML
    private Label error;
    private String imageName = "unknown.jpg";

    public void Submit(MouseEvent mouseEvent) throws IOException {
        register();
    }


    public void register() throws IOException {
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        if (username.equals("") || password.equals("")) {
            error.setText("all field must be filled");
            error.setVisible(true);
            return;
        }
        if (Database.getInstance().getUser(username) != null) {
            Alert errormess = new Alert(Alert.AlertType.ERROR);
            errormess.setHeaderText(null);
            errormess.setContentText("already " + username + " is exist");
            errormess.showAndWait();
            return;
        }
        User user = new User(username, password, imageName, 0);
        Database.getInstance().addUser(user);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("register");
        alert.setHeaderText(null);
        alert.setContentText("register successfully");
        alert.showAndWait();
        clear();
        Database.getInstance().saveData();
    }

    public void setRandowm(MouseEvent mouseEvent) {
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

    public void Clear() {
        error.setVisible(false);
    }

    public void clear() {
        username.setText("");
        password.setText("");
        avatar.setImage(
                new Image(Main.class.getResourceAsStream("Pic/Avatars/unknown.jpg")));
    }

    public void EntetPressedCheck(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            register();
        }
        else {
            Clear();
        }
    }

    public void Back(MouseEvent mouseEvent) throws IOException {
        Menu.menu = Menu.LoginMenu;
        Main.goToLoginPage();
    }
}
