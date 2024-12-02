package model;

import java.util.Random;

public class Bot {
    private int playerIndex; // Identyfikator gracza (bota)
    private Random random;

    public Bot(int playerIndex) {
        this.playerIndex = playerIndex;
        this.random = new Random();
    }

    // Symuluje rzut kostką
    public int rollDice() {
        return random.nextInt(6) + 1; // Losuje liczbę od 1 do 6
    }

    // Decyduje, którym pionkiem się poruszyć na podstawie stanu gry i wyniku kostki
    public int decideMove(Pawn[] pawns, int diceRoll, Field field) {
        for (int i = 0; i < pawns.length; i++) {
            if (!pawns[i].isFinished() && canMove(pawns[i], diceRoll, field)) {
                return i; // Zwraca indeks wybranego pionka
            }
        }
        return -1; // Brak możliwego ruchu
    }

    // Sprawdza, czy ruch pionka jest możliwy
    private boolean canMove(Pawn pawn, int diceRoll, Field field) {
        if (pawn.isAtHome() && diceRoll == 6) return true; // Może opuścić dom na 6
        if (!pawn.isAtHome() && !pawn.isFinished()) {
            int newPosition = (pawn.getPosition() + diceRoll) % field.totalCells;
            // Sprawdź, czy nowa pozycja jest prawidłowa
            return true; // Tutaj można zaimplementować kolizje i inne zasady
        }
        return false;
    }
}
