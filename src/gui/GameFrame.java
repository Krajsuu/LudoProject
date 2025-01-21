package gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import model.Game;
import model.Pawn;
import model.User;
import components.UserInfo;
import interfaces.PanelsInterface;
import model.Player;

public class GameFrame implements PanelsInterface {
    private JPanel mainPanel;
    private JPanel playersPanel;
    private JPanel buttonsPanel;
    private JPanel boardPanel;
    private JPanel dicePanel;
    private int diceValue;
    private JButton SaveButton;
    private JButton quitButton;
    private JButton diceButton;
    private Game game;
    private JLabel[][] board = new JLabel[13][13];
    private JFrame parentFrame;
    private ArrayList<User> users = new ArrayList<>();

    public GameFrame(JFrame parentFrame, ArrayList<User> users) {
        this.parentFrame = parentFrame;
        this.users = users;
        this.game = new Game(users, this);

        parentFrame.setSize(1080, 680);
        playersPanel.setLayout(new GridLayout(users.size(), 1, 5, 5));

        for (User user : users) {
            playersPanel.add(new JPanel().add(new UserInfo(user)));
        }

        //Kostka
        diceButton.setText("");
        diceButton.setIcon(new ImageIcon(new ImageIcon("data/images/diceImages/1.png")
                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        diceButton.addActionListener(e -> {
            this.diceValue = game.rollDice();

            if (diceValue < 1 || diceValue > 6) {
                System.err.println("Invalid dice value " + diceValue);
                return;
            }

            String imagePath = "data/images/diceImages/" + diceValue + ".png";
            diceButton.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        });

        SaveButton.addActionListener(e -> saveGame());

        quitButton.setText("BACK TO MAIN MENU");
        quitButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null,
                    "CZY NAPEWNO CHCESZ OPUSCIC ROZGRYWKE? NIE ZAPISANA GRA ZOSTANIE UTRACONA",
                    "Potwierdź wyjście",
                    JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                parentFrame.setContentPane(((MenuFrame) parentFrame).getOriginalPanel());
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
    }

    private void saveGame() {
        int response = JOptionPane.showConfirmDialog(null,
                "Czy na pewno chcesz zapisać stan gry?",
                "Potwierdź zapis",
                JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            String saveName = JOptionPane.showInputDialog("Podaj nazwę zapisu:");
            if (saveName == null || saveName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nazwa zapisu nie może być pusta.", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("saves/" + saveName + ".ser"))) {
                oos.writeObject(game);
                JOptionPane.showMessageDialog(null, "Stan gry zapisany pomyślnie.", "Sukces", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Błąd zapisu gry: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void createUIComponents() {
        mainPanel = new JPanel();
        playersPanel = new JPanel();
        buttonsPanel = new JPanel();
        boardPanel = new JPanel();
        dicePanel = new JPanel();
        plansza();
        SaveButton = new JButton("Save Game");
        quitButton = new JButton("BACK TO MAIN MENU");
        diceButton = new JButton();
    }

    private void plansza(){
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
                    //kolorowanko
                    switch (row) {
                        case 0:
                        case 12: {
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
                        case 10: {
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
                                    field.setBackground(row >= 1 && row <= 3 ? Color.YELLOW : Color.BLUE);
                                }
                            }
                            if ((row == 1 && col == 7) || (row == 11 && col == 5)) {
                                field.setBackground(col == 7 ? Color.YELLOW : Color.RED);
                            }
                        }
                        break;
                        case 4:
                        case 8: {
                            if (col == 5 || col == 7) {
                                field.setBackground(Color.LIGHT_GRAY);
                            } else {
                                if (col == 6) {
                                    field.setBackground(row == 4 ? Color.YELLOW : Color.RED);
                                } else if (col >= 0 && col <= 4) {
                                    field.setBackground(row == 4 ? Color.GREEN : Color.RED);
                                } else {
                                    field.setBackground(row == 4 ? Color.YELLOW : Color.BLUE);
                                }
                            }
                        }
                        break;
                        case 5:
                            if (col == 6) {
                                field.setBackground(Color.YELLOW);
                            } else if (col == 1) {
                                field.setBackground(Color.GREEN);
                            } else {
                                field.setBackground(Color.LIGHT_GRAY);
                            }
                            break;
                        case 6:
                            if (col == 0 || col == 12) {
                                field.setBackground(Color.LIGHT_GRAY);
                            } else if (col == 6) {
                                field.setBackground(Color.BLACK);
                            } else {
                                field.setBackground((col >= 1 && col <= 5) ? Color.GREEN : Color.BLUE);
                            }
                            break;
                        case 7:
                            if (col == 6) {
                                field.setBackground(Color.RED);
                            } else if (col == 11) {
                                field.setBackground(Color.BLUE);
                            } else {
                                field.setBackground(Color.LIGHT_GRAY);
                            }
                            break;
                    }
                    board[row][col] = field;
                    field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    mainBoardPanel.add(field);
                }
            }
            boardPanel.add(mainBoardPanel, BorderLayout.CENTER);
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
    public void setPawn(Pawn pawn) {
        Point p = pawn.getCurrentPosition();
        board[p.x][p.y].setIcon(pawn.getPawnIcon());
    }
}
