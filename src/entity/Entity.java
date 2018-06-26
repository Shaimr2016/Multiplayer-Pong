package entity;

import main.Main;
import objectdraw.ActiveObject;
import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.VisibleImage;
import util.Polygon;

import java.awt.*;

public class Entity extends ActiveObject {

    int x;
    int y;
    Location position;
    Polygon bounds;

    Thread moveThread;
    Location tempPosition;

    VisibleImage graphic;
    DrawingCanvas canvas;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // OVERRIDES

    @Override
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

    // GETTERS / SETTERS

    public Location getLocation() {
        return position;
    }
    public void setLocation(Location l) {
        position = l;
    }
}
