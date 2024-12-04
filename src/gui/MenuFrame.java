package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MenuFrame extends JFrame{
    private JPanel JPanel1;
    private JLabel LabelLudoGame;
    private JLabel LabelWelome;
    private JButton exitButton;
    private JButton loadGameButton;
    private JButton buttonNewGame;

    private void prepareUI() {
        setTitle("Ludo Game Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(JPanel1);
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

        pack();
    }



    public MenuFrame() {


        prepareUI();
    }




}
