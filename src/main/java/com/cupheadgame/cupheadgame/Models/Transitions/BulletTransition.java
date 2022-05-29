package com.cupheadgame.cupheadgame.Models.Transitions;

import com.cupheadgame.cupheadgame.Models.Component.Bullet;
import javafx.animation.Transition;
import javafx.util.Duration;

public class BulletTransition extends Transition {

    private Bullet bullet;
    public BulletTransition(Bullet bullet) {
        this.bullet = bullet;
        this.setCycleDuration(Duration.millis(700));
    }
    @Override
    protected void interpolate(double v) {
        bullet.moveRight();
    }
}
