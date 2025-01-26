// Player.java
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Player extends User implements Serializable { // Dziedziczenie po Serializable
    private static final long serialVersionUID = 1L;

    private List<Pawn> playerPawns;
    private List<Pawn> pawnsAtBase; // pionki w bazie
    private List<Pawn> pawnsInGame; // pionki na planszy
    private List<Pawn> pawnsAtHome; // pionki w domku
    private transient Pawn currentPawn;
    private boolean hasExtraRoll;
    private boolean hasTurn;

    // NOWOŚĆ: flaga informująca, czy gracz ukończył już grę
    private boolean finished = false;

    // Konstruktor z argumentami
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

    // Konstruktor bezargumentowy
    public Player() {
        super(); // Wywołuje konstruktor bezargumentowy klasy User
        this.pawnsAtBase = new ArrayList<>();
        this.pawnsInGame = new ArrayList<>();
        this.pawnsAtHome = new ArrayList<>();
        this.hasExtraRoll = false;
        this.currentPawn = null;
        this.hasTurn = false;
        this.playerPawns = new ArrayList<>();
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

    public void addPawnToBase(Pawn pawn) {
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
        this.currentPawn = pawn;
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

    public void GiveTurn(){
        hasTurn = true;
    }

    public void NotGiveTurn(){
        hasTurn = false;
    }

    // Liczba pionków jeszcze niewprowadzonych do mety
    public int getPawnsRemaining() {
        return pawnsAtBase.size() + pawnsInGame.size();
    }

    // NOWOŚĆ: Zakończył rozgrywkę?
    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
