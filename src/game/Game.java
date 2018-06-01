package game;

import entity.Player;
import objectdraw.DrawingCanvas;
import objectdraw.WindowController;
import util.KeyBind;

import java.awt.*;

public class Game extends WindowController {

    private Player[] players = new Player[2];
    private boolean gameEnded = false;

    public Game() {
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        canvas.addKeyListener(new KeyBind());
        players[0] = new Player(true, canvas.getHeight() - 100);
        canvas.requestFocus();
    }
    public DrawingCanvas getCanvas() {
        return canvas;
    }
    public Player getPlayer() {
        return players[0];
    }
    public boolean isGameEnded() {
        return gameEnded;
    }
}
