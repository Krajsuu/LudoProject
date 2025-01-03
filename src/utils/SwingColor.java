package utils;

import java.awt.Color;

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

    public Color getColor() {
        return color;
    }

}
