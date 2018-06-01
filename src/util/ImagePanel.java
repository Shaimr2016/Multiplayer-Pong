package util;

import main.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {

    private String path;

    public ImagePanel(String path) {
        super();
        this.path = path;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            Image image = ImageIO.read(new File(path));
            image = image.getScaledInstance(Main.getFrame().getWidth(), Main.getFrame().getHeight(), Image.SCALE_DEFAULT);
            g.drawImage(image, (Main.getFrame().getWidth() / 2) - (image.getWidth(null) / 2),
                    (Main.getFrame().getHeight() / 2) - (image.getHeight(null) / 2), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
