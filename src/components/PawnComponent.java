package components;

import javax.swing.*;
import model.Pawn;
public class PawnComponent extends JButton {
    private Pawn pawn;
    private boolean selected;

    public PawnComponent(Pawn pawn) {
        this.pawn = pawn;
        setIcon(pawn.getPawnIcon());
        setBorderPainted(false);
        setDisabledIcon(pawn.getPawnIcon());
        setContentAreaFilled(false);
        makeClickable(false);
        addActionListener(e -> {
            selected = !selected;
            pawn.getOwner().setCurrentPawn(pawn);
            System.out.println("PawnComponent clicked");
        });

    }

    public void makeClickable(boolean clickable) {
        setEnabled(clickable);
    }

    public Pawn getPawn() {
        return pawn;
    }

    public boolean isSelected() {
        return selected;
    }


}
