package model;

import java.io.Serializable;
import java.util.Random;

public class Dice implements Serializable {
    private static final long serialVersionUID = 1L; // Wersja serializacji

    private int randomValue; // Aktualna wartość wyrzucona na kostce
    private transient Random random; // Generator liczb losowych (transient - nie jest serializowalny)
    private static final String IMAGE_PATH = "data/images/diceImages/"; // Stała ścieżka do obrazów kostki

    // Konstruktor
    public Dice() {
        this.randomValue = 0;
        this.random = new Random();
    }

    // Rzut kostką
    public int roll() {
        if (random == null) {
            random = new Random(); // Odtwarzamy generator losowy po deserializacji
        }
        randomValue = random.nextInt(6) + 1; // Wartości od 1 do 6
        return randomValue;
    }

    // Pobieranie aktualnej wartości kostki
    public int getValue() {
        return randomValue;
    }

    // Pobieranie ścieżki obrazu odpowiadającego aktualnej wartości kostki
    public String getImagePath() {
        return IMAGE_PATH + randomValue + ".png";
    }

    // Serializacja (zapisywanie obiektu)
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        out.defaultWriteObject(); // Zapisujemy pola serializowalne
    }

    // Deserializacja (odtwarzanie obiektu)
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject(); // Odczytujemy pola serializowalne
        random = new Random(); // Tworzymy nowy generator losowy, ponieważ nie jest serializowalny
    }
}
