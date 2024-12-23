package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BackGroundPanel extends JPanel {
    private BufferedImage backgroundImage;

    public BackGroundPanel() {
        try {

            backgroundImage = ImageIO.read(new File("assets/GameBackGround/Ludo_board.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {

            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
