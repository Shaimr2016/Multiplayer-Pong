package entity;

import main.Main;
import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.VisibleImage;
import util.Polygon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    private boolean isYou;
    private Location tempPosition;
    private VisibleImage graphic;
    private DrawingCanvas canvas;
    private Thread moveThread;

    public enum direction { RIGHT, LEFT }

    public Player(boolean isYou, int y, DrawingCanvas canvas) {
        super(canvas.getWidth() / 2, y);
        position = new Location(canvas.getWidth() / 2, y);
        this.canvas = canvas;
        this.isYou = isYou;
        this.bounds = new Polygon(canvas.getWidth() / 2, y, new Dimension(150, 20));

        try {
            graphic = new VisibleImage(ImageIO.read(new File("data/images/pong_paddle.png")),
                    position, 150, 20, canvas);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            graphic.moveTo(position);
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
            x += 20;
        } else if (dir == direction.LEFT) {
            x -= 20;
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
