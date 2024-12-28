package gui;

import javax.swing.*;
import java.awt.*;

public class GameFrame {
    private JPanel mainPanel;
    private JPanel LeftUpCornerPanel;
    private JPanel BoardPanel;
    private JLabel playerIcon1;
    private JLabel playerName1;
    private JPanel RightUpCornerPanel;
    private JLabel playerIcon2;
    private JLabel playerName2;
    private JPanel LeftDownCornerPanel;
    private JLabel playerIcon3;
    private JLabel playerName3;
    private JLabel playerIcon4;
    private JLabel playerName4;
    private JPanel boardPanel;

    GameFrame(){
        createUIComponents();
    }

    private void createUIComponents() {
        // Główny panel planszy
        boardPanel = new JPanel();
        boardPanel.setLayout(new BorderLayout());

        // Panel dla środkowej części planszy (główne pole gry)
        JPanel mainBoardPanel = new JPanel(new GridLayout(11, 11));
        mainBoardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Tworzenie pól planszy
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                JLabel field = new JLabel();
                field.setHorizontalAlignment(SwingConstants.CENTER);
                field.setVerticalAlignment(SwingConstants.CENTER);
                field.setOpaque(true); // Umożliwia ustawienie koloru

                // Logika ustalająca kolor pól
                if ((row == 5 || col == 5) && !(row == 5 && col == 5)) {
                    // Główne pole gry (krzyż)
                    field.setBackground(Color.WHITE);
                } else if (row < 5 && col < 5) {
                    // Baza gracza niebieskiego
                    field.setBackground(Color.BLUE);
                } else if (row < 5 && col > 5) {
                    // Baza gracza czerwonego
                    field.setBackground(Color.RED);
                } else if (row > 5 && col < 5) {
                    // Baza gracza żółtego
                    field.setBackground(Color.YELLOW);
                } else if (row > 5 && col > 5) {
                    // Baza gracza zielonego
                    field.setBackground(Color.GREEN);
                } else {
                    // Pola poza planszą
                    field.setBackground(Color.LIGHT_GRAY);
                }

                field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                mainBoardPanel.add(field);
            }
        }

        // Panel dla graczy (informacje o graczach)
        JPanel playerInfoPanel = new JPanel(new GridLayout(2, 2)); // Gracze w rogach
        playerInfoPanel.setPreferredSize(new Dimension(200, 200));

        JLabel player1Info = new JLabel("Gracz 1");
        player1Info.setHorizontalAlignment(SwingConstants.CENTER);
        player1Info.setIcon(new ImageIcon("blue_icon.png")); // Dodaj ikonkę gracza
        playerInfoPanel.add(player1Info);

        JLabel player2Info = new JLabel("Gracz 2");
        player2Info.setHorizontalAlignment(SwingConstants.CENTER);
        player2Info.setIcon(new ImageIcon("red_icon.png"));
        playerInfoPanel.add(player2Info);

        JLabel player3Info = new JLabel("Gracz 3");
        player3Info.setHorizontalAlignment(SwingConstants.CENTER);
        player3Info.setIcon(new ImageIcon("yellow_icon.png"));
        playerInfoPanel.add(player3Info);

        JLabel player4Info = new JLabel("Gracz 4");
        player4Info.setHorizontalAlignment(SwingConstants.CENTER);
        player4Info.setIcon(new ImageIcon("green_icon.png"));
        playerInfoPanel.add(player4Info);

        // Panel dla kostki do gry
        JPanel dicePanel = new JPanel(new BorderLayout());
        JButton diceButton = new JButton("Rzuć kostką");
        JLabel diceResult = new JLabel("?");
        diceResult.setFont(new Font("Arial", Font.BOLD, 24));
        diceResult.setHorizontalAlignment(SwingConstants.CENTER);
        dicePanel.add(diceButton, BorderLayout.NORTH);
        dicePanel.add(diceResult, BorderLayout.CENTER);

        // Dodanie elementów do głównego panelu
        boardPanel.add(mainBoardPanel, BorderLayout.CENTER);
        boardPanel.add(playerInfoPanel, BorderLayout.EAST);
        boardPanel.add(dicePanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args){
        GameFrame gameFrame = new GameFrame();
        JFrame frame = new JFrame("Ludo Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(gameFrame.mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
