package main;

import javax.swing.*;

import gui.MenuFrame;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Uruchamianie MenuFrame
            MenuFrame menuFrame = new MenuFrame();
            menuFrame.setVisible(true);
        });
    }
}
