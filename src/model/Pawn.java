package model;

public class Pawn {
    private int playerIndex; // Gracz, do którego należy pionek
    private int position; // Pozycja pionka (-1 = w domu)
    private boolean isFinished; // Czy pionek dotarł do "domku"

    public Pawn(int playerIndex) {
        this.playerIndex = playerIndex;
        this.position = -1; // Początkowo w domu
        this.isFinished = false;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isAtHome() {
        return position == -1;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void markFinished() {
        isFinished = true;
    }
}
