package utils;

import java.awt.Color;
import java.util.Objects;

public enum SwingColor {
    RED(Color.RED),
    BLUE(Color.BLUE),
    GREEN(Color.GREEN),
    YELLOW(Color.YELLOW),
    ORANGE(Color.ORANGE),
    PINK(Color.PINK),
    CYAN(Color.CYAN),
    MAGENTA(Color.MAGENTA),
    GRAY(Color.GRAY),
    BLACK(Color.BLACK),
    WHITE(Color.WHITE);

    private final Color color;

    SwingColor(Color color) {
        this.color = color;
    }



    public static Color playerColor(int i){
        switch(i){
            case 0:
                return Color.red;
            case 1:
                return Color.blue;
            case 2:
                return Color.green;
            case 3:
                return Color.yellow;
            default:
                return Color.black;


        }
    }

}


