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

    // Pionki poszczególnych kolorów (niektóre logiki mogą z nich korzystać):
    private ArrayList<Pawn> redPawns;
    private ArrayList<Pawn> bluePawns;
    private ArrayList<Pawn> yellowPawns;
    private ArrayList<Pawn> greenPawns;

    // Ścieżki ruchu (walkTable) dla poszczególnych kolorów:
    private ArrayList<Point> redWalkTable;
    private ArrayList<Point> blueWalkTable;
    private ArrayList<Point> yellowWalkTable;
    private ArrayList<Point> greenWalkTable;

    // Stale
    private static final int REQUIRED_ROLL_FOR_NEW_PAWN = 6;

    // NOWOŚĆ: lista graczy w kolejności kończenia (1 miejsce, 2 miejsce, itd.)
    private ArrayList<Player> finishingOrder = new ArrayList<>();

    /**
     * Konstruktor: przyjmuje listę `users` (Player/Bot) oraz odwołanie do GameFrame,
     * buduje wszystkie pionki itp.
     */
    public Game(ArrayList<User> users, GameFrame gameFrame) {
        this.players = new ArrayList<>();
        this.dice = new Dice();
        this.gameFrame = gameFrame;

        // Inicjalizacja list pionków
        this.redPawns = new ArrayList<>();
        this.bluePawns = new ArrayList<>();
        this.yellowPawns = new ArrayList<>();
        this.greenPawns = new ArrayList<>();

        // Zapisujemy graczy (przyjmujemy, że w `users` są obiekty typu Player/Bot)
        for (User user : users) {
            this.players.add((Player) user);
        }

        // Przygotowujemy tablice ścieżek do przejścia
        initializeWalkTables();

        // Tworzymy właściwe pionki i ustawiamy je na planszy
        initializeGame();

        // Domyślnie: tura pierwszego gracza
        currentPlayer = players.get(0);
        currentPlayer.GiveTurn();

        // Włączamy klikalność pionków (na starcie tylko u bieżącego gracza)
        for (Pawn pawn : currentPlayer.getPlayerPawns()) {
            pawn.getPawnComponent().makeClickable(true);
        }

        gameFrame.diceclickability(true);
    }

    /**
     * Inicjalizuje tablice (listy) ze współrzędnymi pól,
     * którymi poruszają się pionki (dla każdego koloru osobna lista).
     */
    private void initializeWalkTables() {
        this.redWalkTable = new ArrayList<>(Arrays.asList(
                new Point(11, 5), new Point(10, 5), new Point(9, 5), new Point(8, 5),
                new Point(7, 5), new Point(7, 4), new Point(7, 3), new Point(7, 2),
                new Point(7, 1), new Point(7, 0), new Point(6, 0), new Point(5, 0),
                new Point(5, 1), new Point(5, 2), new Point(5, 3), new Point(5, 4),
                new Point(5, 5), new Point(4, 5), new Point(3, 5), new Point(2, 5),
                new Point(1, 5), new Point(0, 5), new Point(0, 6), new Point(0, 7),
                new Point(1, 7), new Point(2, 7), new Point(3, 7), new Point(4, 7),
                new Point(5, 7), new Point(5, 8), new Point(5, 9), new Point(5, 10),
                new Point(5, 11), new Point(5, 12), new Point(6, 12), new Point(7, 12),
                new Point(7, 11), new Point(7, 10), new Point(7, 9), new Point(7, 8),
                new Point(7, 7), new Point(8, 7), new Point(9, 7), new Point(10, 7),
                new Point(11, 7), new Point(12, 7), new Point(12, 6), new Point(11, 6),
                new Point(10, 6), new Point(9, 6), new Point(8, 6), new Point(7, 6),
                new Point(6, 6)
        ));

        this.blueWalkTable = new ArrayList<>(Arrays.asList(
                new Point(7, 11), new Point(7, 10), new Point(7, 9), new Point(7, 8),
                new Point(7, 7), new Point(8, 7), new Point(9, 7), new Point(10, 7),
                new Point(11, 7), new Point(12, 7), new Point(12, 6), new Point(12, 5),
                new Point(11, 5), new Point(10, 5), new Point(9, 5), new Point(8, 5),
                new Point(7, 5), new Point(7, 4), new Point(7, 3), new Point(7, 2),
                new Point(7, 1), new Point(7, 0), new Point(6, 0), new Point(5, 0),
                new Point(5, 1), new Point(5, 2), new Point(5, 3), new Point(5, 4),
                new Point(5, 5), new Point(4, 5), new Point(3, 5), new Point(2, 5),
                new Point(1, 5), new Point(0, 5), new Point(0, 6), new Point(0, 7),
                new Point(1, 7), new Point(2, 7), new Point(3, 7), new Point(4, 7),
                new Point(5, 7), new Point(5, 8), new Point(5, 9), new Point(5, 10),
                new Point(5, 11), new Point(5, 12), new Point(6, 12), new Point(6, 11),
                new Point(6, 10), new Point(6, 9), new Point(6, 8), new Point(6, 7),
                new Point(6, 6)
        ));

        this.yellowWalkTable = new ArrayList<>(Arrays.asList(
                new Point(1, 7), new Point(2, 7), new Point(3, 7), new Point(4, 7),
                new Point(5, 7), new Point(5, 8), new Point(5, 9), new Point(5, 10),
                new Point(5, 11), new Point(5, 12), new Point(6, 12), new Point(7, 12),
                new Point(7, 11), new Point(7, 10), new Point(7, 9), new Point(7, 8),
                new Point(7, 7), new Point(8, 7), new Point(9, 7), new Point(10, 7),
                new Point(11, 7), new Point(12, 7), new Point(12, 6), new Point(12, 5),
                new Point(11, 5), new Point(10, 5), new Point(9, 5), new Point(8, 5),
                new Point(7, 5), new Point(7, 4), new Point(7, 3), new Point(7, 2),
                new Point(7, 1), new Point(7, 0), new Point(6, 0), new Point(5, 0),
                new Point(5, 1), new Point(5, 2), new Point(5, 3), new Point(5, 4),
                new Point(5, 5), new Point(4, 5), new Point(3, 5), new Point(2, 5),
                new Point(1, 5), new Point(0, 5), new Point(0, 6), new Point(1, 6),
                new Point(2, 6), new Point(3, 6), new Point(4, 6), new Point(5, 6),
                new Point(6, 6)
        ));

        this.greenWalkTable = new ArrayList<>(Arrays.asList(
                new Point(5, 1), new Point(5, 2), new Point(5, 3), new Point(5, 4),
                new Point(5, 5), new Point(4, 5), new Point(3, 5), new Point(2, 5),
                new Point(1, 5), new Point(0, 5), new Point(0, 6), new Point(0, 7),
                new Point(1, 7), new Point(2, 7), new Point(3, 7), new Point(4, 7),
                new Point(5, 7), new Point(5, 8), new Point(5, 9), new Point(5, 10),
                new Point(5, 11), new Point(5, 12), new Point(6, 12), new Point(7, 12),
                new Point(7, 11), new Point(7, 10), new Point(7, 9), new Point(7, 8),
                new Point(7, 7), new Point(8, 7), new Point(9, 7), new Point(10, 7),
                new Point(11, 7), new Point(12, 7), new Point(12, 6), new Point(12, 5),
                new Point(11, 5), new Point(10, 5), new Point(9, 5), new Point(8, 5),
                new Point(7, 5), new Point(7, 4), new Point(7, 3), new Point(7, 2),
                new Point(7, 1), new Point(7, 0), new Point(6, 0), new Point(6, 1),
                new Point(6, 2), new Point(6, 3), new Point(6, 4), new Point(6, 5),
                new Point(6, 6)
        ));
    }

    /**
     * Tworzy właściwe pionki (4 na gracza), ustawia je w bazach
     * i dołącza do panelu w GameFrame (setPawn).
     */
    private void initializeGame() {
        // Pierwszy gracz ma turę
        players.get(0).GiveTurn();

        for (Player player : players) {
            Color c = player.getColor();

            if (c == Color.GREEN) {
                Pawn g1 = new Pawn(player, new Point(1, 1), new Point(7,11), greenWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn g2 = new Pawn(player, new Point(3, 1), new Point(7,11), greenWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn g3 = new Pawn(player, new Point(1, 3), new Point(7,11), greenWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnGreen.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn g4 = new Pawn(player, new Point(3, 3), new Point(7,11), greenWalkTable,
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
                Pawn r1 = new Pawn(player, new Point(9, 1), new Point(1, 7), redWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn r2 = new Pawn(player, new Point(9, 3),  new Point(1, 7), redWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn r3 = new Pawn(player, new Point(11, 1),  new Point(1, 7), redWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnRed.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn r4 = new Pawn(player, new Point(11, 3),  new Point(1, 7), redWalkTable,
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
                Pawn y1 = new Pawn(player, new Point(1, 9), new Point(11, 5), yellowWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn y2 = new Pawn(player, new Point(1, 11), new Point(11, 5), yellowWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn y3 = new Pawn(player, new Point(3, 9), new Point(11, 5), yellowWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnYellow.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn y4 = new Pawn(player, new Point(3, 11), new Point(11, 5), yellowWalkTable,
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
                Pawn b1 = new Pawn(player, new Point(9, 9), new Point(5, 1), blueWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn b2 = new Pawn(player, new Point(11, 9), new Point(5, 1), blueWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn b3 = new Pawn(player, new Point(9, 11), new Point(5, 1), blueWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));
                Pawn b4 = new Pawn(player, new Point(11, 11), new Point(5, 1), blueWalkTable,
                        new ImageIcon(new ImageIcon("data/images/pawnsImages/pawnBlue.png")
                                .getImage().getScaledInstance(Constants.PAWN_SIZE, Constants.PAWN_SIZE, Image.SCALE_SMOOTH)));

                bluePawns.add(b1);
                bluePawns.add(b2);
                bluePawns.add(b3);
                bluePawns.add(b4);

                player.addPawnToBase(b1);
                player.addPawnToBase(b2);
                player.addPawnToBase(b3);
                player.addPawnToBase(b4);
                player.setPlayerPawns(Arrays.asList(b1, b2, b3, b4));

                gameFrame.setPawn(b1);
                gameFrame.setPawn(b2);
                gameFrame.setPawn(b3);
                gameFrame.setPawn(b4);
            }
        }
    }

    /**
     * Sprawdza, czy wszyscy gracze skończyli (czyli `isFinished() == true`).
     */
    private boolean allPlayersFinished() {
        for (Player p : players) {
            if (!p.isFinished()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Wyświetla finalny ranking w postaci kolejności w `finishingOrder`.
     * Gdy gra się kończy (wszyscy gracze finished), pokazujemy zbiorczy komunikat.
     */
    private void showFinalRankingAndEnd() {
        // Upewniamy się, że ci, którzy są finished, a nie ma ich w finishingOrder,
        // też zostaną dopisani (na wypadek, gdyby zakończyli się "równocześnie").
        for (Player p : players) {
            if (p.isFinished() && !finishingOrder.contains(p)) {
                finishingOrder.add(p);
            }
        }

        StringBuilder sb = new StringBuilder("Koniec gry!\nOto kolejność wygranych:\n");
        for (int i = 0; i < finishingOrder.size(); i++) {
            Player p = finishingOrder.get(i);
            sb.append(i + 1).append(". ").append(p.getUsername()).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
        // Tu ewentualnie jakaś akcja typu: zamknij okno / powrót do menu itp.
        // np. System.exit(0) albo:
        // gameFrame.returnToMenu();  (w zależności co mamy w GameFrame)
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void handleBotTurn() {
        if (currentPlayer.getUserType().equals("Bot")) {
            Bot bot = (Bot) currentPlayer;
            int diceRollValue = rollDice();
            String imagePath = "data/images/diceImages/" + diceRollValue + ".png";
            gameFrame.setDiceButtonImage(imagePath);

            SwingUtilities.invokeLater(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                bot.setCurrentPawn(bot.choosePawnToMove(diceRollValue));
                thisTurn(diceRollValue);
            });

        }
    }

    /**
     * Przechodzimy do kolejnego gracza (pomijając tych, którzy skończyli).
     * Jeżeli wszyscy już skończyli, wywołujemy showFinalRankingAndEnd().
     */
    public void nextTurn() {
        // Odebranie tury obecnemu graczowi
        int currentPlayerIndex = players.indexOf(currentPlayer);
        currentPlayer.NotGiveTurn();

        // Szukamy kolejnego, który nie jest finished
        int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();

        // Pętlą omijamy graczy, którzy skończyli
        while (players.get(nextPlayerIndex).isFinished()) {
            // Jeśli wszyscy - kończymy
            if (allPlayersFinished()) {
                showFinalRankingAndEnd();
                return;
            }
            nextPlayerIndex = (nextPlayerIndex + 1) % players.size();
        }

        currentPlayer = players.get(nextPlayerIndex);
        currentPlayer.GiveTurn();

        // Dla pewności wyłączamy klikalność u wszystkich,
        // a włączamy tylko u nowego currentPlayer
        for (Player p : players) {
            for (Pawn pawn : p.getPlayerPawns()) {
                pawn.getPawnComponent().makeClickable(false);
            }
        }
        for (Pawn pawn : currentPlayer.getPlayerPawns()) {
            pawn.getPawnComponent().makeClickable(true);
        }

        gameFrame.diceclickability(true);

        // Jeśli w tym momencie (teoretycznie) wszyscy skończyli, wyświetlamy ranking
        if (allPlayersFinished()) {
            showFinalRankingAndEnd();
        }

        if(currentPlayer.getUserType().equals("Bot")) {
            handleBotTurn();
        }
    }

    /**
     * Metoda odpalana z GameFrame przy kliknięciu w kostkę:
     * - Sprawdza, czy można wyjść z bazy,
     * - Przesuwa pionek,
     * - Sprawdza, czy gracz nie skończył,
     * - Przekazuje turę dalej.
     */
    public void thisTurn(int diceRollValue) {
        // Jeśli aktualny gracz już jest finished, to nic nie robi i przechodzi kolejka:
        if (currentPlayer.isFinished()) {
            nextTurn();
            return;
        }

        Pawn currentPawn = currentPlayer.getCurrentPawn();
        if (currentPawn == null) {
            // Jeżeli żaden pionek nie został kliknięty przez gracza,
            // to można (jeśli tak przewidujemy) zignorować albo wymusić wybór.
            // Można np. wstawić informację:
            JOptionPane.showMessageDialog(null,
                    "Nie wybrano pionka do przesunięcia!",
                    "Brak pionka",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 1. Wyjście z bazy, jeśli wypadło 6 i pionek jest w bazie
        if (shouldMovePawnFromBase(diceRollValue)) {
            Point rotatedStartPosition = new Point(
                    currentPawn.getStartPosition().y,
                    12 - currentPawn.getStartPosition().x
            );
            //Sprawdzam czy na tej pozycji jest jakiśpionek
            for (Player player : players) {
                for (Pawn pawn : player.getPawnsInGame()) {
                    if (pawn.getCurrentPosition().equals(rotatedStartPosition) && !pawn.getOwner().equals(currentPawn.getOwner())) {
                        player.movePawnToBase(pawn);
                        gameFrame.movePawn(pawn, pawn.getHomePosition());
                        System.out.println("Pionek gracza " + player.getUsername() + " wrócił do bazy");
                        break;
                    }
                }
            }
            gameFrame.movePawn(currentPawn, rotatedStartPosition);
            currentPawn.setCurrentPosition(rotatedStartPosition);
            currentPlayer.movePawnToGame(currentPawn);

            // 2. Normalne przesunięcie (jeśli pionek jest już na planszy)
        } else if (shouldPawnMove()) {
            int totalSteps = diceRollValue;

            // Jeżeli to pierwszy ruch na planszy
            if (currentPawn.isFirstMove()) {
                currentPawn.setFirstMove(false);
            }
            currentPawn.increaseWalkTableIndex(totalSteps);

            Point newPosition = currentPawn.getWalkTable().get(currentPawn.getWalkTableIndex());

            //Sprawdzam czy na tej pozycji jest jakiśpionek
            for (Player player : players) {
                for (Pawn pawn : player.getPawnsInGame()) {
                    if (pawn.getCurrentPosition().equals(newPosition) && !pawn.getOwner().equals(currentPawn.getOwner())) {
                        player.movePawnToBase(pawn);
                        gameFrame.movePawn(pawn, pawn.getHomePosition());
                        System.out.println("Pionek gracza " + player.getUsername() + " wrócił do bazy");
                        break;
                    }
                }
            }

            // Ustawiamy pionek na nowe pole
            gameFrame.movePawn(currentPawn, newPosition);
            currentPawn.setCurrentPosition(newPosition);

            // Sprawdzamy, czy wszedł na pole (6,6) => meta
            if (currentPawn.getCurrentPosition().equals(new Point(6,6))) {
                currentPlayer.movePawnToHome(currentPawn);
                gameFrame.removePawn(currentPawn);         // Usunięcie pionka z planszy
                gameFrame.updatePlayerPawnsCount(currentPlayer);

                // Czy gracz skończył?
                if (currentPlayer.getPawnsRemaining() == 0) {
                    currentPlayer.setFinished(true);

                    // Dodajemy go do finishingOrder (jeżeli nie jest już tam)
                    if (!finishingOrder.contains(currentPlayer)) {
                        finishingOrder.add(currentPlayer);
                    }

                    // Komunikat, kto zajął które miejsce:
                    String message = "Gracz " + currentPlayer.getUsername()
                            + " zajął miejsce " + finishingOrder.size() + "!";
                    JOptionPane.showMessageDialog(null, message);
                }
            }
        }

        // Przekazujemy kolejkę dalej
        nextTurn();

        // Odświeżamy wygląd kostki, panelu itp.
        gameFrame.updateDicePanelColor(currentPlayer);
    }

    /**
     * Sprawdza, czy pionek może wyjść z bazy
     * (kostka = 6, i jest pionek w bazie).
     */
    private boolean shouldMovePawnFromBase(int diceRollValue) {
        return diceRollValue == REQUIRED_ROLL_FOR_NEW_PAWN
                && !currentPlayer.getPawnsAtBase().isEmpty()
                && currentPlayer.getCurrentPawn() != null
                && currentPlayer.getCurrentPawn().isAtHome();
    }

    /**
     * Sprawdza, czy pionek jest na planszy i może się ruszyć.
     */
    private boolean shouldPawnMove() {
        return !currentPlayer.getPawnsInGame().isEmpty()
                && currentPlayer.getCurrentPawn() != null
                && !currentPlayer.getCurrentPawn().isAtHome();
    }

    /**
     * Rzucenie kostką (zwykle wykonywane w GameFrame).
     */
    public int rollDice() {
        return dice.roll();
    }

    /**
     * Metoda potrzebna przy wczytywaniu zapisu, by odtworzyć listę graczy (User).
     */
    public ArrayList<User> getUsers() {
        return new ArrayList<>(players);
    }

    // Gettery, jeśli potrzebne
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
}
