package model;

import gui.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import utils.Constants;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Player> players; // Gracze w grze
    private Dice dice;
    private GameFrame gameFrame;

    private Player currentPlayer;

    private ArrayList<Pawn> redPawns;
    private ArrayList<Pawn> bluePawns;
    private ArrayList<Pawn> yellowPawns;
    private ArrayList<Pawn> greenPawns;

    private ArrayList<Point> redWalkTable;
    private ArrayList<Point> blueWalkTable;
    private ArrayList<Point> yellowWalkTable;
    private ArrayList<Point> greenWalkTable;

    public Game(ArrayList<User> users, GameFrame gameFrame) {
        this.players = new ArrayList<>();
        this.dice = new Dice();
        this.gameFrame = gameFrame;

        this.redPawns = new ArrayList<>();
        this.bluePawns = new ArrayList<>();
        this.yellowPawns = new ArrayList<>();
        this.greenPawns = new ArrayList<>();



        //Tablice do przejscia z pozycjami pionków
        this.redWalkTable = new ArrayList<>(Arrays.asList(
                new Point(5,11),
                new Point(5, 10),
                new Point(5, 9),
                new Point(5, 8),
                new Point(5, 7),
                new Point(4, 7),
                new Point(3, 7),
                new Point(2, 7),
                new Point(1, 7),
                new Point(0, 7),
                new Point(0, 6),
                new Point(0, 5),
                new Point(1, 5),
                new Point(2, 5),
                new Point(3, 5),
                new Point(4, 5),
                new Point(5, 5),
                new Point(5, 4),
                new Point(5, 3),
                new Point(5, 2),
                new Point(5, 1),
                new Point(5, 0),
                new Point(6, 0),
                new Point(7, 0),
                new Point(7, 1),
                new Point(7, 2),
                new Point(7, 3),
                new Point(7, 4),
                new Point(7, 5),
                new Point(8, 5),
                new Point(9, 5),
                new Point(10, 5),
                new Point(11, 5),
                new Point(12, 5),
                new Point(12, 6),
                new Point(12, 7),
                new Point(11, 7),
                new Point(10, 7),
                new Point(9, 7),
                new Point(8, 7),
                new Point(7, 7),
                new Point(7, 8),
                new Point(7, 9),
                new Point(7, 10),
                new Point(7, 11),
                new Point(7, 12),
                new Point(6, 12),
                new Point(6, 11),
                new Point(6, 10),
                new Point(6, 9),
                new Point(6, 8),
                new Point(6, 7)));

        this.blueWalkTable = new ArrayList<>(Arrays.asList(
                new Point(11, 7),
                new Point(10, 7),
                new Point(9, 7),
                new Point(8, 7),
                new Point(7, 7),
                new Point(7, 8),
                new Point(7, 9),
                new Point(7, 10),
                new Point(7, 11),
                new Point(7, 12),
                new Point(6, 12),
                new Point(5, 12),
                new Point(5,11),
                new Point(5, 10),
                new Point(5, 9),
                new Point(5, 8),
                new Point(5, 7),
                new Point(4, 7),
                new Point(3, 7),
                new Point(2, 7),
                new Point(1, 7),
                new Point(0, 7),
                new Point(0, 6),
                new Point(0, 5),
                new Point(1, 5),
                new Point(2, 5),
                new Point(3, 5),
                new Point(4, 5),
                new Point(5, 5),
                new Point(5, 4),
                new Point(5, 3),
                new Point(5, 2),
                new Point(5, 1),
                new Point(5, 0),
                new Point(6, 0),
                new Point(7, 0),
                new Point(7, 1),
                new Point(7, 2),
                new Point(7, 3),
                new Point(7, 4),
                new Point(7, 5),
                new Point(8, 5),
                new Point(9, 5),
                new Point(10, 5),
                new Point(11, 5),
                new Point(12, 5),
                new Point(12, 6),
                new Point(11, 6),
                new Point(10, 6),
                new Point(9, 6),
                new Point(8, 6),
                new Point(7, 6)));


        this.yellowWalkTable = new ArrayList<>(Arrays.asList(
                new Point(7, 1),
                new Point(7, 2),
                new Point(7, 3),
                new Point(7, 4),
                new Point(7, 5),
                new Point(8, 5),
                new Point(9, 5),
                new Point(10, 5),
                new Point(11, 5),
                new Point(12, 5),
                new Point(12, 6),
                new Point(12, 7),
                new Point(11, 7),
                new Point(10, 7),
                new Point(9, 7),
                new Point(8, 7),
                new Point(7, 7),
                new Point(7, 8),
                new Point(7, 9),
                new Point(7, 10),
                new Point(7, 11),
                new Point(7, 12),
                new Point(6, 12),
                new Point(5, 12),
                new Point(5,11),
                new Point(5, 10),
                new Point(5, 9),
                new Point(5, 8),
                new Point(5, 7),
                new Point(4, 7),
                new Point(3, 7),
                new Point(2, 7),
                new Point(1, 7),
                new Point(0, 7),
                new Point(0, 6),
                new Point(0, 5),
                new Point(1, 5),
                new Point(2, 5),
                new Point(3, 5),
                new Point(4, 5),
                new Point(5, 5),
                new Point(5, 4),
                new Point(5, 3),
                new Point(5, 2),
                new Point(5, 1),
                new Point(5, 0),
                new Point(6, 0),
                new Point(6, 1),
                new Point(6, 2),
                new Point(6, 3),
                new Point(6, 4),
                new Point(6, 5)
                ));


        this.greenWalkTable = new ArrayList<>(Arrays.asList(
                new Point(1, 5),
                new Point(2, 5),
                new Point(3, 5),
                new Point(4, 5),
                new Point(5, 5),
                new Point(5, 4),
                new Point(5, 3),
                new Point(5, 2),
                new Point(5, 1),
                new Point(5, 0),
                new Point(6,0),
                new Point(7, 0),
                new Point(7, 1),
                new Point(7, 2),
                new Point(7, 3),
                new Point(7, 4),
                new Point(7, 5),
                new Point(8, 5),
                new Point(9, 5),
                new Point(10, 5),
                new Point(11, 5),
                new Point(12, 5),
                new Point(12, 6),
                new Point(12, 7),
                new Point(11, 7),
                new Point(10, 7),
                new Point(9, 7),
                new Point(8, 7),
                new Point(7, 7),
                new Point(7, 8),
                new Point(7, 9),
                new Point(7, 10),
                new Point(7, 11),
                new Point(7, 12),
                new Point(6, 12),
                new Point(5, 12),
                new Point(5,11),
                new Point(5, 10),
                new Point(5, 9),
                new Point(5, 8),
                new Point(5, 7),
                new Point(4, 7),
                new Point(3, 7),
                new Point(2, 7),
                new Point(1, 7),
                new Point(0, 7),
                new Point(0, 6),
                new Point(1, 6),
                new Point(2, 6),
                new Point(3, 6),
                new Point(4, 6),
                new Point(5, 6)));



        for (User user : users) {
            Player player = new Player(user.getUsername(), user.getColor());
            players.add(player);
        }
        initializeGame();
        this.currentPlayer = players.get(0);

        for(Pawn pawn : currentPlayer.getPlayerPawns()){
            pawn.getPawnComponent().makeClickable(true);
        }
        gameFrame.diceclickability(true);
    }

    private void initializeGame() {
        //narazie zaczyna gra od pierwszego mozna bdz zrobic losowo kiedy
        players.get(0).GiveTurn();
        for (Player player : players) {
            Color c = player.getColor();
            if (c == Color.GREEN) {
                Pawn g1 = new Pawn(player, new Point(1, 1), new Point(1,5), greenWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn g2 = new Pawn(player, new Point(3, 1), new Point(1,5), greenWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn g3 = new Pawn(player, new Point(1, 3), new Point(1,5), greenWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn g4 = new Pawn(player, new Point(3, 3), new Point(1,5), greenWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));

                greenPawns.add(g1);
                greenPawns.add(g2);
                greenPawns.add(g3);
                greenPawns.add(g4);

                player.addPawnToBase(g1);
                player.addPawnToBase(g2);
                player.addPawnToBase(g3);
                player.addPawnToBase(g4);
                player.setPlayerPawns(Arrays.asList(g1, g2, g3, g4));

                gameFrame.setPawn(g1);
                gameFrame.setPawn(g2);
                gameFrame.setPawn(g3);
                gameFrame.setPawn(g4);

            } else if (c == Color.RED) {
                Pawn r1 = new Pawn(player, new Point(9, 1), new Point(5, 11), redWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn r2 = new Pawn(player, new Point(9, 3),  new Point(5, 11), redWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn r3 = new Pawn(player, new Point(11, 1),  new Point(5, 11), redWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn r4 = new Pawn(player, new Point(11, 3),  new Point(5, 11), redWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));

                redPawns.add(r1);
                redPawns.add(r2);
                redPawns.add(r3);
                redPawns.add(r4);

                player.addPawnToBase(r1);
                player.addPawnToBase(r2);
                player.addPawnToBase(r3);
                player.addPawnToBase(r4);
                player.setPlayerPawns(Arrays.asList(r1, r2, r3, r4));
                gameFrame.setPawn(r1);
                gameFrame.setPawn(r2);
                gameFrame.setPawn(r3);
                gameFrame.setPawn(r4);


            } else if (c == Color.YELLOW) {
                Pawn y1 = new Pawn(player, new Point(1, 9), new Point(7, 1), yellowWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn y2 = new Pawn(player, new Point(1, 11), new Point(7, 1), yellowWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn y3 = new Pawn(player, new Point(3, 9), new Point(7, 1), yellowWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn y4 = new Pawn(player, new Point(3, 11), new Point(7, 1), yellowWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));

                yellowPawns.add(y1);
                yellowPawns.add(y2);
                yellowPawns.add(y3);
                yellowPawns.add(y4);

                player.addPawnToBase(y1);
                player.addPawnToBase(y2);
                player.addPawnToBase(y3);
                player.addPawnToBase(y4);
                player.setPlayerPawns(Arrays.asList(y1, y2, y3, y4));
                gameFrame.setPawn(y1);
                gameFrame.setPawn(y2);
                gameFrame.setPawn(y3);
                gameFrame.setPawn(y4);


            } else if (c == Color.BLUE) {
                Pawn b1 = new Pawn(player, new Point(9, 9),new Point(11, 7), blueWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn b2 = new Pawn(player, new Point(11, 9), new Point(11, 7), blueWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn b3 = new Pawn(player, new Point(9, 11), new Point(11, 7), blueWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn b4 = new Pawn(player, new Point(11, 11), new Point(11, 7), blueWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));

                bluePawns.add(b1);
                bluePawns.add(b2);
                bluePawns.add(b3);
                bluePawns.add(b4);
                player.setPlayerPawns(Arrays.asList(b1, b2, b3, b4));
                player.addPawnToBase(b1);
                player.addPawnToBase(b2);
                player.addPawnToBase(b3);
                player.addPawnToBase(b4);

                gameFrame.setPawn(b1);
                gameFrame.setPawn(b2);
                gameFrame.setPawn(b3);
                gameFrame.setPawn(b4);
            }
        }



    }

    public void nextTurn() {
        int currentPlayerIndex = players.indexOf(currentPlayer);
        currentPlayer.NotGiveTurn();
        int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
        players.get(nextPlayerIndex).GiveTurn();
        //disable old player pawns
        for (Pawn pawn : currentPlayer.getPlayerPawns()) {
            pawn.getPawnComponent().makeClickable(false);
        }

        currentPlayer = players.get(nextPlayerIndex);
        //enable new player pawns
        for(Pawn pawn : currentPlayer.getPlayerPawns()){
            pawn.getPawnComponent().makeClickable(true);
        }
        gameFrame.diceclickability(true);
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

    //Rollowanie kostki
    public int rollDice() {
        return dice.roll();

    }

    private static final int REQUIRED_ROLL_FOR_NEW_PAWN = 6;

    public void thisTurn(int diceRollValue) {
        Pawn currentPawn = currentPlayer.getCurrentPawn();
        if (shouldMovePawnFromBase(diceRollValue)) {
            gameFrame.movePawn(currentPawn,currentPawn.getStartPosition());
            currentPlayer.movePawnToGame(currentPawn);
        }
        if(shouldPawnMove())
        {
            currentPawn.increaseWalkTableIndex(diceRollValue);
            gameFrame.movePawn(currentPawn,currentPawn.getWalkTable().get(currentPawn.getWalkTableIndex()));
        }
        nextTurn();
        gameFrame.updateDicePanelColor(currentPlayer);

    }

    private boolean shouldMovePawnFromBase(int diceRollValue) {
        return diceRollValue == REQUIRED_ROLL_FOR_NEW_PAWN && !currentPlayer.getPawnsAtBase().isEmpty() && currentPlayer.getCurrentPawn().isAtHome();
    }

    private boolean shouldPawnMove(){
        return !currentPlayer.getPawnsInGame().isEmpty() && currentPlayer.getCurrentPawn() != null && !currentPlayer.getCurrentPawn().isAtHome();
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
