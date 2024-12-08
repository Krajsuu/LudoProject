package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;



    public MainFrame(){
        setTitle("Ludo Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);


        setContentPane(mainPanel);
        showPanel("Menu");
    }

    public void showPanel(String panelName){
        cardLayout.show(mainPanel, panelName);
    }
}
