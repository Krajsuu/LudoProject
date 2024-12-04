package gui;

import javax.swing.*;
import java.awt.*;
public class MenuFrame extends JFrame{
    private JPanel JPanel1;
    private JLabel LabelLudoGame;
    private JLabel LabelWelome;
    private JButton exitButton;
    private JButton loadGameButton;
    private JButton buttonNewGame;

    public MenuFrame() {
        setTitle("Ludo Game Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(JPanel1);
        ImageIcon icon = new ImageIcon("data/images/LudoGameIcon.png");
        ImageIcon LudoGameIcon = new ImageIcon(icon.getImage().getScaledInstance(330, 110, Image.SCALE_SMOOTH));
        LabelLudoGame.setIcon(LudoGameIcon);
        pack();
    }

}
