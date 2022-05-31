package com.cupheadgame.cupheadgame.Models.Component;

import com.cupheadgame.cupheadgame.Main;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Die extends Rectangle {
    private Pane pane;
    private Image image = new Image(Main.class.getResource(
            "Pic/Game/models/EggExplosionAssets/0/.png").toExternalForm());

    public Die(double v, double v1, double v2, double v3, Pane pane) {
        super(v, v1, v2, v3);
        this.pane = pane;
    }

    public void setImage(Image im) {
        this.image = im;
    }
}
