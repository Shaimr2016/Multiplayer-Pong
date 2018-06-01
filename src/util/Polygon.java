package util;

import java.awt.*;

public class Polygon extends java.awt.Polygon {
    public Polygon(int x, int y, Dimension d) {
        super (
                new int[] {x, x + d.width, x + d.width, x}, new int[] {y, y, y + d.height, y + d.height}, 4
        );
    }
}
