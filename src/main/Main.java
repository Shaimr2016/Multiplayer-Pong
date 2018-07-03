package main;

import game.Game;
import network.Network;
import util.BeginPane;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static Network network;
    private static Game game;
    private static JFrame frame;

    public static void main(String[] args) {
        initFrame();

        network = new Network();
        new BeginPane();
    }
    public static void replaceContentPane(JPanel panel) {
        frame.setContentPane(panel);
        frame.getContentPane().setVisible(true);
        frame.getContentPane().setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }
    public static void initFrame() {
        frame = new JFrame("Multi-player Pong - For no reason whatsoever");

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    public static void startGame() {
        frame.dispose();
        game = new Game();
        game.start();
    }

    // GETTERS

    public static Container getFrame() {
        return frame.getContentPane();
    }
    public static Game getGame() {
        return game;
    }
    public static void emit(String message, Object ... objects) {
        network.emit(message, objects);
        if (message.equals("join")) {
            startGame();
        }
    }
}
