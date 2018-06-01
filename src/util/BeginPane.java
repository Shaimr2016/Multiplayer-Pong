package util;

import main.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeginPane {

    private JPanel panel;
    private JButton joinGameButton;
    private JButton settingsButton;

    public BeginPane() {
        addActionListeners();

        Main.replaceContentPane(panel);
    }
    private void addActionListeners() {
        joinGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.emit("join");
                joinGameButton.removeActionListener(this);
            }
        });
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    private void createUIComponents() {
        panel = new ImagePanel("./data/images/background.JPG");
    }
}
