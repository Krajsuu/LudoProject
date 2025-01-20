package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import interfaces.PanelsInterface;
import model.User;
import model.Player;
import model.Bot;
import utils.SwingColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Klasa NewGameFrame odpowiada za wyświetlenie okna do rozpoczęcia nowej gry.
 * Umożliwia wybór liczby graczy oraz ich nazw.
 */
public class NewGameFrame implements PanelsInterface {
    private class HintText extends JTextField {
        private final String hint;
        private boolean showingHint;

        public HintText(final String hint) {
            this.hint = hint;
            this.showingHint = true;

            setText(hint);
            setForeground(Color.GRAY);
            setFont(getFont().deriveFont(Font.BOLD));

            addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (showingHint) {
                        setText("");
                        setForeground(Color.BLACK);
                        showingHint = false;
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().isEmpty()) {
                        setText(hint);
                        setForeground(Color.GRAY);
                        showingHint = true;
                    }
                }
            });
        }

        @Override
        public String getText() {
            return showingHint ? "" : super.getText();
        }
    }

    private JPanel NewGamePanel;
    private JLabel TopLabel;
    private JPanel choiceMenu;
    private JPanel playersPanel;
    private JComboBox BotPlayerComboBox;
    private JComboBox HumanPlayerComboBox;
    private JButton backButton;
    private JButton startGameButton;
    private JTextField ddddTextField; // UWAGA: pole ddddTextField zachowane, mimo że nie jest używane w kodzie

    private ArrayList<User> users = new ArrayList<>();
    private JFrame parentFrame;

    public NewGameFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        HumanPlayerComboBox.setModel(new DefaultComboBoxModel(new String[]{"0", "1", "2", "3", "4"}));
        BotPlayerComboBox.setModel(new DefaultComboBoxModel(new String[]{"0", "1", "2", "3", "4"}));

        HumanPlayerComboBox.addActionListener(e -> updatePlayersFields());
        BotPlayerComboBox.addActionListener(e -> updatePlayersFields());

        backButton.addActionListener(e -> {
            parentFrame.setContentPane(((MenuFrame) parentFrame).getOriginalPanel());
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        startGameButton.addActionListener(e -> {
            if (users.size() < 4) {
                JOptionPane.showMessageDialog(null,
                        "Za mało graczy! W grze musi być dokładnie 4 graczy (łącznie boty i ludzie).",
                        "Błąd",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Sprawdzamy, czy każdy gracz ma przypisaną nazwę
            for (User user : users) {
                if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Każdy gracz musi mieć przypisaną nazwę!",
                            "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Sprawdzamy, czy wszystkie nazwy graczy są unikalne
            Set<String> uniqueNames = new HashSet<>();
            for (User user : users) {
                if (!uniqueNames.add(user.getUsername())) {
                    JOptionPane.showMessageDialog(null,
                            "Nazwy graczy muszą być unikalne! Powtarzająca się nazwa: " + user.getUsername(),
                            "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Wszystkie warunki spełnione, można rozpocząć grę
            GameFrame gameFrame = new GameFrame(parentFrame, users);
            parentFrame.setContentPane(gameFrame.getMainPanel());
            parentFrame.revalidate();
        });
    }

    public JPanel getMainPanel() {
        return NewGamePanel;
    }

    /**
     * Metoda updatePlayersFields aktualizuje listę (users),
     * tworząc graczy i boty z unikatowymi kolorami spośród {RED, BLUE, GREEN, YELLOW}.
     */
    private void updatePlayersFields() {
        playersPanel.removeAll();
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));
        users.clear();

        int humanPlayers = Integer.parseInt((String) HumanPlayerComboBox.getSelectedItem());
        int botPlayers = Integer.parseInt((String) BotPlayerComboBox.getSelectedItem());

        if (humanPlayers + botPlayers > 4) {
            JOptionPane.showMessageDialog(null, "Za dużo graczy! Max 4.", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Color> colorPool = new ArrayList<>(Arrays.asList(
                Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED
        ));

        // Dodajemy graczy-ludzi
        for (int i = 0; i < humanPlayers; i++) {
            JLabel humanLabel = new JLabel("Gracz " + (i + 1) + " (Człowiek): ");
            HintText humanTextField = new HintText("Wprowadź nazwę gracza");

            JPanel humanPanel = new JPanel();
            humanPanel.setLayout(new BoxLayout(humanPanel, BoxLayout.Y_AXIS));
            humanPanel.add(humanLabel);
            humanPanel.add(humanTextField);

            Color assignedColor = colorPool.isEmpty() ? Color.BLACK : colorPool.remove(0);
            Player player = new Player("", assignedColor); // Pusta nazwa domyślna
            users.add(player);

            humanTextField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    player.setUsername(humanTextField.getText());
                }
            });

            playersPanel.add(humanPanel);
        }

        // Dodajemy graczy-botów
        for (int i = 0; i < botPlayers; i++) {
            JLabel botLabel = new JLabel("Gracz " + (humanPlayers + i + 1) + " (Bot): ");
            HintText botTextField = new HintText("Wprowadź nazwę bota");

            JPanel botPanel = new JPanel();
            botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.Y_AXIS));
            botPanel.add(botLabel);
            botPanel.add(botTextField);

            Color assignedColor = colorPool.isEmpty() ? Color.BLACK : colorPool.remove(0);
            Bot bot = new Bot("", assignedColor); // Pusta nazwa domyślna
            users.add(bot);

            botTextField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    bot.setUsername(botTextField.getText());
                }
            });

            playersPanel.add(botPanel);
        }

        playersPanel.revalidate();
        playersPanel.repaint();
    }
}
