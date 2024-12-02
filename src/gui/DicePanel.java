package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DicePanel {
    private JButton rollCube;
    private JTextArea pierodoleKurwaToFormTextArea;
    private JLabel diceLabel;

    private int diceValue; // Aktualna wartość kostki
    private Random random; // Generator losowych liczb
    private DiceListener listener; // Listener do obsługi rzutu kostką

    //To do Sama klasa DicePanel


    // Symulacja rzutu kostką
    private void rollDice() {
        diceValue = random.nextInt(6) + 1; // Wartość od 1 do 6
        diceLabel.setText("Kostka: " + diceValue); // Aktualizacja wyniku
    }

    // Zwraca aktualną wartość kostki
    public int getDiceValue() {
        return diceValue;
    }

    // Ustawia listener do powiadamiania innych komponentów
    public void setDiceListener(DiceListener listener) {
        this.listener = listener;
    }

    // Interfejs do obsługi rzutu kostką
    public interface DiceListener {
        void onDiceRoll(int diceValue);
    }
}
