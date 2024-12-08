package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerGUI extends JFrame {
    private Player player;
    private JLabel statusLabel;
    private JLabel diceRollLabel;
    private JLabel positionLabel;
    private JButton startTurnButton;
    private JButton rollDiceButton;
    private JButton moveButton;
    private JButton endTurnButton;

    public PlayerGUI(Player player) {
        this.player = player;
        initializeUI();
    }

    private void initializeUI() {
        // Ustawienia okna
        setTitle("Player Game GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel dla komponentów
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        // Status gracza
        statusLabel = new JLabel("Gracz: " + player.getUsername() + " (" + player.getColor() + ")");
        panel.add(statusLabel);

        // Wyświetlanie rzutu kostką
        diceRollLabel = new JLabel("Wynik rzutu: 0");
        panel.add(diceRollLabel);

        // Wyświetlanie pozycji gracza na planszy
        positionLabel = new JLabel("Pozycja gracza: 0");
        panel.add(positionLabel);

        // Przycisk do rozpoczęcia tury
        startTurnButton = new JButton("Rozpocznij turę");
        startTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTurn();
            }
        });
        panel.add(startTurnButton);

        // Przycisk do rzutu kostką
        rollDiceButton = new JButton("Rzuć kostką");
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });
        rollDiceButton.setEnabled(false);  // Początkowo zablokowany, dopóki tura nie jest rozpoczęta
        panel.add(rollDiceButton);

        // Przycisk do przesunięcia pionka
        moveButton = new JButton("Przesuń pionek");
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
            }
        });
        moveButton.setEnabled(false);  // Początkowo zablokowany, dopóki rzut nie jest wykonany
        panel.add(moveButton);

        // Przycisk do zakończenia tury
        endTurnButton = new JButton("Zakończ turę");
        endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endTurn();
            }
        });
        endTurnButton.setEnabled(false);  // Początkowo zablokowany, dopóki tura nie jest aktywna
        panel.add(endTurnButton);

        // Dodanie panelu do okna
        add(panel, BorderLayout.CENTER);

        // Aktualizacja statusu
        updateStatus();

        // Ustawienie widoczności okna
        setVisible(true);
    }

    // Rozpoczęcie tury
    private void startTurn() {
        player.startTurn();
        rollDiceButton.setEnabled(true);  // Po rozpoczęciu tury gracz może rzucić kostką
        updateStatus();
    }

    // Rzucenie kostką
    private void rollDice() {
        player.rollDice();
        diceRollLabel.setText("Wynik rzutu: " + player.getDiceRoll());
        moveButton.setEnabled(true);  // Po rzuceniu kostką gracz może przesunąć pionek
        updateStatus();
    }

    // Przesunięcie pionka
    private void move() {
        player.move();
        positionLabel.setText("Pozycja gracza: " + player.getCurrentPosition());
        updateStatus();
    }

    // Zakończenie tury
    private void endTurn() {
        player.endTurn();
        rollDiceButton.setEnabled(false);  // Po zakończeniu tury przycisk rzutu kostką jest zablokowany
        moveButton.setEnabled(false);  // Po zakończeniu tury przycisk przesunięcia pionka jest zablokowany
        startTurnButton.setEnabled(true);  // Możliwość rozpoczęcia nowej tury
        updateStatus();
    }

    // Aktualizacja statusu GUI (pozycja, wynik rzutu, liczba ruchów)
    private void updateStatus() {
        statusLabel.setText("Gracz: " + player.getUsername() + " (" + player.getColor() + ")");
        positionLabel.setText("Pozycja gracza: " + player.getCurrentPosition());
        diceRollLabel.setText("Wynik rzutu: " + player.getDiceRoll());
    }

    // Metoda uruchamiająca GUI
    public static void main(String[] args) {
        Player player = new Player("Gracz1", 1, 4, "Czerwony");
        new PlayerGUI(player);
    }
}
