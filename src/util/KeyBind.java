package util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBind implements KeyListener {

    static int KEY_RIGHT = KeyEvent.VK_RIGHT;
    static int KEY_LEFT = KeyEvent.VK_LEFT;

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KEY_RIGHT) {
            // Game.getPlayer().move(Player.direction.RIGHT);
        } else if (e.getKeyCode() == KEY_LEFT) {
            // Game.getPlayer().move(Player.direction.LEFT);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}