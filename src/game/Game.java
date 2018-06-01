package game;

import objectdraw.WindowController;

import java.awt.*;

public class Game extends WindowController {



    public Game() {
        setSize(Toolkit.getDefaultToolkit().getScreenSize());

        canvas.requestFocus();
    }
}
