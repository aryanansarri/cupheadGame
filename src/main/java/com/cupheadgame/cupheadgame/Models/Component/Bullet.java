package com.cupheadgame.cupheadgame.Models.Component;

import com.cupheadgame.cupheadgame.Main;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Bullet extends Rectangle {
    private Image normal = new Image(Main.class.getResource(
            "Pic/Game/models/bullet/bullet.png").toExternalForm());

    public Bullet() {
        super(Airplane.getAirplane().getX() + 70, Airplane.getAirplane().getY() + 50, 72, 15);
    }

    public void moveRight() {
        if (!hitRightWall()) {
            this.setFill(new ImagePattern(normal));
            this.setX(this.getX() + 15);
        }
    }

    public boolean hitRightWall() {
        return this.getX() + this.getWidth() >= 1400;
    }
}
