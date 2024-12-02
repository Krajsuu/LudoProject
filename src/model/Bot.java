package model;

import java.util.Random;

public class Bot {
    private int playerIndex; // Identyfikator gracza
    private Random random;

    public Bot(int playerIndex) {
        this.playerIndex = playerIndex;
        this.random = new Random();
    }

    public int rollDice() {
        return random.nextInt(6) + 1; // Losowanie wartości od 1 do 6
    }

    public int decideMove(Pawn[] pawns, int diceRoll, Field field) {
        for (int i = 0; i < pawns.length; i++) {
            if (!pawns[i].isFinished() && canMove(pawns[i], diceRoll, field)) {
                return i;
            }
        }
        return -1; // Brak możliwego ruchu
    }

    private boolean canMove(Pawn pawn, int diceRoll, Field field) {
        if (pawn.isAtHome() && diceRoll == 6) return true;
        if (!pawn.isAtHome() && !pawn.isFinished()) {
            int newPosition = (pawn.getPosition() + diceRoll) % field.totalCells;
            return newPosition < field.totalCells;
        }
        return false;
    }
}
