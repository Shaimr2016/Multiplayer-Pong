package main;

import game.Game;
import network.Network;
import util.BeginPane;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static Network network;
    private static JFrame frame;
    private static Game game;

    public static void main(String[] args) {
        initFrame();
        network = new Network();
        new BeginPane();
    }
    private static void initFrame() {
        frame = new JFrame("Multi-player Pong LoL");

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void replaceContentPane(JPanel panel) {
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.revalidate();
        frame.repaint();
    }

    public static void startGame() {
        game = new Game();
    }

    // GETTERS

    public static JFrame getFrame() {
        return frame;
    }
    public static Game getGame() {
        return game;
    }
    public static void emit(String message, Object ... objects) {
        network.emit(message, objects);
    }
}
