package entity;

import main.Main;
import objectdraw.DrawingCanvas;
import objectdraw.FilledRect;
import util.Polygon;
import java.awt.Dimension;

public class Player extends Entity {

    public boolean isYou;
    private FilledRect graphic;
    private DrawingCanvas canvas;
    public enum direction { RIGHT, LEFT }

    public Player(boolean isYou, int y) {
        this.canvas = Main.getGame().getCanvas();
        this.isYou = isYou;
        this.bounds = new Polygon(Main.getGame().getCanvas().getWidth() / 2, y, new Dimension(200, 50));

        graphic = new FilledRect(
                bounds.xpoints[0], bounds.ypoints[0], bounds.xpoints[2], bounds.ypoints[2], canvas);

        start();
    }
    public void run() {
        while(!Main.getGame().isGameEnded()) {
            graphic.removeFromCanvas();
            graphic = new FilledRect(
                    bounds.xpoints[0], bounds.ypoints[0], bounds.xpoints[2], bounds.ypoints[2], canvas);
        }
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
