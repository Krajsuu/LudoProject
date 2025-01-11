package components;

import javax.swing.*;
import java.awt.*;
import model.User;


public class UserInfo extends JPanel{
    private JLabel playerNickname;
    private Color playerColor;
    private JLabel playerIcon;


    private final Font nicknameFont = new Font("Arial", Font.BOLD, 20);
    private final int iconSize = 50;
    public UserInfo(User user){
        playerNickname = new JLabel(user.getUsername());
        playerColor = user.getColor();
        playerIcon = new JLabel();
        ImageIcon icon = new ImageIcon("data/images/playerImages/playerBlue.png");
        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
        playerIcon.setIcon(scaledIcon);
        playerIcon.setBackground(playerColor);
        playerIcon.setOpaque(true);
        playerIcon.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        playerIcon.setPreferredSize(new Dimension(iconSize, iconSize));
        playerIcon.setMaximumSize(new Dimension(iconSize, iconSize));
        playerIcon.setMinimumSize(new Dimension(iconSize, iconSize));
        playerIcon.setSize(30, 30);
        playerIcon.setVisible(true);
        playerNickname.setFont(nicknameFont);
        playerNickname.setForeground(Color.BLACK);
        playerNickname.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(playerIcon);
        this.add(playerNickname);
        this.setVisible(true);
    }


    public void setPlayerNickname(String nickname){
        playerNickname.setText(nickname);
    }
    public void setPlayerColor(Color color){
        playerColor = color;
        playerIcon.setBackground(playerColor);
    }
    public void setPlayerIcon(String iconPath){
        ImageIcon icon = new ImageIcon(iconPath);
        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        playerIcon.setIcon(scaledIcon);
    }
    public String getPlayerNickname(){
        return playerNickname.getText();
    }
    public Color getPlayerColor(){
        return playerColor;
    }
    public JLabel getPlayerIcon(){
        return playerIcon;
    }





}
