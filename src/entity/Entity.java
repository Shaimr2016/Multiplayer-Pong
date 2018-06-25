package entity;

import objectdraw.ActiveObject;
import objectdraw.Location;
import util.Polygon;

import java.awt.*;

public class Entity extends ActiveObject {

    int x;
    int y;
    Location position;
    Polygon bounds;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public void run() {

    }
    public Point getLocation() {
        return new Point(x, y);
    }
}
