package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Bot extends Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient Random random;

    public Bot(String username, Color color) {
        super(username, color);
        this.random = new Random();
    }

    // Konstruktor bezargumentowy dla deserializacji
    public Bot() {
        super();
        this.random = new Random();
    }

    public Pawn choosePawnToMove(int diceValue) {
        List<Pawn> pawnsInGame = getPawnsInGame();
        List<Pawn> pawnsInBase = getPawnsAtBase();

        if(diceValue == 6 && !pawnsInBase.isEmpty()){
            return pawnsInBase.get(0);
            }

        if (pawnsInGame.isEmpty()) {
            return pawnsInBase.get(0);
        }

        return pawnsInGame.get(random.nextInt(pawnsInGame.size()));
    }

    private boolean canReachGoal(Pawn pawn, int steps) {
        return false; // Placeholder na przyszłą logikę
    }

    public void makeMove(Field board, int steps) {
        Pawn pawnToMove = choosePawnToMove(steps);

        if (pawnToMove == null) {
            if (!getPawnsAtHome().isEmpty() && steps == 6) {
                Pawn pawn = getPawnsAtHome().get(0);
                movePawnToGame(pawn);
            }
        } else {
            pawnToMove.move(steps);
        }
    }

    @Override
    public String getUserType() {
        return "Bot";
    }
}
