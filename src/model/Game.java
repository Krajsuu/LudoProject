package model;
/*
public class Game {
    private Field field;
    private Player[] players;
    private int currentPlayerIndex;
    private int diceRoll;
    private boolean diceRolled;

    public Game() {
        this.field = new Field();
        this.players = new Player[4];
        for (int i = 0; i < 4; i++) {
            this.players[i] = new Bot(i);
        }
        this.currentPlayerIndex = 0;
        this.diceRoll = 0;
        this.diceRolled = false;
    }

    public Game(int players, int bots){
        this.field = new Field();
        this.players = new Player[players+bots];
        for (int i = 0; i < players; i++) {
            this.players[i] = new Player(i);
        }
        for (int i = players; i < players+bots; i++) {
            this.players[i] = new Bot(i);
        }
        this.currentPlayerIndex = 0;
        this.diceRoll = 0;
        this.diceRolled = false;
    }

    public Field getField() {
        return field;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public int getDiceRoll() {
        return diceRoll;
    }

    public boolean isDiceRolled() {
        return diceRolled;
    }

    public void rollDice() {
        diceRoll = (int) (Math.random() * 6) + 1;
        diceRolled = true;
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % 4;
        diceRoll = 0;
        diceRolled = false;
    }

    public void movePawn(int pawnIndex) {
        Pawn pawn = players[currentPlayerIndex].getPawns()[pawnIndex];
        if (pawn.isAtHome() && diceRoll == 6) {
            field.movePawn(pawn, currentPlayerIndex * 13);
        } else if (!pawn.isAtHome() && !pawn.isFinished()) {
            int newPosition = (pawn.getPosition() + diceRoll) % 52;
            field.movePawn(pawn, newPosition);
        }
        if (diceRoll != 6) {
            nextPlayer();
        }
    }

    public boolean isGameFinished() {
        for (Player player : players) {
            if (!player.isFinished()) {
                return false;
            }
        }
        return true;
    }
}
*/