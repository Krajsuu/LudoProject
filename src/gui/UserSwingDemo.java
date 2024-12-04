package game;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa GUI, która wizualizuje dane użytkownika (User) i umożliwia interakcję z nimi.
 */
public class UserSwingDemo extends JFrame {
    private User user; // Obiekt reprezentujący użytkownika
    private JLabel nameLabel; // Etykieta dla nazwy użytkownika
    private JLabel pawnLabel; // Etykieta dla liczby pionków
    private JLabel activeLabel; // Etykieta dla statusu aktywności użytkownika
    private JLabel movesLabel; // Etykieta dla liczby ruchów do wykonania
    private JButton simulateMoveButton; // Przycisk do symulacji wykonania ruchu
    private JButton endTurnButton; // Przycisk do zakończenia tury

    /**
     * Konstruktor klasy UserSwingDemo. Tworzy GUI i integruje go z klasą User.
     */
    public UserSwingDemo(User user) {
        // Inicjalizacja okna
        this.user = user; // Przypisanie użytkownika
        setTitle("User Panel - " + user.getUsername());
        setSize(400, 300); // Rozmiar okna
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Zamknięcie aplikacji po zamknięciu okna
        setLayout(new GridLayout(6, 1)); // Ustawienie układu siatki

        // Tworzenie komponentów GUI
        nameLabel = new JLabel("Username: " + user.getUsername());
        pawnLabel = new JLabel("Pawns in game: " + user.getPawnsInGame() + "/" + user.getTotalPawns());
        activeLabel = new JLabel("Active: " + (user.isActive() ? "Yes" : "No"));
        movesLabel = new JLabel("Moves remaining: " + user.getMovesRemaining());

        // Przycisk symulujący wykonanie ruchu
        simulateMoveButton = new JButton("Simulate Move");
        simulateMoveButton.addActionListener(e -> {
            if (user.getMovesRemaining() > 0) {
                user.updateMovesRemaining(-1); // Zmniejsz liczbę ruchów
                updateLabels(); // Aktualizacja etykiet
            } else {
                JOptionPane.showMessageDialog(this, "No moves remaining!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Przycisk kończący turę gracza
        endTurnButton = new JButton("End Turn");
        endTurnButton.addActionListener(e -> {
            user.setActive(false); // Dezaktywacja gracza
            JOptionPane.showMessageDialog(this, "Turn ended for " + user.getUsername(), "Info", JOptionPane.INFORMATION_MESSAGE);
            updateLabels(); // Aktualizacja etykiet
        });

        // Dodawanie komponentów do okna
        add(nameLabel);
        add(pawnLabel);
        add(activeLabel);
        add(movesLabel);
        add(simulateMoveButton);
        add(endTurnButton);
    }

    /**
     * Aktualizuje etykiety w GUI na podstawie aktualnego stanu użytkownika.
     */
    private void updateLabels() {
        nameLabel.setText("Username: " + user.getUsername());
        pawnLabel.setText("Pawns in game: " + user.getPawnsInGame() + "/" + user.getTotalPawns());
        activeLabel.setText("Active: " + (user.isActive() ? "Yes" : "No"));
        movesLabel.setText("Moves remaining: " + user.getMovesRemaining());
    }

    /**
     * Metoda główna do testowania integracji klasy User z GUI.
     *
     * @param args argumenty wejściowe (niewykorzystane)
     */
    public static void main(String[] args) {
        // Tworzenie przykładowego użytkownika
        User user = new User("Player1", 1, 4);
        user.setActive(true); // Ustawienie użytkownika jako aktywnego
        user.setMovesRemaining(3); // Przyznanie trzech ruchów

        // Uruchomienie GUI
        SwingUtilities.invokeLater(() -> {
            UserSwingDemo demo = new UserSwingDemo(user); // Tworzenie okna dla użytkownika
            demo.setVisible(true); // Wyświetlanie okna
        });
    }
}
