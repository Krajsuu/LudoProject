package main;

import javax.swing.*;

import gui.MenuFrame;
import model.Logs;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        // Utworzenie logów
        Logs.createLog();
        // Zapisanie informacji o uruchomieniu programu
        Logs.writeLog("Program started");
        SwingUtilities.invokeLater(() -> {
            // Uruchamianie MenuFrame
            MenuFrame menuFrame = new MenuFrame();
            menuFrame.setVisible(true);
        });
    }

}
