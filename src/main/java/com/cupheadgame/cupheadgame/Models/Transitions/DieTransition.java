package com.cupheadgame.cupheadgame.Models.Transitions;

import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Models.Component.Die;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class DieTransition extends Transition {
    private Die die;

    public DieTransition(Die die) {
        this.die = die;
        this.setCycleDuration(Duration.millis(1000));
    }
    @Override
    protected void interpolate(double v) {
        int id = (int) (v * 3);
        die.setImage(new Image(Main.class.getResource(
                        "Pic/models/EggExplosionAssets/" + id + ".png").toExternalForm()
        ));
    }
}
