import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dice.Dice;
/**
 * Klasa prezentująca prosty interfejs graficzny dla symulacji rzutu kostką.
 * Używa biblioteki Swing oraz klasy Dice.
 */
public class DiceSwingDemo extends JFrame {
    private JLabel resultLabel; // Etykieta do wyświetlania wyniku rzutu kostką
    private JButton rollButton; // Przycisk do wykonania rzutu
    private Dice dice; // Obiekt reprezentujący kostkę

    /**
     * Konstruktor klasy DiceSwingDemo. Tworzy interfejs graficzny i inicjalizuje komponenty.
     */
    public DiceSwingDemo() {
        // Ustawienie podstawowych parametrów okna
        setTitle("Game - Dice Roll"); // Tytuł okna
        setSize(300, 200); // Rozmiar okna: 300x200 pikseli
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Zamknij aplikację po zamknięciu okna
        setLayout(null); // Ustawienie układu na null (komponenty będą pozycjonowane ręcznie)

        // Tworzenie etykiety do wyświetlania wyniku
        resultLabel = new JLabel("Result: Dice not rolled yet"); // Domyślny tekst
        resultLabel.setBounds(50, 50, 200, 30); // Pozycja i rozmiar etykiety
        add(resultLabel); // Dodanie etykiety do okna

        // Tworzenie przycisku do rzutu kostką
        rollButton = new JButton("Roll Dice"); // Tekst na przycisku
        rollButton.setBounds(50, 100, 200, 30); // Pozycja i rozmiar przycisku
        add(rollButton); // Dodanie przycisku do okna

        // Tworzenie obiektu kostki (Dice)
        dice = new Dice();

        // Dodanie działania dla przycisku (zdarzenie ActionListener)
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Po kliknięciu przycisku wykonaj rzut kostką
                int result = dice.roll(); // Rzut kostką i zapis wyniku
                resultLabel.setText("Result: " + result); // Aktualizacja tekstu etykiety
            }
        });
    }

    /**
     * Metoda główna (punkt startowy aplikacji).
     * Tworzy obiekt DiceSwingDemo i wyświetla go.
     */
    public static void main(String[] args) {
        // Użycie SwingUtilities do poprawnego uruchomienia GUI w wątku EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            DiceSwingDemo demo = new DiceSwingDemo(); // Tworzenie okna aplikacji
            demo.setVisible(true); // Ustawienie widoczności okna na true
        });
    }
}
