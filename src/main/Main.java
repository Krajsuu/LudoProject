package main;

import javax.swing.*;
import gui.MenuFrame;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Uruchamianie MenuFrame
            MenuFrame menuFrame = new MenuFrame();
            menuFrame.setVisible(true);
        });
    }
}
