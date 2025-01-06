package model;

import java.util.Random;

public class Dice {
    private int randomValue;
    private String ImagePath = "data/images/diceImages/";

    public Dice() {
        this.randomValue = 0;
    }

    public int roll() {
        Random random = new Random();
        randomValue = random.nextInt(6) + 1; // +1 bo nextInt zwraca wartości od 0 do 5
        return randomValue;
    }

    public int getValue() {
        return randomValue;
    }

    public String getImagePath() {
        return ImagePath + randomValue + ".png";
    }

}
