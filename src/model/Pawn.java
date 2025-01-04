package model;

import java.awt.Point;
import javax.swing.ImageIcon;
import java.util.Objects;

public class Pawn {
    private Player owner; // Referencja do gracza
    private Point currentPosition; // Aktualna pozycja na planszy
    private Point homePosition; // Pozycja startowa
    private Point goalPosition; // Pozycja mety
    private ImageIcon pawnIcon; // Ikona pionka
    private boolean isFinished; // Czy pionek osiągnął metę

    // Konstruktor inicjalizujący pionek z odpowiednimi parametrami
    public Pawn(Player owner, Point homePosition, Point goalPosition, ImageIcon icon) {
        this.owner = owner; // Przypisanie gracza jako właściciela pionka
        this.homePosition = homePosition; // Ustawienie pozycji startowej
        this.goalPosition = goalPosition; // Ustawienie pozycji mety
        this.pawnIcon = icon; // Ustawienie ikony reprezentującej pionek
        this.currentPosition = homePosition; // Pionek startuje w swojej bazie
        this.isFinished = false; // Początkowo pionek nie osiągnął mety
    }

    // Dodanie unikalnego identyfikatora pionka
    public String getType() { // DODAĆ DO KLASY PLAYER
        return owner.getColor() + " Pawn"; // Kolor jest przekazywany przez właściciela
    }

    // Getter zwracający właściciela pionka
    public Player getOwner() {
        return owner;
    }

    // Getter zwracający aktualną pozycję pionka na planszy
    public Point getCurrentPosition() {
        return currentPosition;
    }

    // Setter ustawiający nową pozycję pionka
    public void setCurrentPosition(Point position) {
        this.currentPosition = position;
    }

    // Sprawdza, czy pionek znajduje się w swojej bazie
    public boolean isAtHome() {
        return currentPosition.equals(homePosition);
    }

    // Sprawdza, czy pionek osiągnął metę
    public boolean isFinished() {
        return isFinished;
    }

    // Oznacza pionek jako zakończony (osiągnięcie mety)
    public void markFinished() {
        this.isFinished = true;
    }

    // Przesuwa pionek o określoną liczbę pól na planszy
    public void move(int steps, Field board) {
        if (isFinished) {
            return; // Pionek nie może się ruszać, jeśli osiągnął metę
        }

        // Obliczenie nowej pozycji na podstawie obecnej pozycji i liczby kroków
        Point newPosition = board.calculateNewPosition(currentPosition, steps);

        // Sprawdzenie, czy nowe pole jest zajęte przez inny pionek
        Pawn occupyingPawn = board.checkOccupied(newPosition);

        if (occupyingPawn != null && !Objects.equals(occupyingPawn.getOwner(), this.owner)) {
            occupyingPawn.returnToBase(); // Usunięcie pionka przeciwnika do bazy
            owner.grantExtraRoll();  // Gracz dostaje dodatkowy rzut
        }

        // Aktualizacja pozycji pionka
        setCurrentPosition(newPosition);
        board.updatePosition(this, newPosition);

        // Sprawdzenie, czy pionek dotarł do mety
        if (newPosition.equals(goalPosition)) {
            markFinished();
        }
    }

    // Zwraca pionek do pozycji startowej (bazy)
    public void returnToBase() {
        this.currentPosition = homePosition;
    }

    // Getter zwracający ikonę pionka
    public ImageIcon getPawnIcon() {
        return pawnIcon;
    }

}