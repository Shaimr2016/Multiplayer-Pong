package game;

import entity.Player;
import objectdraw.DrawingCanvas;
import objectdraw.FrameCanvas;
import util.KeyBind;

import java.awt.*;

public class Game extends FrameCanvas {

    private Player[] players = new Player[2];
    private boolean gameEnded = false;

    public Game() {
        setSize(Toolkit.getDefaultToolkit().getScreenSize());

        addKeyListener(new KeyBind());
        players[0] = new Player(true, getHeight() - 100, this);
        requestFocus();
    }

    // GETTERS

    public DrawingCanvas getCanvas() {
        return this;
    }
    public Player getPlayer() {
        return players[0];
    }
    public boolean isGameEnded() {
        return gameEnded;
    }
}
