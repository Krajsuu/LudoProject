package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import interfaces.PanelsInterface;
import model.User;
import model.Player;
import model.Bot;
import utils.SwingColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewGameFrame implements PanelsInterface {
    private class HintText extends JTextField {
        private final String hint;
        private boolean showingHint;

        /**
         * Konstruktor bezargumentowy (domyślny), wymagany przez IntelliJ
         */
        public HintText() {
            this.hint = ""; // Domyślna podpowiedź
            this.showingHint = true;
            initializeHint();
        }

        /**
         * Konstruktor z podpowiedzią jako argument
         *
         * @param hint Podpowiedź
         */
        public HintText(final String hint) {
            this.hint = hint;
            this.showingHint = true;
            initializeHint();
        }

        /**
         * Metoda inicjalizująca hint i listenery
         */
        private void initializeHint() {
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
    private JTextField ddddTextField;

    private ArrayList<User> users = new ArrayList<>();
    private JFrame parentFrame;

    // *** Usuwamy stare `amountOfPlayers`, a w zamian będziemy pobierać kolory z listy. ***
    // private int amountOfPlayers = 0; // usuwamy to, bo często powodowało duplikaty

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

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame gameFrame = new GameFrame(parentFrame, users);
                parentFrame.setContentPane(gameFrame.getMainPanel());
                parentFrame.revalidate();
            }
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
        int botPlayers   = Integer.parseInt((String) BotPlayerComboBox.getSelectedItem());

        // Suma graczy nie może przekraczać 4
        if (humanPlayers + botPlayers > 4) {
            JOptionPane.showMessageDialog(null, "Za dużo graczy! Max 4.", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Przygotowujemy listę dostępnych kolorów: [RED, BLUE, GREEN, YELLOW]
        List<Color> colorPool = new ArrayList<>(Arrays.asList(
                Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW
        ));

        // Dodajemy graczy-ludzi
        for (int i = 0; i < humanPlayers; i++) {
            JLabel humanLabel = new JLabel("Gracz " + (i + 1) + " (Człowiek): ");
            HintText humanTextField = new HintText("Podaj nazwę dla Gracza " + (i + 1));

            JPanel humanPanel = new JPanel();
            humanPanel.setLayout(new BoxLayout(humanPanel, BoxLayout.Y_AXIS));
            humanPanel.add(humanLabel);
            humanPanel.add(humanTextField);

            // Pobieramy pierwszy wolny kolor z puli
            Color assignedColor = colorPool.isEmpty() ? Color.BLACK : colorPool.remove(0);

            Player player = new Player("Gracz " + (i + 1), assignedColor);
            users.add(player);

            humanTextField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {}
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
            HintText botTextField = new HintText("Podaj nazwę dla Bota " + (i + 1));

            JPanel botPanel = new JPanel();
            botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.Y_AXIS));
            botPanel.add(botLabel);
            botPanel.add(botTextField);

            // Pobieramy pierwszy wolny kolor z puli
            Color assignedColor = colorPool.isEmpty() ? Color.BLACK : colorPool.remove(0);

            Bot bot = new Bot("Bot " + (i + 1), assignedColor);
            users.add(bot);

            botTextField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {}
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
