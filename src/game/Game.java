package game;

import entity.Player;
import main.Main;
import objectdraw.DrawingCanvas;
import objectdraw.WindowController;
import util.KeyBind;

import java.awt.*;

public class Game extends WindowController {

    private Player[] players = new Player[2];
    private boolean gameEnded = false;

    public Game() {
        Main main = new Main(this);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
    }

    // GETTERS

    public DrawingCanvas getCanvas() {
        return canvas;
    }
    public Player getPlayer() {
        return players[0];
    }
    public boolean isGameEnded() {
        return gameEnded;
    }
    public void run() {
        canvas.addKeyListener(new KeyBind());
        players[0] = new Player(true, canvas.getHeight() - 100, canvas);
        canvas.requestFocus();
    }
}
