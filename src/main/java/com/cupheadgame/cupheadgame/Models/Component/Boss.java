package com.cupheadgame.cupheadgame.Models.Component;

import com.cupheadgame.cupheadgame.Main;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Boss extends Rectangle {
    private static Boss boss;
    public static Boss getInstance() {
        if (boss == null) boss = new Boss();
        return boss;
    }
    private Image image = new Image(
            Main.class.getResource("Pic/Game/models/boss/1.png").toExternalForm());
    public Boss() {
        super(800, 120, 651, 509);
        this.setFill(new ImagePattern(image));
    }

    public void setImage(Image image) {
        this.image = image;
        this.setFill(new ImagePattern(image));
    }
}
