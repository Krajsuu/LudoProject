package model;

public class Pawn {
    private int playerIndex; // Identyfikator gracza, do którego należy pionek
    private int position; // Aktualna pozycja na planszy (-1 oznacza w domu)
    private boolean isAtHome; // Czy pionek jest w strefie startowej
    private boolean isFinished; // Czy pionek osiągnął "domek"

    public Pawn(int playerIndex) {
        this.playerIndex = playerIndex;
        this.position = -1; // Początkowo pionek jest w domu
        this.isAtHome = true;
        this.isFinished = false;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public int getPosition() {
        return position;
    }

    // Ustawia nową pozycję pionka na planszy
    public void setPosition(int position) {
        this.position = position;
        this.isAtHome = false;
    }

    public boolean isAtHome() {
        return isAtHome;
    }

    public boolean isFinished() {
        return isFinished;
    }

    // Oznacza pionek jako ukończony (dotarł do "domku")
    public void markFinished() {
        this.isFinished = true;
    }

    // Resetuje pionek do pozycji startowej
    public void resetToHome() {
        this.position = -1;
        this.isAtHome = true;
    }
}
