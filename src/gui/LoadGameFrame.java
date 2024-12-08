package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


/**
 * Klasa LoadGameFrame odpowiada za wyświetlenie okna z listą zapisanych gier.
 * Umożliwia załadowanie jednej z nich.
 */
public class LoadGameFrame {
    // Deklaracja zmiennych
    private JPanel LoadGamePanel; // Panel główny
    private JButton LoadButton; // Przycisk do załadowania gry
    private JButton BackButton; // Przycisk powrotu
    private JList ListOfGames; // Lista zapisanych gier
    private JLabel TopLabel; // Nagłówek

    private JFrame parentFrame; // Okno nadrzędne

    /**
     * Konstruktor klasy LoadGameFrame
     *
     *
     * @param parentFrame Okno nadrzędne
     */
    public LoadGameFrame(JFrame parentFrame){
        this.parentFrame = parentFrame;  // Przypisanie okna nadrzędnego


        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(((MenuFrame) parentFrame).getOriginalPanel());
                parentFrame.revalidate();
                parentFrame.repaint();

            }
        });

    }

    public JPanel getMainPanel(){
        return LoadGamePanel;
    }
}
