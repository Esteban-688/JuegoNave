package com.mygdx.game.balas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Ball2;
import com.badlogic.gdx.math.Intersector;

public class BalaEspecial extends Bullet {
    
    private float sizeBala = 150;
    private Circle circle;
    private Sprite sprite;
    private float tiempoDeBala = 0.0f;
   
    public BalaEspecial(int daño, float x, float y, Texture tx, boolean balaMia) {
       super(daño, balaMia);
       
    	circle = new Circle(x, y, sizeBala / 2);
        sprite = new Sprite(tx);
        sprite.setSize(sizeBala, sizeBala);
        }

    public void draw(SpriteBatch batch) {
    	sprite.setPosition(circle.x - circle.radius, circle.y - circle.radius);
        sprite.draw(batch);
        
    }

    public void update(int posicionXCamara, int posicionYCamara, int anchoCamara, int altoCamara, int x, int y) {
        circle.setPosition(x, y);
        sprite.setPosition(x, y);
        tiempoDeBala += Gdx.graphics.getDeltaTime();
      
        if(tiempoDeBala >= 4) {
        	setDestroyed(true);
        }
    }

    public Circle getCircle() {
        return circle;
    }
    public Sprite getSprite() {
        return sprite;
    }

}
