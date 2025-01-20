package model;

import gui.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import utils.Constants;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Player> players; // Gracze w grze
    private Dice dice;
    private GameFrame gameFrame;

    private ArrayList<Pawn> redPawns;
    private ArrayList<Pawn> bluePawns;
    private ArrayList<Pawn> yellowPawns;
    private ArrayList<Pawn> greenPawns;

    public Game(ArrayList<User> users, GameFrame gameFrame) {
        this.players = new ArrayList<>();
        this.dice = new Dice();
        this.gameFrame = gameFrame;

        this.redPawns = new ArrayList<>();
        this.bluePawns = new ArrayList<>();
        this.yellowPawns = new ArrayList<>();
        this.greenPawns = new ArrayList<>();

        for (User user : users) {
            Player player = new Player(user.getUsername(), user.getColor());
            players.add(player);
        }
        initializeGame();
    }

    private void initializeGame() {
        for (Player player : players) {
            Color c = player.getColor();

            if (c == Color.GREEN) {
                Pawn g1 = new Pawn(player, new Point(1, 1),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn g2 = new Pawn(player, new Point(3, 1),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn g3 = new Pawn(player, new Point(1, 3),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn g4 = new Pawn(player, new Point(3, 3),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));

                greenPawns.add(g1);
                greenPawns.add(g2);
                greenPawns.add(g3);
                greenPawns.add(g4);

                player.addPawnToHome(g1);
                player.addPawnToHome(g2);
                player.addPawnToHome(g3);
                player.addPawnToHome(g4);

                gameFrame.setPawn(g1);
                gameFrame.setPawn(g2);
                gameFrame.setPawn(g3);
                gameFrame.setPawn(g4);

            } else if (c == Color.RED) {
                Pawn r1 = new Pawn(player, new Point(9, 1),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn r2 = new Pawn(player, new Point(9, 3),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn r3 = new Pawn(player, new Point(11, 1),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn r4 = new Pawn(player, new Point(11, 3),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));

                redPawns.add(r1);
                redPawns.add(r2);
                redPawns.add(r3);
                redPawns.add(r4);

                player.addPawnToHome(r1);
                player.addPawnToHome(r2);
                player.addPawnToHome(r3);
                player.addPawnToHome(r4);

                gameFrame.setPawn(r1);
                gameFrame.setPawn(r2);
                gameFrame.setPawn(r3);
                gameFrame.setPawn(r4);


            } else if (c == Color.YELLOW) {
                Pawn y1 = new Pawn(player, new Point(1, 9),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn y2 = new Pawn(player, new Point(1, 11),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn y3 = new Pawn(player, new Point(3, 9),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn y4 = new Pawn(player, new Point(3, 11),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));

                yellowPawns.add(y1);
                yellowPawns.add(y2);
                yellowPawns.add(y3);
                yellowPawns.add(y4);

                player.addPawnToHome(y1);
                player.addPawnToHome(y2);
                player.addPawnToHome(y3);
                player.addPawnToHome(y4);

                gameFrame.setPawn(y1);
                gameFrame.setPawn(y2);
                gameFrame.setPawn(y3);
                gameFrame.setPawn(y4);


            } else if (c == Color.BLUE) {
                Pawn b1 = new Pawn(player, new Point(9, 9),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn b2 = new Pawn(player, new Point(11, 9),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn b3 = new Pawn(player, new Point(9, 11),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn b4 = new Pawn(player, new Point(11, 11),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));

                bluePawns.add(b1);
                bluePawns.add(b2);
                bluePawns.add(b3);
                bluePawns.add(b4);

                player.addPawnToHome(b1);
                player.addPawnToHome(b2);
                player.addPawnToHome(b3);
                player.addPawnToHome(b4);

                gameFrame.setPawn(b1);
                gameFrame.setPawn(b2);
                gameFrame.setPawn(b3);
                gameFrame.setPawn(b4);
            }
        }

    }

    public ArrayList<Pawn> getRedPawns() {
        return redPawns;
    }

    public ArrayList<Pawn> getBluePawns() {
        return bluePawns;
    }

    public ArrayList<Pawn> getYellowPawns() {
        return yellowPawns;
    }

    public ArrayList<Pawn> getGreenPawns() {
        return greenPawns;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int rollDice() {
        return dice.roll();
    }


    /**
     * Dodano metodę getUsers do zwracania listy użytkowników w grze.
     *
     * @return lista obiektów User
     */
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>(players);
        return users;
    }
}
