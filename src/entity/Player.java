package entity;

import main.Main;
import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.Text;
import objectdraw.VisibleImage;
import org.json.JSONException;
import org.json.JSONObject;
import util.Polygon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    public enum direction { RIGHT, LEFT }

    public Player(int y, DrawingCanvas canvas) {
        super(canvas.getWidth() / 2, y);
        position = new Location(canvas.getWidth() / 2, y);
        this.canvas = canvas;
        this.bounds = new Polygon(canvas.getWidth() / 2, y, new Dimension(150, 20));

        try {
            graphic = new VisibleImage(ImageIO.read(new File("data/images/pong_paddle.png")),
                    position, 200, 30, canvas);
        } catch (IOException e) {
            e.printStackTrace();
        }

        start();
    }

    // MOVE METHODS

    public void move(Player.direction dir) {
        if (x + graphic.getWidth() > canvas.getWidth() && dir == Player.direction.RIGHT) {
            return;
        } else if (x < 0 && dir == Player.direction.LEFT) {
            return;
        }
        if (dir == Player.direction.RIGHT) {
            x += 20;
        } else if (dir == Player.direction.LEFT) {
            x -= 20;
        }
        tempPosition = new Location(x, y);
        moveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (tempPosition == null) {
                    try {
                        synchronized (this) {
                            wait(1);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
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
    public void moveTo(Location location) {
        // This method is mainly for the not-you player, so that the Network can easily update that player
        while (!position.equals(location)) {
            if (location.getX() > position.getX()) {
                move(Player.direction.RIGHT);
            } else if (location.getX() < position.getX()) {
                move(Player.direction.LEFT);
            }
        }
    }
}
