package model;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Player> players; // Gracze w grze
    private Dice dice;
    private Field board;

    private ArrayList<Pawn> redPawns;
    private ArrayList<Pawn> bluePawns;
    private ArrayList<Pawn> yellowPawns;
    private ArrayList<Pawn> greenPawns;

    public Game(ArrayList<User> users) {
        this.players = new ArrayList<>();
        this.dice = new Dice();
        this.board = new Field();

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
                Pawn g1 = new Pawn(player, new Point(0, 0), new Point(6, 6),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png")
                                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                Pawn g2 = new Pawn(player, new Point(0, 1), new Point(6, 6),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png")
                                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                Pawn g3 = new Pawn(player, new Point(0, 2), new Point(6, 6),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png")
                                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                Pawn g4 = new Pawn(player, new Point(0, 3), new Point(6, 6),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png")
                                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

                greenPawns.add(g1);
                greenPawns.add(g2);
                greenPawns.add(g3);
                greenPawns.add(g4);

                player.addPawnToHome(g1);
                player.addPawnToHome(g2);
                player.addPawnToHome(g3);
                player.addPawnToHome(g4);

                board.updatePosition(g1, g1.getCurrentPosition());
                board.updatePosition(g2, g2.getCurrentPosition());
                board.updatePosition(g3, g3.getCurrentPosition());
                board.updatePosition(g4, g4.getCurrentPosition());

            } else if (c == Color.RED) {
                Pawn r1 = new Pawn(player, new Point(12, 0), new Point(6, 6),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png")
                                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                Pawn r2 = new Pawn(player, new Point(12, 1), new Point(6, 6),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png")
                                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                Pawn r3 = new Pawn(player, new Point(12, 2), new Point(6, 6),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png")
                                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                Pawn r4 = new Pawn(player, new Point(12, 3), new Point(6, 6),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png")
                                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

                redPawns.add(r1);
                redPawns.add(r2);
                redPawns.add(r3);
                redPawns.add(r4);

                player.addPawnToHome(r1);
                player.addPawnToHome(r2);
                player.addPawnToHome(r3);
                player.addPawnToHome(r4);

                board.updatePosition(r1, r1.getCurrentPosition());
                board.updatePosition(r2, r2.getCurrentPosition());
                board.updatePosition(r3, r3.getCurrentPosition());
                board.updatePosition(r4, r4.getCurrentPosition());

            } else if (c == Color.YELLOW) {
                Pawn y1 = new Pawn(player, new Point(0, 9), new Point(6, 6),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png")
                                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                Pawn y2 = new Pawn(player, new Point(0, 10), new Point(6, 6),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png")
                                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                Pawn y3 = new Pawn(player, new Point(0, 11), new Point(6, 6),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png")
                                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                Pawn y4 = new Pawn(player, new Point(0, 12), new Point(6, 6),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png")
                                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

                yellowPawns.add(y1);
                yellowPawns.add(y2);
                yellowPawns.add(y3);
                yellowPawns.add(y4);

                player.addPawnToHome(y1);
                player.addPawnToHome(y2);
                player.addPawnToHome(y3);
                player.addPawnToHome(y4);

                board.updatePosition(y1, y1.getCurrentPosition());
                board.updatePosition(y2, y2.getCurrentPosition());
                board.updatePosition(y3, y3.getCurrentPosition());
                board.updatePosition(y4, y4.getCurrentPosition());

            } else if (c == Color.BLUE) {
                Pawn b1 = new Pawn(player, new Point(12, 9), new Point(6, 6),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png")
                                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                Pawn b2 = new Pawn(player, new Point(12, 10), new Point(6, 6),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png")
                                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                Pawn b3 = new Pawn(player, new Point(12, 11), new Point(6, 6),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png")
                                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                Pawn b4 = new Pawn(player, new Point(12, 12), new Point(6, 6),
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png")
                                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

                bluePawns.add(b1);
                bluePawns.add(b2);
                bluePawns.add(b3);
                bluePawns.add(b4);

                player.addPawnToHome(b1);
                player.addPawnToHome(b2);
                player.addPawnToHome(b3);
                player.addPawnToHome(b4);

                board.updatePosition(b1, b1.getCurrentPosition());
                board.updatePosition(b2, b2.getCurrentPosition());
                board.updatePosition(b3, b3.getCurrentPosition());
                board.updatePosition(b4, b4.getCurrentPosition());
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

    public Field getBoard() {
        return board;
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
