package util;

import entity.Player;
import main.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBind implements KeyListener {

    static int KEY_RIGHT = KeyEvent.VK_RIGHT;
    static int KEY_LEFT = KeyEvent.VK_LEFT;

    private Player player;

    public KeyBind(Player p) {
        player = p;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KEY_RIGHT) {
            player.move(Player.direction.RIGHT);
            Main.emit("move", player.getLocation());
        } else if (e.getKeyCode() == KEY_LEFT) {
            player.move(Player.direction.LEFT);
            Main.emit("move", player.getLocation());
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KEY_RIGHT || e.getKeyCode() == KEY_LEFT) {
            player.stopMove();
        }
    }

}