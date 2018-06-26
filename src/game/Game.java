package game;

import entity.*;
import objectdraw.DrawingCanvas;
import objectdraw.FrameCanvas;
import objectdraw.Location;
import util.KeyBind;

import java.awt.*;
import java.util.Random;

public class Game extends FrameCanvas {

    private Player[] players = new Player[2];
    private Ball ball;
    private Random random = new Random();
    private boolean gameEnded = false;

    public Game() {
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
    }
    public void start() {
        players[0] = new Player(getHeight() - 100, this);
        players[1] = new Player(100, this);
        ball = new Ball(getCanvas());

        addKeyListener(new KeyBind(players[0]));
        requestFocus();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ball.moveTo(players[0].getLocation());
            }
        });
        t.start();
    }

    // GETTERS

    private DrawingCanvas getCanvas() {
        return this;
    }
    public Player getPlayer() {
        return players[0];
    }
    public Player getOtherPlayer() {
        return players[1];
    }

    public Ball getBall() {
        return ball;
    }
    public Player getBallOwner() {
        return ball.getOwner();
    }
    public boolean isGameEnded() {
        return gameEnded;
    }
}
