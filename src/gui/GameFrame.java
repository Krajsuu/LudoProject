package gui;

import javax.swing.*;
import java.awt.*;
import model.User;
import components.UserInfo;
import interfaces.PanelsInterface;
import java.util.ArrayList;
import model.*;

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

    private JFrame parentFrame;
    //private JPanel[] playersBaners; // 4 banery graczy

    private ArrayList<User> users = new ArrayList<User>();


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


                field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                mainBoardPanel.add(field);
            }
        }
        boardPanel.add(mainBoardPanel, BorderLayout.CENTER);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


    public GameFrame(JFrame parentFrame, ArrayList<User> users) {
        this.parentFrame = parentFrame;
        this.users = users;
        //gra
        this.game = new Game(users);
        createUIComponents();
        createPawnGridOverlay();
        parentFrame.setSize(1080, 680);
        playersPanel.setLayout(new GridLayout(users.size(), 1, 5, 5));
        for (int i = 0; i < users.size(); i++) {
            playersPanel.add(new JPanel().add(new UserInfo(users.get(i))));
        }
        diceButton.setText("");
        diceButton.setIcon(new ImageIcon(new ImageIcon("data/images/diceImages/1.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        //losowanie kostka diceValue
        diceButton.addActionListener(e -> {
            this.diceValue = game.rollDice();

            if (diceValue < 1 || diceValue > 6) {
                System.err.println("Invalid dice value " + diceValue);
                return;
            }

            String imagePath = "data/images/diceImages/" + diceValue + ".png";
            diceButton.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        });
    }

    private void createPawnGridOverlay() {
        if (game == null) {
            System.err.println("Game is not initialized.");
            return;
        }


        JPanel overlayPanel = new JPanel();
        overlayPanel.setLayout(new GridLayout(13, 13));
        overlayPanel.setOpaque(false);


        JLabel[][] overlayFields = new JLabel[13][13];
        for (int row = 0; row < 13; row++) {
            for (int col = 0; col < 13; col++) {
                JLabel field = new JLabel();
                field.setHorizontalAlignment(SwingConstants.CENTER);
                field.setVerticalAlignment(SwingConstants.CENTER);
                field.setOpaque(false);
                overlayFields[row][col] = field;
                overlayPanel.add(field);
            }
        }

        // Umiesc pionki na planszy
        for (Player player : game.getPlayers()) {
            ArrayList<Pawn> pawns = new ArrayList<>();
            if (player.getColor() == Color.RED) {
                pawns = game.getRedPawns();
            } else if (player.getColor() == Color.BLUE) {
                pawns = game.getBluePawns();
            } else if (player.getColor() == Color.YELLOW) {
                pawns = game.getYellowPawns();
            } else if (player.getColor() == Color.GREEN) {
                pawns = game.getGreenPawns();
            }

            for (Pawn pawn : pawns) {
                Point position = pawn.getCurrentPosition();
                if (position.x >= 0 && position.x < 13 && position.y >= 0 && position.y < 13) {
                    overlayFields[position.x][position.y].setIcon(pawn.getPawnIcon());
                } else {
                    System.err.println("Pawn position out of bounds: " + position);
                }
            }
        }


        boardPanel.add(overlayPanel, BorderLayout.CENTER);


        boardPanel.revalidate();
        boardPanel.repaint();
    }

}
