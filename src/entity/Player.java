package entity;

import java.awt.*;

public class Player extends Entity {

    private boolean isYou;
    public enum direction { RIGHT, LEFT }

    public Player(boolean isYou) {
        this.isYou = isYou;
        this.bounds = new Polygon();
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
