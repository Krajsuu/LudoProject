package components;

import javax.swing.*;
import java.awt.*;
import model.User;

public class UserInfo extends JPanel {
    private JLabel playerNickname;
    private Color playerColor;
    private JLabel playerIcon;

    private final Font nicknameFont = new Font("Arial", Font.BOLD, 20);
    private final int iconSize = 50;

    public UserInfo(User user) {
        playerNickname = new JLabel(user.getUsername());
        playerColor = user.getColor();
        playerIcon = new JLabel();

        // Wybieramy ikonę zależnie od tego, czy to Bot, czy normalny gracz
        String iconPath = selectPlayerIcon(user.getColor(), user.getUserType());

        ImageIcon icon = new ImageIcon(iconPath);
        ImageIcon scaledIcon = new ImageIcon(
                icon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));

        playerIcon.setIcon(scaledIcon);
        playerIcon.setBackground(playerColor);
        playerIcon.setOpaque(true);
        playerIcon.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        playerIcon.setPreferredSize(new Dimension(iconSize, iconSize));

        playerNickname.setFont(nicknameFont);
        playerNickname.setForeground(Color.BLACK);

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(playerIcon);
        this.add(Box.createHorizontalStrut(10));
        this.add(playerNickname);
    }

    /**
     * Metoda sprawdzająca, czy user jest Botem czy nie,
     * i dobierająca odpowiednią ścieżkę do ikony na podstawie koloru.
     */
    private String selectPlayerIcon(Color color, String userType) {
        // Sprawdzamy, czy user to Bot (userType = "Bot")
        boolean isBot = userType.equals("Bot");

        if (isBot) {
            // Ikony z folderu botImages
            if (color == Color.RED) {
                return "data/images/botImages/botRed.png";
            } else if (color == Color.BLUE) {
                return "data/images/botImages/botBlue.png";
            } else if (color == Color.GREEN) {
                return "data/images/botImages/botGreen.png";
            } else if (color == Color.YELLOW) {
                return "data/images/botImages/botYellow.png";
            } else {
                // Domyślna ikona bota
                return "data/images/botImages/botBlue.png";
            }
        } else {
            // Normalny gracz, ikony z playerImages
            if (color == Color.RED) {
                return "data/images/playerImages/playerRed.png";
            } else if (color == Color.BLUE) {
                return "data/images/playerImages/playerBlue.png";
            } else if (color == Color.GREEN) {
                return "data/images/playerImages/playerGreen.png";
            } else if (color == Color.YELLOW) {
                return "data/images/playerImages/playerYellow.png";
            } else {
                // Domyślna ikona gracza
                return "data/images/playerImages/playerBlue.png";
            }
        }
    }

    public void setPlayerNickname(String nickname) {
        playerNickname.setText(nickname);
    }

    public void setPlayerColor(Color color) {
        playerColor = color;
        playerIcon.setBackground(playerColor);
    }

    public void setPlayerIcon(String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        ImageIcon scaledIcon = new ImageIcon(
                icon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH)
        );
        playerIcon.setIcon(scaledIcon);
    }

    public String getPlayerNickname() {
        return playerNickname.getText();
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public JLabel getPlayerIcon() {
        return playerIcon;
    }
}
