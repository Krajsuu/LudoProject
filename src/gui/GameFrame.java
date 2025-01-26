package gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import model.Game;
import model.Pawn;
import model.Player;
import model.User;
import components.UserInfo;
import interfaces.PanelsInterface;
import model.Logs;
public class GameFrame implements PanelsInterface {
    private JPanel mainPanel;
    private JPanel playersPanel;
    private JPanel buttonsPanel;
    private JPanel boardPanel;
    private JPanel dicePanel;
    private JButton SaveButton;
    private JButton quitButton;
    private JButton diceButton;
    private Game game;
    private JPanel[][] board = new JPanel[13][13];
    private JFrame parentFrame;
    private ArrayList<User> users = new ArrayList<>();
    private int diceValue;

    // NOWOŚĆ: przechowujemy listę UserInfo, żeby móc później aktualizować liczbę pionków
    private ArrayList<UserInfo> userInfos;

    public GameFrame(JFrame parentFrame, ArrayList<User> users) {
        Logs.writeLog("GameFrame created");
        this.parentFrame = parentFrame;
        this.users = users;
        this.game = new Game(users, this);

        parentFrame.setSize(1080, 680);
        playersPanel.setLayout(new GridLayout(users.size(), 1, 5, 5));

        userInfos = new ArrayList<>();

        // Zamiast dodawać "new JPanel().add(...)" zapiszmy referencję, aby móc później aktualizować
        for (User user : users) {
            UserInfo ui = new UserInfo(user);
            playersPanel.add(ui);
            userInfos.add(ui);
        }
        Logs.writeLog("UserInfos created");
        // Inicjalnie odświeżamy licznik pionków
        // (np. dla gier wczytanych z pliku może się różnić od 4)
        for (int i = 0; i < users.size(); i++) {
            Player p = (Player) users.get(i);
            userInfos.get(i).setPawnsCount(p.getPawnsRemaining());
        }
        Logs.writeLog("Pawns count updated");
        //Kostka
        diceButton.setText("");
        diceButton.setIcon(new ImageIcon(new ImageIcon("data/images/diceImages/1.png")
                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        diceButton.addActionListener(e -> {
            game.thisTurn(this.diceValue);
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
                Logs.writeLog("Exiting game to main menu");
                parentFrame.setContentPane(((MenuFrame) parentFrame).getOriginalPanel());
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
    }

    public void setDiceValue(int value){
        this.diceValue = value;
    }

    public void setDiceButtonImage(String imagePath){
        diceButton.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
    }

    // Metoda pozwalająca w dowolnym momencie odświeżyć liczbę pionków wyświetlaną dla danego gracza
    public void updatePlayerPawnsCount(Player player) {
        // Szukamy indexu obiektu Player w naszej liście users (tam są oryginalne obiekty typu Player/Bot)
        int index = users.indexOf(player);
        if (index != -1) {
            userInfos.get(index).setPawnsCount(player.getPawnsRemaining());
        }
    }

    public void diceclickability(boolean clickable) {
        diceButton.setEnabled(clickable);
    }

    public boolean getdiceclickability() {
        return diceButton.isEnabled();
    }

    private void saveGame() {
        int response = JOptionPane.showConfirmDialog(null,
                "Czy na pewno chcesz zapisać stan gry?",
                "Potwierdź zapis",
                JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            Logs.writeLog("Saving game state");
            String saveName = JOptionPane.showInputDialog("Podaj nazwę zapisu:");
            if (saveName == null || saveName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nazwa zapisu nie może być pusta.", "Błąd", JOptionPane.ERROR_MESSAGE);
                Logs.writeLog("ERROR - Save name cannot be empty");
                return;
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("saves/" + saveName + ".ser"))) {
                oos.writeObject(game);
                JOptionPane.showMessageDialog(null, "Stan gry zapisany pomyślnie.", "Sukces", JOptionPane.INFORMATION_MESSAGE);
                Logs.writeLog("Game state saved successfully");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Błąd zapisu gry: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                Logs.writeLog("Error saving game state: " + ex.getMessage());
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
        Logs.writeLog("UI components created");
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
                JPanel field = new JPanel();
                field.setOpaque(true);

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
                this.board[row][col] = field;
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
        board[p.x][p.y].add(pawn.getPawnComponent());
    }

    public void removePawn(Pawn pawn) {
        Point p = pawn.getCurrentPosition();
        board[p.x][p.y].remove(pawn.getPawnComponent());

        board[p.x][p.y].revalidate();
        board[p.x][p.y].repaint();
    }

    public void movePawn(Pawn pawn, Point newPosition) {
        removePawn(pawn);
        pawn.setCurrentPosition(newPosition);
        setPawn(pawn);
        board[ newPosition.x][ newPosition.y].revalidate();
        board[ newPosition.x][ newPosition.y].repaint();
    }

    public void updateDicePanelColor(Player player){
        Color color = player.getColor();
        dicePanel.setBackground(color);
        dicePanel.revalidate();
        dicePanel.repaint();
    }
}
