package com.cupheadgame.cupheadgame.Models.Transitions;

import com.cupheadgame.cupheadgame.Models.Component.Bomb;
import javafx.animation.Transition;
import javafx.util.Duration;

public class BombTransition extends Transition {
    private Bomb bomb;
    public BombTransition(Bomb bomb) {
        this.bomb = bomb;
        this.setCycleDuration(Duration.millis(600));
    }
    @Override
    protected void interpolate(double v) {
        bomb.moveDown();
    }
}
