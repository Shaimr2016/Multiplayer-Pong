package entity;

import objectdraw.ActiveObject;
import util.Polygon;

import java.awt.*;

public class Entity extends ActiveObject {

    int x;
    int y;
    Polygon bounds;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
        start();
    }
    @Override
    public void run() {

    }
    public Point getLocation() {
        return new Point(x, y);
    }
}
