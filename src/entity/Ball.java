package entity;

import com.sun.istack.internal.Nullable;
import main.Main;
import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.VisibleImage;
import org.json.JSONException;
import org.json.JSONObject;
import util.Polygon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Ball extends Entity {

    private Player owner;

    public Ball(DrawingCanvas canvas) {
        super(canvas.getWidth() / 2, canvas.getHeight() / 2);
        this.canvas = canvas;
        position = new Location(canvas.getWidth() / 2, canvas.getHeight() / 2);
        bounds = new Polygon((int) position.getX(), (int) position.getY(), new Dimension(75, 75));

        try {
            graphic = new VisibleImage(ImageIO.read(new File("data/images/ball.png")),
                        position, 75, 75, canvas
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }

        start();
    }
    @Override
    public void run() {
        super.run();
        checkCollision();
    }

    // COLLISION TRACKING METHODS

    private void checkCollision() {
        if (checkPlayerCollision() != null) {
            Main.emit("player_bounce");
        }
        if (checkWallBounce() != null) {
            Main.emit("wall_bounce");
        }
        if (checkScore() != null) {
            JSONObject j = new JSONObject();
            try {
                j.put("player", checkScore());
                Main.emit("player_scores", j);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private Player checkPlayerCollision() {
        if (bounds.intersects(Main.getGame().getPlayer().bounds.getBounds())) {
            return Main.getGame().getPlayer();
        } else if (bounds.intersects(Main.getGame().getOtherPlayer().bounds.getBounds())) {
            return Main.getGame().getOtherPlayer();
        }
        return null;
    }
    private Player.direction checkWallBounce() {
        if (position.getX() <= 0) {
            return Player.direction.LEFT;
            // collided on left side
        } else if (position.getX() + 75 >= canvas.getWidth()) {
            return Player.direction.RIGHT;
            // collided on right side
            // 75 is width of ball graphic
        }
        return null;
    }
    private Player checkScore() {
        // returns player who scored
        if (position.getY() + 75 >= canvas.getHeight()) {
            return Main.getGame().getOtherPlayer();
        } else if (position.getY() <= 0) {
            return Main.getGame().getPlayer();
        }
        return null;
    }

    // MOVEMENT METHODS

    public void moveTo(Location l) {
        tempPosition = l;
        moveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (tempPosition != null && !tempPosition.equals(position)) {
                    if (tempPosition.getX() > position.getX()) {
                        position.translate(1, 0);
                    } else if (tempPosition.getX() < position.getX()) {
                        position.translate(-1, 0);
                    }
                    if (tempPosition.getY() > position.getY()) {
                        position.translate(0, 1);
                    } else if (tempPosition.getY() < position.getY()) {
                        position.translate(0, -1);
                    }
                    try {
                        synchronized (this) {
                            wait(2);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopMove();
            }
        });
        moveThread.start();
    }
    private void stopMove() {
        moveThread = null;
        tempPosition = null;
    }

    // GETTER / SETTER METHODS

    public Player getOwner() {
        return owner;
    }
}
