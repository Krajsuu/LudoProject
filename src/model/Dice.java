package dice;

import java.util.Random;

/**
 * Klasa reprezentująca kostkę do gry (Dice). Obsługuje rzuty kostką,
 * symulację wyniku i przekazywanie liczby oczek do logiki gry.
 */
public class Dice {
    private static final int MIN_VALUE = 1; // Minimalna wartość oczek
    private static final int MAX_VALUE = 6; // Maksymalna wartość oczek
    private int result;         // Aktualna wartość oczek na kostce
    private boolean hasRolled;  // Flaga: czy wykonano rzut
    private Random random;      // Generator liczb losowych

    /**
     * Konstruktor domyślny - tworzy standardową kostkę (1-6 oczek).
     */
    public Dice() {
        this.random = new Random();
        this.result = MIN_VALUE; // Domyślnie 1
        this.hasRolled = false;  // Na początku nie wykonano rzutu
    }

    /**
     * Symuluje rzut kostką i zapisuje wynik.
     *
     * @return wynik rzutu
     */
    public int roll() {
        result = random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
        hasRolled = true; // Flaga, że rzut został wykonany
        return result;
    }

    /**
     * Getter dla aktualnego wyniku rzutu kostką.
     *
     * @return wynik rzutu
     * @throws IllegalStateException jeśli próbujemy pobrać wynik przed rzutem
     */
    public int getResult() {
        if (!hasRolled) {
            throw new IllegalStateException("Dice has not been rolled yet. Result is unknown.");
        }
        return result;
    }

    /**
     * Zwraca liczbę oczek do przesunięcia pionka.
     * Jest to alias dla getResult(), ale można dodać tutaj dodatkową logikę.
     *
     * @return liczba oczek
     */
    public int getStepsToMove() {
        return getResult(); // Można dodać dodatkową logikę, jeśli będzie potrzebna
    }

    /**
     * Symulacja rzutu kostką bez zapisywania wyniku.
     * Przydatne w GUI do prezentacji animacji lub testów.
     *
     * @return symulowany wynik
     */
    public int simulateRoll() {
        return random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
    }

    /**
     * Resetuje stan kostki, np. po zakończeniu rundy lub gry.
     */
    public void reset() {
        result = MIN_VALUE;  // Ustawiamy domyślny wynik
        hasRolled = false;   // Resetujemy flagę rzutu
    }

    /**
     * Wyświetla informacje o kostce (dla debugowania).
     *
     * @return tekstowy opis aktualnego stanu kostki
     */
    @Override
    public String toString() {
        return hasRolled
                ? "Dice: last roll result = " + result
                : "Dice: roll has not been performed yet.";
    }

    // Metoda testowa
    public static void main(String[] args) {
        Dice dice = new Dice();

        System.out.println(dice); // Informacja przed rzutem
        try {
            System.out.println("Current result: " + dice.getResult());
        } catch (IllegalStateException e) {
            System.err.println("Exception: " + e.getMessage());
        }

        System.out.println("Simulated roll: " + dice.simulateRoll());
        System.out.println("Actual roll: " + dice.roll());
        System.out.println("Current result: " + dice.getResult());

        // Przekazanie liczby oczek
        System.out.println("Steps to move: " + dice.getStepsToMove());

        // Resetowanie stanu kostki
        dice.reset();
        System.out.println(dice); // Informacja po resecie
    }
}
