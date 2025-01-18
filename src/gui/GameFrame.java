package gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import model.Game;
import model.User;
import components.UserInfo;
import interfaces.PanelsInterface;

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
    private ArrayList<User> users = new ArrayList<>();

    public GameFrame(JFrame parentFrame, ArrayList<User> users) {
        this.parentFrame = parentFrame;
        this.users = users;
        this.game = new Game(users);

        parentFrame.setSize(1080, 680);
        playersPanel.setLayout(new GridLayout(users.size(), 1, 5, 5));

        for (User user : users) {
            playersPanel.add(new JPanel().add(new UserInfo(user)));
        }

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
        SaveButton = new JButton("Save Game");
        quitButton = new JButton("BACK TO MAIN MENU");
        diceButton = new JButton();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
