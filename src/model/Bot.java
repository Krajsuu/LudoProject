// Klasa reprezentująca bota, dziedziczy po klasie Player
package model;

import utils.SwingColor;

import java.awt.Color;
import java.util.List;
import java.util.Random;

public class Bot extends Player {
    private Random random; // Generator losowych liczb

    // Konstruktor inicjalizujący bota
    public Bot(String username, Color color) {
        super(username, color);
        this.random = new Random();
    }

    // Wybór pionka do ruchu na podstawie prostej logiki
    public Pawn choosePawnToMove(int steps) {
        List<Pawn> pawnsInGame = getPawnsInGame();

        // Jeśli nie ma pionków w grze, nie można wykonać ruchu
        if (pawnsInGame.isEmpty()) {
            return null;
        }

        // Wybór pionka, który może osiągnąć cel
        for (Pawn pawn : pawnsInGame) {
            if (canReachGoal(pawn, steps)) {
                return pawn;
            }
        }

        // Wybór losowego pionka, jeśli żaden nie może osiągnąć celu
        return pawnsInGame.get(random.nextInt(pawnsInGame.size()));
    }

    // sprawdza czy dojdzie do bazy w danym ruchu
    private boolean canReachGoal(Pawn pawn, int steps) {
        return false; // TODO
    }

    // Wykonanie ruchu przez bota
    public void makeMove(Field board, int steps) {
        Pawn pawnToMove = choosePawnToMove(steps);

        if (pawnToMove == null) {
            // Jeśli nie ma pionka do ruchu, próbuje wprowadzić nowy pionek do gry
            if (!getPawnsAtHome().isEmpty() && steps == 6) {
                Pawn pawn = getPawnsAtHome().get(0);
                MovePawnToGame(pawn);

            }
        } else {
            // Jeśli jest pionek do ruchu, wykonuje ruch
            pawnToMove.move(steps, board);
        }
    }
    @Override
    public String getUserType() {
        return "Bot";
    }

}