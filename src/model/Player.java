package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Player extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Pawn> playerPawns;
    private List<Pawn> pawnsAtBase; // pionki w bazie
    private List<Pawn> pawnsInGame; // Pionki na planszy
    private List<Pawn> pawnsAtHome; // pionki w domku
    private transient Pawn currentPawn; // obecny pionek w grze (transient, bo może być null)
    private boolean hasExtraRoll;
    private boolean hasTurn;

    public Player(String username, Color color) {
        super(username, color);
        this.pawnsAtBase = new ArrayList<>();
        this.pawnsInGame = new ArrayList<>();
        this.pawnsAtHome = new ArrayList<>();

        this.hasExtraRoll = false;
        this.currentPawn = null;
        this.hasTurn = false;
        this.playerPawns = new ArrayList<>();
    }

    // Konstruktor bezargumentowy wymagany do deserializacji
    public Player() {
        super("", Color.BLACK); // Domyślne wartości
        this.pawnsAtBase = new ArrayList<>();
        this.pawnsInGame = new ArrayList<>();
        this.pawnsAtHome = new ArrayList<>();
        this.hasExtraRoll = false;
        this.currentPawn = null;
    }


    public void setPlayerPawns(List<Pawn> playerPawns) {
        this.playerPawns = playerPawns;
    }

    public List<Pawn> getPlayerPawns() {
        return playerPawns;
    }
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        out.defaultWriteObject();
        out.writeObject(currentPawn != null ? pawnsInGame.indexOf(currentPawn) : -1);
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        int currentPawnIndex = (int) in.readObject();
        if (currentPawnIndex >= 0 && currentPawnIndex < pawnsInGame.size()) {
            currentPawn = pawnsInGame.get(currentPawnIndex);
        } else {
            currentPawn = null;
        }
    }

    public void addPawnToHome(Pawn pawn) {
        pawnsAtBase.add(pawn);
    }

    public void movePawnToGame(Pawn pawn) {
        if (pawnsAtBase.contains(pawn)) {
            pawnsAtBase.remove(pawn);
            pawnsInGame.add(pawn);
        } else {
            throw new IllegalArgumentException("Pawn is not in base");
        }
    }

    public void movePawnToBase(Pawn pawn) {
        if (pawnsInGame.contains(pawn)) {
            pawnsInGame.remove(pawn);
            pawnsAtBase.add(pawn);
        } else {
            throw new IllegalArgumentException("Pawn is not in game list");
        }
    }

    public void movePawnToHome(Pawn pawn) {
        if (pawnsInGame.contains(pawn)) {
            pawnsInGame.remove(pawn);
            pawnsAtHome.add(pawn);
        } else {
            throw new IllegalArgumentException("Pawn is not in game list");
        }
    }

    public List<Pawn> getPawnsAtBase() {
        return pawnsAtBase;
    }

    public List<Pawn> getPawnsInGame() {
        return pawnsInGame;
    }

    public List<Pawn> getPawnsAtHome() {
        return pawnsAtHome;
    }

    public Pawn getCurrentPawn() {
        return currentPawn;
    }

    public void setCurrentPawn(Pawn pawn) {
        if (pawnsInGame.contains(pawn)) {
            this.currentPawn = pawn;
        } else {
            throw new IllegalArgumentException("Pawn does not belong to this player or not in game");
        }
    }

    public void grantExtraRoll() {
        this.hasExtraRoll = true;
    }

    public boolean hasExtraRoll() {
        return hasExtraRoll;
    }

    public void useExtraRoll() {
        this.hasExtraRoll = false;
    }

    public String getUserType() {
        return "Player";
    }

    public boolean DoesHaveTurn(){
        return hasTurn;
    }
    //peak nazewnictwo
    public void GiveTurn(){
        hasTurn = true;
    }
    public void NotGiveTurn(){
        hasTurn = false;
    }
}
