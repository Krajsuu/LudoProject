package model;

import java.awt.Point;
import java.util.ArrayList;

public class Field {
    final int totalCells = 52; // Liczba pól na głównej ścieżce
    private final int cellsPerPlayer = 13; // Liczba pól na ścieżce do "domku" dla każdego gracza
    private ArrayList<Point> boardPositions; // Współrzędne pól na planszy (dla renderowania)
    private Point[][] playerHomes; // Pozycje startowe dla pionków każdego gracza
    private Point[][] goalAreas; // Obszary "domku" dla graczy

    public Field() {
        initializeBoard();
        initializePlayerHomes();
        initializeGoalAreas();
    }

    // Inicjalizacja pozycji pól planszy
    private void initializeBoard() {
        boardPositions = new ArrayList<>();
        // Przykład: generowanie pozycji dla 52 pól
        for (int i = 0; i < totalCells; i++) {
            boardPositions.add(calculatePosition(i));
        }
    }

    // Obliczanie pozycji pojedynczego pola na planszy
    private Point calculatePosition(int index) {
        // Pozycjonowanie pól w kształcie koła
        int x = (int) (200 + 100 * Math.cos(index * 2 * Math.PI / totalCells));
        int y = (int) (200 + 100 * Math.sin(index * 2 * Math.PI / totalCells));
        return new Point(x, y);
    }

    // Inicjalizacja pozycji startowych dla graczy
    private void initializePlayerHomes() {
        playerHomes = new Point[4][4]; // 4 graczy, po 4 pionki dla każdego
        // Definiowanie pozycji startowych (można dodać konkretne współrzędne)
    }

    // Inicjalizacja obszarów "domku" dla graczy
    private void initializeGoalAreas() {
        goalAreas = new Point[4][cellsPerPlayer]; // Każdy gracz ma 13 pól w "domku"
        // Definiowanie ścieżek do "domku"
    }

    // Zwraca pozycję konkretnego pola na planszy
    public Point getPosition(int index) {
        return boardPositions.get(index);
    }

    // Zwraca pozycje startowe dla danego gracza
    public Point[] getPlayerHome(int playerIndex) {
        return playerHomes[playerIndex];
    }

    // Zwraca ścieżkę do "domku" dla danego gracza
    public Point[] getGoalArea(int playerIndex) {
        return goalAreas[playerIndex];
    }
}
