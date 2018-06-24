package entity;

import main.Main;
import objectdraw.DrawingCanvas;
import objectdraw.FilledRect;
import objectdraw.Location;
import util.Polygon;

import java.awt.*;

public class Player extends Entity {

    private boolean isYou;
    private Location position;
    private Location tempPosition;
    private FilledRect graphic;
    private DrawingCanvas canvas;
    private Thread moveThread;

    public enum direction { RIGHT, LEFT }

    public Player(boolean isYou, int y, DrawingCanvas canvas) {
        super(canvas.getWidth() / 2, y);
        position = new Location(canvas.getWidth() / 2, y);
        this.canvas = canvas;
        this.isYou = isYou;
        this.bounds = new Polygon(canvas.getWidth() / 2, y, new Dimension(150, 20));

        graphic = new FilledRect(
                bounds.xpoints[0], bounds.ypoints[0], bounds.xpoints[2], bounds.ypoints[2], canvas);

        start();
    }
    public void run() {
        while(!Main.getGame().isGameEnded()) {
            try {
                synchronized (this) {
                    wait(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            graphic.removeFromCanvas();
            graphic = new FilledRect(position, 150, 20, canvas);
        }
        graphic.removeFromCanvas();
    }
    public void move(direction dir) {
        if (!isYou) return;
        if (x + graphic.getWidth() > canvas.getWidth() && dir == direction.RIGHT) {
            return;
        } else if (x < 0 && dir == direction.LEFT) {
            return;
        }
        if (dir == direction.RIGHT) {
            this.x += 20;
        } else if (dir == direction.LEFT) {
            this.x -= 20;
        }
        tempPosition = new Location(x, y);
        moveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (tempPosition != null && !tempPosition.equals(position)) {
                    if (tempPosition.getX() > position.getX()) {
                        position.translate(1, 0);
                    } else if (tempPosition.getX() < position.getX()) {
                        position.translate(-1, 0);
                    }
                    try {
                        synchronized (this) {
                            wait(1);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        moveThread.start();
    }
    public void stopMove() {
        moveThread = null;
        tempPosition = null;
    }
}
