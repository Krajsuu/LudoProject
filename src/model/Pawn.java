package model;

import java.awt.Point;
import javax.swing.ImageIcon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.io.IOException;
import components.PawnComponent;

public class Pawn implements Serializable {
    private static final long serialVersionUID = 1L;

    private Player owner;
    private Point currentPosition;
    private Point homePosition;
    private Point startPosition;
    private Point goalPosition;
    private transient ImageIcon pawnIcon;
    private boolean isFinished;
    private ArrayList<Point> walkTable;
    private PawnComponent pawnComponent;
    private int walkTableIndex;
    private boolean isFirstMove = true;

    public Pawn(Player owner, Point homePosition, Point startPosition, ArrayList<Point> walkTable, ImageIcon icon) {
        this.owner = owner;
        this.homePosition = homePosition;
        this.startPosition = startPosition;
        rotateStartPosition(); // Rotacja startowej pozycji
        this.walkTable = walkTable;
        this.pawnIcon = icon;
        this.currentPosition = homePosition;
        this.isFinished = false;
        this.pawnComponent = new PawnComponent(this);
        this.walkTableIndex = 0;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.isFirstMove = firstMove;
    }


    public String getType() {
        return owner.getColor() + " Pawn";
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Point position) {
        this.currentPosition = position;
    }

    public Point getStartPosition() {
        return startPosition;
    }

    public boolean isAtHome() {
        return currentPosition.equals(homePosition);
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void markFinished() {
        this.isFinished = true;
    }

    public void move(int steps) {

    }

    public void returnToBase() {
        this.currentPosition = homePosition;
    }

    public ImageIcon getPawnIcon() {
        return pawnIcon;
    }

    public void setPawnIcon(ImageIcon icon) {
        this.pawnIcon = icon;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(pawnIcon != null ? pawnIcon.toString() : null);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        String iconPath = (String) in.readObject();
        this.pawnIcon = iconPath != null ? new ImageIcon(iconPath) : null;
    }

    public PawnComponent getPawnComponent() {
        return pawnComponent;
    }
    public int getWalkTableIndex(){
        return this.walkTableIndex;
    }

    public void increaseWalkTableIndex(int rollValue){
        this.walkTableIndex = this.walkTableIndex + rollValue;
        if(this.walkTableIndex >= this.walkTable.size()){
            this.walkTableIndex = this.walkTableIndex - rollValue;
        }
    }
    public void rotateStartPosition() {
        this.startPosition = new Point(startPosition.y, 12 - startPosition.x);
    }


    public ArrayList<Point> getWalkTable(){
        return this.walkTable;
    }
}
