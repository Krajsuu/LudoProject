package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import model.Game;
import model.Logs;
public class LoadGameFrame {
    private JPanel LoadGamePanel;
    private JButton LoadButton;
    private JButton BackButton;
    private JList<String> ListOfGames;
    private JLabel TopLabel;
    private JFrame parentFrame;

    public LoadGameFrame(JFrame parentFrame) {
        Logs.writeLog("LoadGameFrame started");
        this.parentFrame = parentFrame;

        updateGameList();

        BackButton.addActionListener(e -> {
            Logs.writeLog("Back to menu from LoadGameFrame");
            parentFrame.setContentPane(((MenuFrame) parentFrame).getOriginalPanel());
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        LoadButton.addActionListener(e -> loadSelectedGame());

    }

    private void updateGameList() {
        Logs.writeLog("Updating game list");
        File saveDirectory = new File("saves");
        if (!saveDirectory.exists() || !saveDirectory.isDirectory()) {
            saveDirectory.mkdir();
        }

        String[] saves = saveDirectory.list((dir, name) -> name.endsWith(".ser"));
        if (saves != null) {
            ListOfGames.setListData(saves);
        } else {
            ListOfGames.setListData(new String[]{});
        }
    }

    private void loadSelectedGame() {
        String selectedSave = ListOfGames.getSelectedValue();
        if (selectedSave == null) {
            JOptionPane.showMessageDialog(null, "Wybierz zapis gry, aby go wczytać.", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("saves/" + selectedSave))) {
            Game loadedGame = (Game) ois.readObject();
            GameFrame gameFrame = new GameFrame(parentFrame, loadedGame.getUsers());
            parentFrame.setContentPane(gameFrame.getMainPanel());
            parentFrame.revalidate();
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Błąd wczytywania gry: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getMainPanel() {
        return LoadGamePanel;
    }
}

























