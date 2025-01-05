package model;


import utils.SwingColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends User {
    private SwingColor color;
    private List<Pawn> pawnsAtBase; //pionki w bazie
    private List<Pawn> pawnsInGame; //Pionki na planszy
    private List<Pawn> pawnsAtHome; // pionki w domku
    private Pawn currentPawn;  // obecny pionek w grze
    private boolean hasExtraRoll;
    private boolean hasTurn;

    //konstruktor
    public Player(String username,SwingColor color) {
        super(username);
        this.color = color;
        this.pawnsAtBase = new ArrayList<>();
        this.pawnsInGame = new ArrayList<>();
        this.pawnsAtHome = new ArrayList<>();
        this.hasExtraRoll = false;
        this.currentPawn = null;
    }

    public SwingColor getColor() {
        return color;
    }
    public void setColor(SwingColor color) {
        this.color = color;
    }
    //dodanie pionka do domku gracza
    public void addPawnToHome(Pawn pawn) {
        pawnsAtBase.add(pawn);
    }
    //przeniesienie pionka z domku do gry
    public void MovePawnToGame(Pawn pawn) {
        if(pawnsAtBase.contains(pawn)) {
            pawnsAtBase.remove(pawn);
            pawnsInGame.add(pawn);
        }
        else{
            throw new IllegalArgumentException("Pawn is not in base");
        }
    }
    //przeniesienie pionka z gry do domku(jakby został zbity)
    public void movePawnToBase(Pawn pawn) {
        if(pawnsInGame.contains(pawn)) {
            pawnsInGame.remove(pawn);
            pawnsAtBase.add(pawn);
        }
        else{
            throw new IllegalArgumentException("Pawn is not in game list");
        }
    }

    //przeniesienie pionka z gry do bazy celu idk
    public void movePawnToHome(Pawn pawn) {
        if(pawnsInGame.contains(pawn)) {
            pawnsInGame.remove(pawn);
            pawnsAtHome.add(pawn);
        }
        else{
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
        if(pawnsInGame.contains(pawn)) {
            this.currentPawn = pawn;
        }
        else {
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
}