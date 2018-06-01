package main;

import network.Network;
import javax.swing.*;
import java.awt.*;

public class Main {

    private static Network network;
    private static JFrame frame;

    public static void main(String[] args) {
        initFrame();
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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.validate();
        frame.repaint();
    }
}
