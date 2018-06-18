package entity;

import main.Main;
import objectdraw.DrawingCanvas;
import objectdraw.FilledRect;
import objectdraw.Location;
import util.Polygon;
import java.awt.Dimension;

public class Player extends Entity {

    private boolean isYou;
    private Location position;
    private FilledRect graphic;
    private DrawingCanvas canvas;
    public enum direction { RIGHT, LEFT }

    public Player(boolean isYou, int y, DrawingCanvas canvas) {
        super(canvas.getWidth() / 2, y);
        position = new Location(canvas.getWidth() / 2, y);
        this.canvas = canvas;
        this.isYou = isYou;
        this.bounds = new Polygon(canvas.getWidth() / 2, y, new Dimension(200, 50));

        graphic = new FilledRect(
                bounds.xpoints[0], bounds.ypoints[0], bounds.xpoints[2], bounds.ypoints[2], canvas);

        start();
    }
    public void run() {
        while(!Main.getGame().isGameEnded()) {
            try {
                synchronized (this) {
                    wait(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            graphic.removeFromCanvas();
            graphic = new FilledRect(position, 200, 50, canvas);
        }
        graphic.removeFromCanvas();
    }
    public void move(direction dir) {
        if (!isYou) return;
        if (dir == direction.RIGHT) {
            this.x += 2;
        } else if (dir == direction.LEFT) {
            this.x -= 2;
        }
    }
}
