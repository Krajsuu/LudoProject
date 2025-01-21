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

    public Pawn(Player owner, Point homePosition,Point startPosition,ArrayList<Point> walkTable, ImageIcon icon) {
        this.owner = owner;
        this.homePosition = homePosition;
        this.startPosition = startPosition;
        this.goalPosition = goalPosition;
        this.walkTable = walkTable;
        this.pawnIcon = icon;
        this.currentPosition = homePosition;
        this.isFinished = false;
        this.pawnComponent = new PawnComponent(this);
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

    public boolean isAtHome() {
        return currentPosition.equals(homePosition);
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void markFinished() {
        this.isFinished = true;
    }

    public void move(int steps, Field board) {
        if (isFinished) return;

        Point newPosition = board.calculateNewPosition(currentPosition, steps);
        Pawn occupyingPawn = board.checkOccupied(newPosition);

        if (occupyingPawn != null && !Objects.equals(occupyingPawn.getOwner(), this.owner)) {
            occupyingPawn.returnToBase();
            owner.grantExtraRoll();
        }

        setCurrentPosition(newPosition);
        board.updatePosition(this, newPosition);

        if (newPosition.equals(goalPosition)) {
            markFinished();
        }
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
}
