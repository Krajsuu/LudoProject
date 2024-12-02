package model;

import utils.Constants;

import java.awt.Point;
import java.util.ArrayList;

public class Field {
    final int totalCells = Constants.BOARD_SIZE; // Liczba pól na głównej ścieżce
    final int cellsPerPlayer = 6; // Liczba pól w "domku" dla każdego gracza
    private ArrayList<Point> boardPositions; // Współrzędne pól planszy
    private Point[][] playerHomes; // Pozycje startowe dla każdego gracza
    private Point[][] goalAreas; // Obszary "domku" dla graczy

    public Field() {
        initializeBoard();
        initializePlayerHomes();
        initializeGoalAreas();
    }

    // Inicjalizacja pozycji pól na planszy
    private void initializeBoard() {
        boardPositions = new ArrayList<>();
        for (int i = 0; i < totalCells; i++) {
            boardPositions.add(calculatePosition(i));
        }
    }

    //Random kod obliczenia dopiero jak bdz plansza
    private Point calculatePosition(int index) {
        return boardPositions.get(index % boardPositions.size());
    }

    // Inicjalizacja pozycji startowych pionków
    private void initializePlayerHomes() {
        playerHomes = new Point[4][4]; // 4 graczy, po 4 pionki każdy
        // Przykładowe pozycje startowe
        playerHomes[0] = new Point[] { new Point(50, 50), new Point(90, 50), new Point(50, 90), new Point(90, 90) };
        playerHomes[1] = new Point[] { new Point(310, 50), new Point(350, 50), new Point(310, 90), new Point(350, 90) };
        playerHomes[2] = new Point[] { new Point(310, 310), new Point(350, 310), new Point(310, 350), new Point(350, 350) };
        playerHomes[3] = new Point[] { new Point(50, 310), new Point(90, 310), new Point(50, 350), new Point(90, 350) };
    }

    // Inicjalizacja "domków" graczy
    private void initializeGoalAreas() {
        goalAreas = new Point[4][cellsPerPlayer];
        for (int player = 0; player < 4; player++) {
            for (int cell = 0; cell < cellsPerPlayer; cell++) {
                goalAreas[player][cell] = new Point(200 + (cell * 10), 200); // Przykładowa linia w "domku"
            }
        }
    }

    public Point getPosition(int index) {
        return boardPositions.get(index);
    }

    public Point[] getPlayerHome(int playerIndex) {
        return playerHomes[playerIndex];
    }

    public Point[] getGoalArea(int playerIndex) {
        return goalAreas[playerIndex];
    }
}
