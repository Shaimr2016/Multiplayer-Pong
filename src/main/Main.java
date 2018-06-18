package main;

import game.Game;
import network.Network;
import util.BeginPane;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static Network network;
    private static Game game;

    public Main(Game game) {
        this.game = game;
        network = new Network();
        new BeginPane();
    }
    public static void replaceContentPane(JPanel panel) {
        game.setContentPane(panel);
        game.getContentPane().setVisible(true);
        game.getContentPane().setSize(Toolkit.getDefaultToolkit().getScreenSize());
        game.getContentPane().revalidate();
        game.getContentPane().repaint();
    }
    public static void startGame() {
        game.run();
    }

    // GETTERS

    public static Container getFrame() {
        return game.getContentPane();
    }
    public static Game getGame() {
        return game;
    }
    public static void emit(String message, Object ... objects) {
        network.emit(message, objects);
    }
}
