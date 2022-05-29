package com.cupheadgame.cupheadgame.Models.Transitions;

import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Models.Component.Boss;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class BossTransition extends Transition {
    private Boss boss;
    private Image bosses[] =
            {
                    new Image(Main.class.getResource("Pic/Game/models/boss/1.png").toExternalForm()),
                    new Image(Main.class.getResource("Pic/Game/models/boss/2.png").toExternalForm()),
                    new Image(Main.class.getResource("Pic/Game/models/boss/3.png").toExternalForm()),
                    new Image(Main.class.getResource("Pic/Game/models/boss/4.png").toExternalForm()),
                    new Image(Main.class.getResource("Pic/Game/models/boss/5.png").toExternalForm()),
                    new Image(Main.class.getResource("Pic/Game/models/boss/6.png").toExternalForm())
            };
    public BossTransition(Boss boss) {
        this.boss = boss;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    private int id = 1;
    @Override
    protected void interpolate(double v) {
        id = (int) (v * 5);
        boss.setImage(bosses[id]);
    }
}