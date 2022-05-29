package com.cupheadgame.cupheadgame.Controller;

import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Models.Component.Airplane;
import com.cupheadgame.cupheadgame.Models.Component.Bomb;
import com.cupheadgame.cupheadgame.Models.Component.Boss;
import com.cupheadgame.cupheadgame.Models.Component.Bullet;
import com.cupheadgame.cupheadgame.Models.Transitions.BombTransition;
import com.cupheadgame.cupheadgame.Models.Transitions.BossTransition;
import com.cupheadgame.cupheadgame.Models.Transitions.BulletTransition;
import com.cupheadgame.cupheadgame.Models.Transitions.Cloud;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameController extends Application {
    private int score = 0;
    Label label = new Label("");
    private Image currentBomb = new Image(Main.class.getResource(
            "Pic/Game/models/BulletLogos/BulletLogo.png").toExternalForm());

    private Image muteImg = new Image(Main.class.getResource(
            "Pic/mute.png").toExternalForm());
    Rectangle currentBombShape = new Rectangle(5, 700, 66, 66);
    private boolean bomb = true;

    private boolean mute = false;
    private AudioClip bultAudioSound = new AudioClip(
            Main.class.getResource("Musics/bult.mp3").toExternalForm()
    );

    private AudioClip bombAudioSound = new AudioClip(
            Main.class.getResource("Musics/bomb.mp3").toExternalForm()
    );

    private AudioClip audioClip = new
            AudioClip(Main.class.getResource("Musics/1.mp3").toExternalForm());
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(Main.class.getResource("GameMenu.fxml"));
        createBackground(pane);
        createAirplane(pane);
        setBulletLogos();
        setScoreLabel(pane);
        setBoss(pane);
        setMute(pane);
        pane.getChildren().add(currentBombShape);
        Scene scene = new Scene(pane);
        stage.setTitle("cupheadGame");
        stage.setScene(scene);
        stage.setResizable(false);
        pane.getChildren().get(2).requestFocus();
        stage.centerOnScreen();
        stage.setFullScreen(true);
        stage.show();
    }
    public void initialize() {
        audioClip.setCycleCount(-1);
        if (!mute)
            audioClip.play();
    }

    private void createBackground(Pane pane) {
        Image image = new Image(
                Main.class.getResourceAsStream("Pic/Game/Background/birdhouse_bg_0008.png"));
        pane.getChildren().add(new ImageView(image));
        Image cloud = new Image(
                Main.class.getResourceAsStream("Pic/Game/Background/birdhouse_bg_0006.png"));
        ImageView cloudView = new ImageView(cloud);
        pane.getChildren().add(cloudView);
        new Cloud(cloudView).play();
    }

    private void createAirplane(Pane pane) {
        Airplane airplane = Airplane.getAirplane();
        airplane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.LEFT)
                    || keyEvent.getCode().equals(KeyCode.A)) {
                    airplane.moveLeft();
                }
                else if (keyEvent.getCode().equals(KeyCode.RIGHT)
                    || keyEvent.getCode().equals(KeyCode.D)) {
                    airplane.moveRight();
                }
                else if (keyEvent.getCode().equals(KeyCode.UP)
                    || keyEvent.getCode().equals(KeyCode.W)) {
                    airplane.moveUp();
                }
                else if (keyEvent.getCode().equals(KeyCode.DOWN)
                    || keyEvent.getCode().equals(KeyCode.S)) {
                    airplane.moveDown();
                }
                else if (keyEvent.getCode().equals(KeyCode.SPACE)) {
                    if (bomb) {
                        Bullet bullet = new Bullet();
                        pane.getChildren().add(bullet);
                        BulletTransition bulletTransition = new BulletTransition(bullet);
                        bulletTransition.play();
                        if (!mute)
                            bultAudioSound.play();
                        bulletTransition.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                pane.getChildren().remove(bullet);
                            }
                        });
                    }
                    else {
                        Bomb bmb = new Bomb();
                        pane.getChildren().add(bmb);
                        BombTransition bombTransition = new BombTransition(bmb);
                        bombTransition.play();
                        if (!mute)
                            bombAudioSound.play();
                        bombTransition.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                pane.getChildren().remove(bmb);
                            }
                        });
                    }
                }
                else if (keyEvent.getCode().equals(KeyCode.TAB)) {
                    if (bomb) {
                        bomb = false;
                        currentBomb = new Image(Main.class.getResource(
                                "Pic/Game/models/BulletLogos/BombLogo.png").toExternalForm());
                    }
                    else {
                        bomb = true;
                        currentBomb = new Image(Main.class.getResource(
                                "Pic/Game/models/BulletLogos/BulletLogo.png").toExternalForm());
                    }
                    currentBombShape.setFill(new ImagePattern(currentBomb));
                }
                updateScore();
            }
        });
        pane.getChildren().add(airplane);
    }

    private void setBulletLogos() {
        currentBombShape.setFill(new ImagePattern(currentBomb));
    }

    private void setScoreLabel(Pane pane) {
        label.setText("score: " + score);
        label.setTranslateX(1250);
        label.setTranslateY(730);
        label.setFont(new Font(18));
        pane.getChildren().add(label);
    }

    private void updateScore() {
        label.setText("score: " + score);
    }

    private void setBoss(Pane pane) {
        Boss boss = Boss.getInstance();
        pane.getChildren().add(boss);
        BossTransition bossTransition = new BossTransition(boss);
        bossTransition.play();
    }

    private void setMute(Pane pane) {
        Rectangle rectangle = new Rectangle(72, 700, 66, 66);
        rectangle.setFill(new ImagePattern(muteImg));
        rectangle.setCursor(Cursor.HAND);
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectangle.setEffect(new Glow());
                if (!mute) {
                    mute = true;
                    audioClip.stop();
                }
                else {
                    mute = false;
                    audioClip.play();
                }
            }
        });
        pane.getChildren().add(rectangle);
    }
}
