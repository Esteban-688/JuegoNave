package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BalaNormal extends Bullet {
	
	private float xSpeed;
    private float ySpeed;
    private Sprite spr;
    
	public BalaNormal(float x, float y, float xSpeed, float ySpeed, Texture tx) {
		//super(x,y,tx);
		spr = new Sprite(tx);
        spr.setPosition(x, y);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	
	public void draw(SpriteBatch batch) {
        spr.draw(batch);
    }
	
	public boolean checkCollision(Ball2 b2) {
        if (spr.getBoundingRectangle().overlaps(b2.getArea())) {
            destroyed = true;
            return true;
        }
        return false;
    }
	
	public void update(int posicionXCamara, int posicionYCamara, int anchoCamara, int altoCamara, int x, int y) {
		spr.setPosition(spr.getX() + xSpeed, spr.getY() + ySpeed);

        if (spr.getX() < ((anchoCamara / 2) - posicionXCamara) || // izquierda
            spr.getX() > ((anchoCamara / 2) + posicionXCamara) || // derecha
            spr.getY() > ((altoCamara / 2) + posicionYCamara) ||  // arriba
            spr.getY() < ((altoCamara / 2) - posicionYCamara)) {   // abajo
            destroyed = true;
        }
	}
	public void setVelocity(float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
	
	public Sprite getSprite() {
        return spr;
    }
}
