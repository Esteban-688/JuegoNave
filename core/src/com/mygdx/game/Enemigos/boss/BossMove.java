package com.mygdx.game.Enemigos.boss;

import com.badlogic.gdx.graphics.g2d.Sprite;


public class BossMove {
	
	    private Sprite sprite;
	    private boolean movingUp;
	    private float speed;
	    private float upperLimit;
	    private float lowerLimit;
	    private float currentY;

	    public BossMove(Sprite sprite, float speed, float upperLimit, float lowerLimit) {
	        this.sprite = sprite;
	        this.speed = speed;
	        this.upperLimit = upperLimit;
	        this.lowerLimit = lowerLimit;
	        this.currentY = sprite.getY();
	        this.movingUp = true;
	    }

	    public void move() {
	        if (currentY >= upperLimit) {
	            movingUp = true;
	        } else if (currentY <= lowerLimit) {
	            movingUp = false;
	        }

	        if (movingUp) {
	            currentY -= speed;
	        } else {
	            currentY += speed;
	        }

	        sprite.setPosition(sprite.getX(), currentY);
	    }

	    public Sprite getSprite() {
	        return sprite;
	    }

	    public boolean isMovingUp() {
	        return movingUp;
	    }

	    public float getSpeed() {
	        return speed;
	    }

	    public float getUpperLimit() {
	        return upperLimit;
	    }

	    public float getLowerLimit() {
	        return lowerLimit;
	    }

	    public float getCurrentY() {
	        return currentY;
	    }

	    public void setSprite(Sprite sprite) {
	        this.sprite = sprite;
	    }

	    public void setMovingUp(boolean movingUp) {
	        this.movingUp = movingUp;
	    }

	    public void setSpeed(float speed) {
	        this.speed = speed;
	    }

	    public void setUpperLimit(float upperLimit) {
	        this.upperLimit = upperLimit;
	    }

	    public void setLowerLimit(float lowerLimit) {
	        this.lowerLimit = lowerLimit;
	    }

	    public void setCurrentY(float currentY) {
	        this.currentY = currentY;
	    }
	}

