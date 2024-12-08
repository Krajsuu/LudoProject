/*package game;

/**
 * Klasa reprezentująca gracza (Player) w grze planszowej, np. Chińczyk.
 * Rozszerza klasę User, dodając informacje o aktywnej turze, rzucie kostką,
 * pozycji na planszy i kolorze pionków.
*//*
public class Player extends User {
    private String color;             // Kolor pionków gracza (np. czerwony, zielony)
    private int currentPosition;      // Aktualna pozycja gracza na planszy
    private boolean isTurn;           // Czy to tura tego gracza?
    private int diceRoll;             // Wartość wyrzucona na kostce w tej turze

    /**
     * Konstruktor klasy Player.
     * Tworzy gracza z nazwą, ID, liczbą pionków oraz kolorem.
     *
     * @param username   nazwa gracza
     * @param id         unikalny identyfikator gracza
     * @param totalPawns liczba pionków gracza
     * @param color      kolor pionków
     *//*
    public Player(String username, int id, int totalPawns, String color) {
        super(username, id, totalPawns);  // Wywołanie konstruktora klasy User
        this.color = color;                // Ustawienie koloru pionków
        this.currentPosition = 0;          // Początkowo gracz jest na pozycji 0
        this.isTurn = false;               // Na początku gracz nie ma tury
        this.diceRoll = 0;                 // Brak rzutu kostką
    }

    // Gettery i Settery

    public String getColor() {
        return color;   // Zwraca kolor pionków gracza
    }

    public void setColor(String color) {
        this.color = color;  // Ustawia kolor pionków
    }

    public int getCurrentPosition() {
        return currentPosition;  // Zwraca aktualną pozycję gracza na planszy
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;  // Ustawia nową pozycję gracza
    }

    public boolean isTurn() {
        return isTurn;  // Zwraca, czy gracz ma teraz turę
    }

    public void setTurn(boolean isTurn) {
        this.isTurn = isTurn;  // Ustawia, czy gracz ma teraz turę
    }

    public int getDiceRoll() {
        return diceRoll;  // Zwraca wynik rzutu kostką
    }

    public void setDiceRoll(int diceRoll) {
        this.diceRoll = diceRoll;  // Ustawia wynik rzutu kostką
    }

    /**
     * Rzuca kostką i ustawia wynik.
     * Rzut generuje liczbę oczek w przedziale 1-6.
     *//*
    public void rollDice() {
        Dice dice = new Dice();        // Tworzenie obiektu kostki
        this.diceRoll = dice.roll();   // Rzut kostką i zapisanie wyniku
    }

    /**
     * Przesuwa gracza o wynik rzutu kostką.
     * Zmienia pozycję gracza na planszy na podstawie wyniku rzutu.
     *//*
    public void move() {
        if (getMovesRemaining() > 0) {   // Sprawdzamy, czy gracz ma dostępne ruchy
            setCurrentPosition(getCurrentPosition() + diceRoll);  // Przesuwamy gracza
            updateMovesRemaining(-1);  // Zmniejszamy liczbę dostępnych ruchów o 1
        } else {
            System.out.println("Brak dostępnych ruchów.");  // Brak ruchów
        }
    }

    /**
     * Metoda odpowiadająca za zakończenie tury gracza.
     * Zmienia status tury na 'false' oraz resetuje liczby ruchów.
     *//*
    public void endTurn() {
        setTurn(false);             // Zakończenie tury gracza
        setMovesRemaining(0);       // Reset liczby dostępnych ruchów
        setDiceRoll(0);             // Reset wyniku rzutu kostką
    }

    /**
     * Metoda do rozpoczęcia tury gracza.
     * Ustawia gracza jako aktywnego w grze i ustawia liczbę ruchów.
     *//*
    public void startTurn() {
        setTurn(true);              // Rozpoczęcie tury, ustawiamy gracza jako aktywnego
        setMovesRemaining(3);       // Na początku tury gracz ma 3 ruchy do wykonania
        rollDice();                 // Rzuca kostką na początku tury
    }

    /**
     * Zwraca dane o graczu w formie tekstowej.
     * Jest to przydatne do logowania, debugowania itp.
     *
     * @return tekstowy opis gracza
     *//*
    @Override
    public String toString() {
        // Zwraca szczegółowy opis gracza w formie tekstowej
        return String.format("Player{id=%d, username='%s', color='%s', position=%d, turn=%b, diceRoll=%d, pawnsInGame=%d/%d, movesRemaining=%d}",
                getId(), getUsername(), color, currentPosition, isTurn, diceRoll, getPawnsInGame(), getTotalPawns(), getMovesRemaining());
    }
}*/
