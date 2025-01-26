// User.java
package model;

import java.awt.Color;
import java.io.Serializable;

public abstract class User implements Serializable { // Dodanie Serializable
    private static final long serialVersionUID = 1L;

    protected String username; // nick
    protected Color color; // kolor gracza

    // Konstruktor z argumentami
    public User(String username, Color color){
        this.username = username;
        this.color = color;
    }

    // Konstruktor bezargumentowy
    protected User() {
        this.username = "";
        this.color = Color.BLACK;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public abstract String getUserType();
}
