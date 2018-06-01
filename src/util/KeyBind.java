package util;

import entity.Player;
import main.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBind implements KeyListener {

    static int KEY_RIGHT = KeyEvent.VK_RIGHT;
    static int KEY_LEFT = KeyEvent.VK_LEFT;

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KEY_RIGHT) {
             Main.getGame().getPlayer().move(Player.direction.RIGHT);
        } else if (e.getKeyCode() == KEY_LEFT) {
            Main.getGame().getPlayer().move(Player.direction.LEFT);
            Main.emit("move", Main.getGame().getPlayer().getLocation());
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

}