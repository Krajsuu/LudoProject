package gui;

import javax.swing.*;
import java.awt.*;

public class GameFrame {
    private JPanel mainPanel;
    private JPanel playersPanel;
    private JPanel buttonsPanel;
    private JPanel boardPanel;
    private JPanel dicePanel;
    private JButton SaveButton;
    private JButton quitButton;
    private JButton button1;
    //private JPanel[] playersBaners; // 4 banery graczy







    private void createUIComponents() {
        // Główny panel planszy
        boardPanel = new JPanel();
        boardPanel.setLayout(new BorderLayout());

        // Panel dla środkowej części planszy (główne pole gry)
        JPanel mainBoardPanel = new JPanel(new GridLayout(13, 13));
        mainBoardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Tworzenie pól planszy
        for (int row = 0; row < 13; row++) {
            for (int col = 0; col < 13; col++) {
                JLabel field = new JLabel();
                field.setHorizontalAlignment(SwingConstants.CENTER);
                field.setVerticalAlignment(SwingConstants.CENTER);
                field.setOpaque(true); // Umożliwia ustawienie koloru

                switch (row){
                    case 0:
                    case 12:{
                        if (col >= 0 && col <= 4) {
                            field.setBackground(row == 0 ? Color.GREEN : Color.RED);
                        } else if (col >= 8 && col <= 12) {
                            field.setBackground(row == 0 ? Color.YELLOW : Color.BLUE);
                        } else {
                            field.setBackground(Color.LIGHT_GRAY);
                        }
                    }
                    break;
                    case 1:
                    case 11:
                    case 2:
                    case 3:
                    case 9:
                    case 10:{
                        if ((col >= 1 && col <= 3) || (col >= 9 && col <= 11)) {
                            field.setBackground(Color.WHITE);
                        } else if (col == 5 || col == 7) {
                            field.setBackground(Color.LIGHT_GRAY);
                        } else {
                            if (col == 6) {
                                field.setBackground(row >= 1 && row <= 3 ? Color.YELLOW : Color.RED);
                            } else if (col == 0 || col == 4) {
                                field.setBackground(row >= 1 && row <= 3 ? Color.GREEN : Color.RED);
                            } else {
                                field.setBackground(row >= 1 && row <= 3  ? Color.YELLOW : Color.BLUE);
                            }
                        }
                        if((row == 1 && col == 7)|| (row == 11 && col == 5)){
                            field.setBackground(col == 7 ? Color.YELLOW : Color.RED);

                        }
                    }
                    break;
                    case 4:
                    case 8:{
                        if (col == 5 || col == 7) {
                            field.setBackground(Color.LIGHT_GRAY);
                        } else {
                            if(col == 6){
                                field.setBackground(row == 4 ? Color.YELLOW : Color.RED);
                            } else if (col >=0 && col <= 4){
                                field.setBackground(row == 4 ? Color.GREEN : Color.RED);
                            } else {
                                field.setBackground(row == 4 ? Color.YELLOW : Color.BLUE);
                            }
                        }
                    }
                    break;
                    case 5:
                        if(col == 6) {
                            field.setBackground(Color.YELLOW);
                        } else if(col == 1) {
                            field.setBackground(Color.GREEN);
                        } else {
                            field.setBackground(Color.LIGHT_GRAY);
                        }
                        break;
                    case 6:
                        if(col == 0 || col == 12){
                            field.setBackground(Color.LIGHT_GRAY);
                        } else if(col == 6){
                            field.setBackground(Color.BLACK);
                        } else {
                            field.setBackground((col >= 1 && col <= 5) ? Color.GREEN : Color.BLUE);
                        }
                        break;
                    case 7:
                        if(col == 6){
                            field.setBackground(Color.RED);
                        } else if(col == 11){
                            field.setBackground(Color.BLUE);
                        } else {
                            field.setBackground(Color.LIGHT_GRAY);
                        }
                        break;


                }



                field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                mainBoardPanel.add(field);
            }
        }
        boardPanel.add(mainBoardPanel, BorderLayout.CENTER);
    }



    public GameFrame() {
        createUIComponents();
    }


    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        JFrame frame = new JFrame("Ludo Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(gameFrame.mainPanel);
        frame.pack();
        frame.setVisible(true);

    }
}
