// Field.java
package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Field implements Serializable { // Implementacja Serializable
    private static final long serialVersionUID = 1L; // Wersja serializacji

    private static final int TOTAL_CELLS = 52; // Liczba pól na głównej ścieżce
    private static final int CELLS_PER_PLAYER = 6; // Liczba pól w "domku" dla każdego gracza
    private ArrayList<Point> boardPositions; // Współrzędne pól planszy
    private Point[][] playerHomes; // Pozycje startowe dla każdego gracza
    private Point[][] goalAreas; // Obszary "domku" dla graczy
    private Map<Pawn, Point> pawnPositions; // Mapa przechowująca pozycje pionków

    // Konstruktor inicjalizujący planszę, domki i pozycje graczy
    public Field() {
        this.boardPositions = new ArrayList<>();
        this.pawnPositions = new HashMap<>();
        initializeBoard();
        initializePlayerHomes();
        initializeGoalAreas();
    }

    // Inicjalizacja pozycji pól na planszy
    private void initializeBoard() {
        for (int i = 0; i < TOTAL_CELLS; i++) {
            boardPositions.add(calculatePosition(i));
        }
    }

    // Oblicza współrzędne dla danego indeksu pola na planszy
    private Point calculatePosition(int index) {
        int x = (index % 13) * 40; // Przykładowa logika wyliczania współrzędnych
        int y = (index / 13) * 40;
        return new Point(x, y);
    }

    // Inicjalizacja pozycji startowych pionków
    private void initializePlayerHomes() {
        playerHomes = new Point[4][4]; // 4 graczy, po 4 pionki każdy
        playerHomes[0] = new Point[]{new Point(50, 50), new Point(90, 50), new Point(50, 90), new Point(90, 90)};
        playerHomes[1] = new Point[]{new Point(310, 50), new Point(350, 50), new Point(310, 90), new Point(350, 90)};
        playerHomes[2] = new Point[]{new Point(310, 310), new Point(350, 310), new Point(310, 350), new Point(350, 350)};
        playerHomes[3] = new Point[]{new Point(50, 310), new Point(90, 310), new Point(50, 350), new Point(90, 350)};
    }

    // Inicjalizacja obszarów "domków" graczy
    private void initializeGoalAreas() {
        goalAreas = new Point[4][CELLS_PER_PLAYER];
        for (int player = 0; player < 4; player++) {
            for (int cell = 0; cell < CELLS_PER_PLAYER; cell++) {
                goalAreas[player][cell] = new Point(200 + (cell * 10), 200); // Przykładowe ustawienie domków
            }
        }
    }

    // Aktualizuje pozycję pionka na planszy
    public void updatePosition(Pawn pawn, Point newPosition) {
        Point oldPosition = pawnPositions.get(pawn);

        if (oldPosition != null) {
            boardPositions.remove(oldPosition); // Usunięcie starej pozycji
        }

        pawnPositions.put(pawn, newPosition); // Aktualizacja nowej pozycji
    }

    // Sprawdza, czy pole jest zajęte i zwraca pionek, jeśli jest
    public Pawn checkOccupied(Point position) {
        for (Pawn pawn : pawnPositions.keySet()) {
            if (pawnPositions.get(pawn).equals(position)) {
                return pawn;
            }
        }
        return null;
    }

    // Zwraca pozycję startową gracza
    public Point[] getPlayerHome(int playerIndex) {
        return playerHomes[playerIndex];
    }

    // Zwraca obszar mety gracza
    public Point[] getGoalArea(int playerIndex) {
        return goalAreas[playerIndex];
    }

    // Zwraca współrzędne pola na podstawie indeksu
    public Point getPosition(int index) {
        return boardPositions.get(index);
    }

    public Point calculateNewPosition(Point currentPosition, int steps) {
        // Implementacja logiki przesuwania pionka
        int newX = (currentPosition.x + steps) % 13; // Przykładowy wymiar planszy
        int newY = (currentPosition.y + (currentPosition.x + steps) / 13) % 13;
        return new Point(newX, newY);
    }

    // Sprawdzenie, czy dana pozycja to meta gracza
    public boolean isGoal(Point position, int playerIndex) {
        for (Point goal : goalAreas[playerIndex]) {
            if (goal.equals(position)) {
                return true;
            }
        }
        return false;
    }

    // Resetuje stan planszy
    public void resetBoard() {
        pawnPositions.clear();
    }

    // Serializacja pola pawnPositions
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        out.defaultWriteObject();
        out.writeInt(pawnPositions.size());
        for (Map.Entry<Pawn, Point> entry : pawnPositions.entrySet()) {
            out.writeObject(entry.getKey());
            out.writeObject(entry.getValue());
        }
    }

    // Deserializacja pola pawnPositions
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        int size = in.readInt();
        pawnPositions = new HashMap<>();
        for (int i = 0; i < size; i++) {
            Pawn key = (Pawn) in.readObject();
            Point value = (Point) in.readObject();
            pawnPositions.put(key, value);
        }
    }
}
