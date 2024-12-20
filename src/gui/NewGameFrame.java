package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


/**
 * Klasa NewGameFrame odpowiada za wyświetlenie okna do rozpoczęcia nowej gry.
 * Umożliwia wybór liczby graczy oraz ich nazw.
 */
public class NewGameFrame{
    /**
     * Klasa HintText dziedzicząca po JTextField, która dodaje podpowiedź do pola tekstowego.
     */
    private class HintText extends JTextField{
        // Deklaracja zmiennych
        private final String hint; // Podpowiedź
        private boolean showingHint; // Czy podpowiedź jest wyświetlana


        /**
         * Konstruktor klasy HintText
         *
         * @param hint Podpowiedź
         */
        public HintText(final String hint){
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
                    if(getText().isEmpty()){
                        setText("");
                        setForeground(Color.BLACK);
                        showingHint = false;
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if(getText().isEmpty()){
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
        public String getText(){
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

    private JFrame parentFrame; // Okno nadrzędne

    /**
     * Konstruktor klasy NewGameFrame
     *
     * @param parentFrame Okno nadrzędne
     */
    public NewGameFrame(JFrame parentFrame){
        this.parentFrame = parentFrame; // Przypisanie okna nadrzędnego

        /**
         * Dodanie zdarzenia ActionListener do przycisku startGameButton.
         * Po kliknięciu przycisku zostanie pobrana liczba graczy oraz ich nazwy.
         * Następnie zostanie utworzona nowa gra z podanymi parametrami.
         */
        HumanPlayerComboBox.setModel(new DefaultComboBoxModel(new String[] { "0" , "1", "2", "3", "4" }));
        BotPlayerComboBox.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4" }));


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

        startGameButton.addActionListener(e -> {

            GamePanel gamePanel = new GamePanel();

            parentFrame.setContentPane(gamePanel.GamePanel);

            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }

    public JPanel getMainPanel(){
        return NewGamePanel;
    }

    /**
     * Metoda updatePlayersFields aktualizuje pola tekstowe z nazwami graczy w zależności od wybranej liczby graczy.
     */
    private void updatePlayersFields(){
        playersPanel.removeAll(); // Usunięcie poprzednich pól tekstowych
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS)); // Ustawienie nowego układu siatki
        int humanPlayers = Integer.parseInt((String) HumanPlayerComboBox.getSelectedItem());
        int botPlayers = Integer.parseInt((String) BotPlayerComboBox.getSelectedItem());
        // Sprawdzenie, czy suma graczy nie przekracza 4
        if(humanPlayers + botPlayers > 4){
            JOptionPane.showMessageDialog(null, "Za dużo graczy", "Błąd", JOptionPane.ERROR_MESSAGE); // Wyświetlenie komunikatu o błędzie
            return;
        }
        // Dodanie pól tekstowych do wprowadzenia nazw graczy
        for(int i = 0; i < humanPlayers; i++){
            JLabel Humanlabel = new JLabel("Gracz " + (i + 1) + "(Człowiek): ");
            HintText HumantextField = new HintText("Podaj nazwę dla Gracza " + (i + 1));
            HumantextField.setName("humanPlayer" + (i+1));
            HumantextField.setText("Podaj nazwę dla Gracza " + (i + 1));

            JPanel HumanPanel = new JPanel();
            HumanPanel.setLayout(new BoxLayout(HumanPanel, BoxLayout.Y_AXIS));
            HumanPanel.add(Humanlabel);
            HumanPanel.add(HumantextField);

            playersPanel.add(HumanPanel);
        }
        // Dodanie pól tekstowych do wprowadzenia nazw botów
        for(int i = 0; i < botPlayers; i++){
            JLabel Botlabel = new JLabel("Gracz " + (i + 1) + "(Bot): ");
            HintText BottextField = new HintText("Podaj nazwę dla Bota " + (i + 1));
            BottextField.setName("botPlayer" + (i+1));
            BottextField.setText("Podaj nazwę dla Bota " + (i + 1));

            JPanel BotPanel = new JPanel();
            BotPanel.setLayout(new BoxLayout(BotPanel, BoxLayout.Y_AXIS));
            BotPanel.add(Botlabel);
            BotPanel.add(BottextField);

            playersPanel.add(BotPanel);
        }
        // Aktualizacja panelu z graczami
        playersPanel.revalidate();
        playersPanel.repaint();
    }
}
