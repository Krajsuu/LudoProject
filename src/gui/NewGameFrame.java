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
/**
 * Klasa NewGameFrame odpowiada za wyświetlenie okna do rozpoczęcia nowej gry.
 * Umożliwia wybór liczby graczy oraz ich nazw.
 */
public class NewGameFrame implements PanelsInterface {
    /**
     * Klasa HintText dziedzicząca po JTextField, która dodaje podpowiedź do pola tekstowego.
     */
    private class HintText extends JTextField {
        // Deklaracja zmiennych
        private final String hint; // Podpowiedź
        private boolean showingHint; // Czy podpowiedź jest wyświetlana


        /**
         * Konstruktor klasy HintText
         *
         * @param hint Podpowiedź
         */
        public HintText(final String hint) {
            super(hint); // Wywołanie konstruktora klasy nadrzędnej
            this.hint = hint; // Przypisanie podpowiedzi
            this.showingHint = true; // Ustawienie, że podpowiedź jest wyświetlana

            setForeground(Color.GRAY); // Ustawienie koloru tekstu na szary
            setFont(getFont().deriveFont(Font.BOLD)); // Ustawienie czcionki na pogrubioną

            /**
             * Dodanie zdarzenia FocusListener do pola tekstowego.
             * Po uzyskaniu focusu, jeśli pole tekstowe zawiera podpowiedź, to zostanie wyczyszczone.
             * Po utraceniu focusu, jeśli pole tekstowe jest puste, to zostanie wyświetlona podpowiedź.
             */
            this.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (getText().isEmpty()) {
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

        /**
         * Metoda getText zwraca tekst z pola tekstowego, jeśli nie jest wyświetlana podpowiedź.
         * W przeciwnym razie zwraca pusty ciąg znaków.
         *
         * @return tekst z pola tekstowego lub pusty ciąg znaków
         */
        @Override
        public String getText() {
            return showingHint ? "" : super.getText();
        }
    }

    // Deklaracja zmiennych
    private JPanel NewGamePanel; // Panel główny
    private JLabel TopLabel; // Nagłówek
    private JPanel choiceMenu; // Panel wyboru liczby graczy
    private JPanel playersPanel; // Panel z polami do wprowadzenia nazw graczy
    private JComboBox BotPlayerComboBox; // Lista rozwijana z liczbą botów
    private JComboBox HumanPlayerComboBox; // Lista rozwijana z liczbą graczy
    private JButton backButton; // Przycisk powrotu
    private JButton startGameButton;
    private JTextField ddddTextField;
    private ArrayList<User> users = new ArrayList<User>();
    // Tablica z graczami
    private int amountOfPlayers = 0; // Liczba graczy
    private JFrame parentFrame; // Okno nadrzędne

    /**
     * Konstruktor klasy NewGameFrame
     *
     * @param parentFrame Okno nadrzędne
     */
    public NewGameFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame; // Przypisanie okna nadrzędnego

        /**
         * Dodanie zdarzenia ActionListener do przycisku startGameButton.
         * Po kliknięciu przycisku zostanie pobrana liczba graczy oraz ich nazwy.
         * Następnie zostanie utworzona nowa gra z podanymi parametrami.
         */
        HumanPlayerComboBox.setModel(new DefaultComboBoxModel(new String[]{"0", "1", "2", "3", "4"}));
        BotPlayerComboBox.setModel(new DefaultComboBoxModel(new String[]{"0", "1", "2", "3", "4"}));


        HumanPlayerComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePlayersFields();
            }
        });

        BotPlayerComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePlayersFields();
            }
        });
        /**
         * Dodanie zdarzenia ActionListener do przycisku backButton.
         * Po kliknięciu przycisku zostanie wyświetlone okno MenuFrame.
         */
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
     * Metoda updatePlayersFields aktualizuje pola tekstowe z nazwami graczy w zależności od wybranej liczby graczy.
     */
    private void updatePlayersFields() {
        playersPanel.removeAll(); // Usunięcie poprzednich pól
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS)); // Ustawienie układu

        users.clear(); // Czyścimy listę użytkowników, aby uniknąć nadpisywania

        int humanPlayers = Integer.parseInt((String) HumanPlayerComboBox.getSelectedItem());
        int botPlayers = Integer.parseInt((String) BotPlayerComboBox.getSelectedItem());

        // Sprawdzenie, czy suma graczy nie przekracza limitu
        if (humanPlayers + botPlayers > 4) {
            JOptionPane.showMessageDialog(null, "Za dużo graczy", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Dodanie graczy-ludzi
        for (int i = 0; i < humanPlayers; i++) {
            JLabel humanLabel = new JLabel("Gracz " + (i + 1) + " (Człowiek): ");
            HintText humanTextField = new HintText("Podaj nazwę dla Gracza " + (i + 1));

            JPanel humanPanel = new JPanel();
            humanPanel.setLayout(new BoxLayout(humanPanel, BoxLayout.Y_AXIS));
            humanPanel.add(humanLabel);
            humanPanel.add(humanTextField);

            // Tworzenie obiektu Player i dodanie do listy
            Player player = new Player("Gracz " + (i + 1), SwingColor.playerColor(amountOfPlayers++));
            users.add(player);

            // Listener do aktualizacji nazwy gracza
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

        // Dodanie graczy-botów
        for (int i = 0; i < botPlayers; i++) {
            JLabel botLabel = new JLabel("Gracz " + (humanPlayers + i + 1) + " (Bot): ");
            HintText botTextField = new HintText("Podaj nazwę dla Bota " + (i + 1));

            JPanel botPanel = new JPanel();
            botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.Y_AXIS));
            botPanel.add(botLabel);
            botPanel.add(botTextField);

            // Tworzenie obiektu Bot i dodanie do listy
            Bot bot = new Bot("Bot " + (i + 1), SwingColor.playerColor(amountOfPlayers++));
            users.add(bot);

            // Listener do aktualizacji nazwy bota
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

