package com.example.footballcommunityapp.Database;

import android.widget.ImageView;

import java.io.Serializable;

public class Player implements Serializable {
    // Contains the following member variables.
    private int id;
    private float positionX;
    private float positionY;
    private ImageView playerImage;

    public Player() {
        super();
        // TODO Auto-generated constructor stub
    }

    // Constructor
    public Player(float positionX, float positionY, ImageView playerImage) {
        super();
        this.positionX = positionX;
        this.positionY = positionY;
        this.playerImage = playerImage;
    }

    // Implement get and set methods
    public float getPositionX() {
        return positionX;
    }
    public void setPositionX(float positionX) {
        this.positionX = positionX;
        this.playerImage.setX(positionX);
    }
    public float getPositionY() {
        return positionY;
    }
    public void setPositionY(float positionY) {
        this.positionY = positionY;
        this.playerImage.setY(positionY);
    }
    public ImageView getPlayerImage() {
        return playerImage;
    }
    public void setPlayerImage(ImageView playerImage) {
        this.playerImage = playerImage;
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", positionX=" + positionX + ", positionY=" + positionY + "]";
    }
}