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

    private JPanel originalPanel;

    public MenuFrame() {
        setTitle("Ludo Game");
        ImageIcon icon = new ImageIcon("data/images/LudoGameIcon.png");
        ImageIcon LudoGameIcon = new ImageIcon(icon.getImage().getScaledInstance(330, 110, Image.SCALE_SMOOTH));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        originalPanel = JPanel1;
        setContentPane(JPanel1);
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

        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("loadGame")) {
                    LoadGameFrame loadGameFrame = new LoadGameFrame(MenuFrame.this);
                    setContentPane(loadGameFrame.getMainPanel());
                    revalidate();
                }
            }
        });

        buttonNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("newGame")) {
                    NewGameFrame newGameFrame = new NewGameFrame(MenuFrame.this);
                    setContentPane(newGameFrame.getMainPanel());
                    revalidate();
                }
            }
        });

        pack();

    }

    public JPanel getOriginalPanel(){
        return originalPanel;
    }




}
