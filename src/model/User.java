package model;

import java.awt.Color;

public abstract class User{
    protected String username; // nick
    protected Color color; // kolor gracza
    public User(String username, Color color){
        this.username = username;
        this.color = color;
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