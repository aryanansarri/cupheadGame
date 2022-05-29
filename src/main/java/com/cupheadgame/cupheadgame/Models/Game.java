package com.cupheadgame.cupheadgame.Models;

public class Game {
    private static Game game;
    public static Game getInstance() {
        return game;
    }
    public static void newGame(User user) {
        game = new Game(user);
    }

    public static void GameOver() {
        game = null;
    }

    private User player;
    private double time;
    private int score;
    private double rocket;

    public Game(User player) {
        this.player = player;
        this.time = 0;
        this.score = 0;
        this.rocket = 0;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        Game.game = game;
    }

    public double getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incTime() {
        this.time ++;
    }

    public void incScore(int amount) {
        this.score += amount;
    }

    public void incRocket() {
        this.rocket += 0.03;
        this.rocket = Math.min(
                100, this.rocket
        );
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public double getRocket() {
        return rocket;
    }

    public void setRocket(double rocket) {
        this.rocket = rocket;
    }

    public void setTime(double time) {
        this.time = time;
    }

}
