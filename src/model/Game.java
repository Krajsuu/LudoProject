package model;

import javax.swing.*;
import java.awt.Point;
import java.awt.*;
import java.util.ArrayList;


public class Game {
    private ArrayList<Player> players;
    private Dice dice;
    private ArrayList<Pawn> redPawns;
    private ArrayList<Pawn> bluePawns;
    private ArrayList<Pawn> yellowPawns;
    private ArrayList<Pawn> greenPawns;

    private void initializeGame(){

        for(Player player : players){
            if(player.getColor() == Color.RED){
                Pawn redPawn1 = new Pawn(player,new Point(1,1),new Point(7,6),new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                redPawns.add(redPawn1);
                Pawn redPawn2 = new Pawn(player,new Point(2,1),new Point(7,6),new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                redPawns.add(redPawn2);
                Pawn redPawn3 = new Pawn(player,new Point(1,2),new Point(7,6),new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                redPawns.add(redPawn3);
                Pawn redPawn4 = new Pawn(player,new Point(2,2),new Point(7,6),new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                redPawns.add(redPawn4);
                for(Pawn pawn : redPawns){
                    player.addPawnToHome(pawn);
                }
            }
            else if(player.getColor() == Color.BLUE){
                Pawn bluepawn1 = new Pawn(player,new Point(10,1),new Point(7,6),new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                bluePawns.add(bluepawn1);
                Pawn bluepawn2 = new Pawn(player,new Point(11,1),new Point(7,6),new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                bluePawns.add(bluepawn2);
                Pawn bluepawn3 = new Pawn(player,new Point(10,2),new Point(7,6),new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                bluePawns.add(bluepawn3);
                Pawn bluepawn4 = new Pawn(player,new Point(11,2),new Point(7,6),new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                bluePawns.add(bluepawn4);
                for(Pawn pawn : bluePawns){
                    player.addPawnToHome(pawn);
                }
            }
            else if(player.getColor() == Color.YELLOW){
                Pawn yellowpawn1 = new Pawn(player,new Point(10,10),new Point(7,6),new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                yellowPawns.add(yellowpawn1);
                Pawn yellowpawn2 = new Pawn(player,new Point(11,11),new Point(7,6),new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                yellowPawns.add(yellowpawn2);
                Pawn yellowpawn3 = new Pawn(player,new Point(10,11),new Point(7,6),new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                yellowPawns.add(yellowpawn3);
                Pawn yellowpawn4 = new Pawn(player,new Point(11,10),new Point(7,6),new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                yellowPawns.add(yellowpawn4);
                for(Pawn pawn : yellowPawns){
                    player.addPawnToHome(pawn);
                }
            }
            else if(player.getColor() == Color.GREEN){
                Pawn greenpawn1 = new Pawn(player,new Point(10,1),new Point(7,6),new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                greenPawns.add(greenpawn1);
                Pawn greenpawn2 = new Pawn(player,new Point(11,1),new Point(7,6),new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                greenPawns.add(greenpawn2);
                Pawn greenpawn3 = new Pawn(player,new Point(10,2),new Point(7,6),new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                greenPawns.add(greenpawn3);
                Pawn greenpawn4 = new Pawn(player,new Point(11,2),new Point(7,6),new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                greenPawns.add(greenpawn4);
                for(Pawn pawn : greenPawns){
                    player.addPawnToHome(pawn);
                }
            }


        }


    }
    public Game(ArrayList<User> users) {
        this.players = new ArrayList<>();
        this.dice = new Dice();
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

    public ArrayList<Pawn> getRedPawns(){
        return redPawns;
    }
    public ArrayList<Pawn> getBluePawns(){
        return bluePawns;
    }
    public ArrayList<Pawn> getYellowPawns(){
        return yellowPawns;
    }
    public ArrayList<Pawn> getGreenPawns(){
        return greenPawns;
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }

    public int rollDice(){
        return dice.roll();
    }



}