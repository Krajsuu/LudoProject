package model; // Pakiet gry (zmień zgodnie ze strukturą projektu)

/**
 * Klasa reprezentująca użytkownika w grze. Obsługuje takie elementy, jak:
 * - Nazwa użytkownika
 * - Identyfikator gracza (ID)
 * - Liczba pionków (np. do gry w "Chińczyka")
 * - Aktywny status użytkownika (czy jest jego tura)
 * - Liczba ruchów do wykonania (po rzucie kostką)
 */
public class User {
    private String username;    // Nazwa użytkownika
    private int id;             // Identyfikator gracza
    private int totalPawns;     // Całkowita liczba pionków
    private int pawnsInGame;    // Liczba pionków obecnie w grze
    private boolean isActive;   // Czy użytkownik ma aktywną turę?
    private int movesRemaining; // Liczba ruchów do wykonania (po rzucie kostką)

    /**
     * Konstruktor domyślny - tworzy pustego użytkownika.
     */
    public User() {
        this("Unknown", -1, 4); // Domyślna liczba pionków to 4 (dla "Chińczyka")
    }

    /**
     * Konstruktor parametryczny - tworzy użytkownika o podanej nazwie, ID i liczbie pionków.
     *
     * @param username   nazwa użytkownika
     * @param id         unikalny identyfikator gracza
     * @param totalPawns liczba pionków przypisana do gracza
     */
    public User(String username, int id, int totalPawns) {
        this.username = username;
        this.id = id;
        this.totalPawns = totalPawns;
        this.pawnsInGame = totalPawns; // Na początku wszystkie pionki są w grze
        this.isActive = false;         // Na początku gracz nie jest aktywny
        this.movesRemaining = 0;      // Na początku gracz nie ma ruchów
    }

    /**
     * Getter dla nazwy użytkownika.
     *
     * @return nazwa użytkownika
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter dla nazwy użytkownika.
     *
     * @param username nowa nazwa użytkownika
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter dla identyfikatora użytkownika.
     *
     * @return identyfikator użytkownika
     */
    public int getId() {
        return id;
    }

    /**
     * Setter dla identyfikatora użytkownika.
     *
     * @param id nowy identyfikator użytkownika
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter dla liczby pionków przypisanych do gracza.
     *
     * @return całkowita liczba pionków
     */
    public int getTotalPawns() {
        return totalPawns;
    }

    /**
     * Getter dla liczby pionków obecnie w grze.
     *
     * @return liczba pionków w grze
     */
    public int getPawnsInGame() {
        return pawnsInGame;
    }

    /**
     * Aktualizuje liczbę pionków w grze (np. po zdobyciu lub stracie pionka).
     *
     * @param change liczba pionków do dodania/odjęcia
     */
    public void updatePawnsInGame(int change) {
        this.pawnsInGame += change;

        // Upewnij się, że liczba pionków nie jest ujemna ani większa niż maksymalna
        if (this.pawnsInGame < 0) {
            this.pawnsInGame = 0;
        } else if (this.pawnsInGame > totalPawns) {
            this.pawnsInGame = totalPawns;
        }
    }

    /**
     * Sprawdza, czy użytkownik ma jeszcze pionki w grze.
     *
     * @return true, jeśli ma pionki, false w przeciwnym razie
     */
    public boolean hasPawnsInGame() {
        return pawnsInGame > 0;
    }

    /**
     * Getter dla statusu aktywności użytkownika.
     *
     * @return true, jeśli jest aktywny, false w przeciwnym razie
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Setter dla statusu aktywności użytkownika.
     *
     * @param isActive nowy status aktywności
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Getter dla liczby ruchów do wykonania.
     *
     * @return liczba ruchów do wykonania
     */
    public int getMovesRemaining() {
        return movesRemaining;
    }

    /**
     * Setter dla liczby ruchów do wykonania.
     *
     * @param moves liczba ruchów
     */
    public void setMovesRemaining(int moves) {
        this.movesRemaining = moves;
    }

    /**
     * Aktualizuje liczbę ruchów (np. po wykonaniu jednego z ruchów).
     *
     * @param change liczba ruchów do dodania/odjęcia
     */
    public void updateMovesRemaining(int change) {
        this.movesRemaining += change;

        // Upewnij się, że liczba ruchów nie jest ujemna
        if (this.movesRemaining < 0) {
            this.movesRemaining = 0;
        }
    }

    /**
     * Zwraca opis tekstowy użytkownika (np. do debugowania).
     *
     * @return opis użytkownika
     */
    @Override
    public String toString() {
        return String.format("User{id=%d, username='%s', pawnsInGame=%d/%d, active=%b, movesRemaining=%d}",
                id, username, pawnsInGame, totalPawns, isActive, movesRemaining);
    }

    /**
     * Metoda testowa dla klasy User.
     * @param args argumenty wejściowe (niewykorzystane)
     */
    public static void main(String[] args) {
        // Tworzenie przykładowego użytkownika
        User user = new User("Player1", 1, 4);

        // Wyświetlanie informacji o użytkowniku
        System.out.println(user);

        // Aktywacja użytkownika i wykonanie ruchów
        user.setActive(true);
        user.setMovesRemaining(3);
        System.out.println(user);

        // Aktualizacja liczby pionków
        user.updatePawnsInGame(-1);
        System.out.println(user);

        // Resetowanie liczby ruchów
        user.updateMovesRemaining(-2);
        System.out.println(user);

        // Dezaktywacja użytkownika
        user.setActive(false);
        System.out.println(user);
    }
}
