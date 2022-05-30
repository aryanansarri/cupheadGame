package com.cupheadgame.cupheadgame.Models.Transitions;

import com.cupheadgame.cupheadgame.Controller.GameController;
import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Models.Component.Boss;
import com.cupheadgame.cupheadgame.Models.Game;
import com.cupheadgame.cupheadgame.Models.Timer;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class BossTransition extends Transition {
    private Boss boss;
    private GameController gameController;
    private Image bosses[] =
            {
                    new Image(Main.class.getResource("Pic/Game/models/boss/1.png").toExternalForm()),
                    new Image(Main.class.getResource("Pic/Game/models/boss/2.png").toExternalForm()),
                    new Image(Main.class.getResource("Pic/Game/models/boss/3.png").toExternalForm()),
                    new Image(Main.class.getResource("Pic/Game/models/boss/4.png").toExternalForm()),
                    new Image(Main.class.getResource("Pic/Game/models/boss/5.png").toExternalForm()),
                    new Image(Main.class.getResource("Pic/Game/models/boss/6.png").toExternalForm())
            };
    public BossTransition(Boss boss, GameController gameController) {
        this.boss = boss;
        this.gameController = gameController;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);

    }

    private int id = 1;
    private int pre = 0;
    @Override
    protected void interpolate(double v) {
        id = (int) (v * 5);
        Game.getGame().incRocket();
        Timer.getTimer().incSec();
        boss.setImage(bosses[id]);
        gameController.updateRocket();
        gameController.updateScore();
        gameController.timerUpdate();
        if (Timer.getTimer().getS() > pre) {
            pre += 15;
            gameController.setMiniBoss(gameController.p);
        }
    }
}
