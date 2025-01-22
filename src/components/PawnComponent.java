package components;

import javax.swing.*;
import model.Pawn;
public class PawnComponent extends JButton {
    private Pawn pawn;

    public PawnComponent(Pawn pawn) {
        this.pawn = pawn;
        setIcon(pawn.getPawnIcon());
        setDisabledIcon(pawn.getPawnIcon());
        setBorderPainted(false);
        setContentAreaFilled(false);
        makeClickable(false);
        addActionListener(e -> {
            System.out.println("PawnComponent clicked");
        });

    }

    public void makeClickable(boolean clickable) {
        setEnabled(clickable);
    }

    public Pawn getPawn() {
        return pawn;
    }


}
