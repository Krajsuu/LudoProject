package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MenuPanel extends JPanel{
    private JPanel JPanel1;
    private JLabel LabelLudoGame;
    private JLabel LabelWelome;
    private JButton exitButton;
    private JButton loadGameButton;
    private JButton buttonNewGame;

    private void prepareUI() {
        ImageIcon icon = new ImageIcon("data/images/LudoGameIcon.png");
        ImageIcon LudoGameIcon = new ImageIcon(icon.getImage().getScaledInstance(330, 110, Image.SCALE_SMOOTH));
        LabelLudoGame.setIcon(LudoGameIcon);

        exitButton.setActionCommand("exit");
        loadGameButton.setActionCommand("loadGame");
        buttonNewGame.setActionCommand("newGame");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("exit")) {
                    System.exit(0);
                }
            }
        });


    }



    public MenuPanel(MainFrame frame) {


        prepareUI();
    }




}
