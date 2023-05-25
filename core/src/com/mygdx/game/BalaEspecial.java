package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Intersector;

public class BalaEspecial extends Bullet {
    
    private int vidaBala = 3;
    private float sizeBala = 150;
    private Circle circle;
    private Sprite sprite;
    private float tiempoDeBala = 0.0f;

    public BalaEspecial(float x, float y, Texture tx) {
        circle = new Circle(x, y, sizeBala / 2);
        sprite = new Sprite(tx);
        sprite.setSize(sizeBala, sizeBala);
        }

    public void draw(SpriteBatch batch) {
    	sprite.setPosition(circle.x - circle.radius, circle.y - circle.radius);
        sprite.draw(batch);
        
    }

    public boolean checkCollision(Ball2 b2) {
        Circle circle = getCircle();
        Rectangle rectangle = b2.getArea();
        
        if (Intersector.overlaps(circle, rectangle)) {
            if (vidaBala <= 0) {
                destroyed = true;
                return true;
            }
            vidaBala--;
        }
        return false;
    }

    public void update(int posicionXCamara, int posicionYCamara, int anchoCamara, int altoCamara, int x, int y) {
        circle.setPosition(x, y);
        sprite.setPosition(x, y);
        //sprite.rotate(45f+1);
        tiempoDeBala += Gdx.graphics.getDeltaTime();
        //System.out.println(Gdx.graphics.getDeltaTime());
        //System.out.println("tiempoDebala"+tiempoDeBala);
        if(tiempoDeBala >= 5) {
        	destroyed = true;
        }
        
        
    }

    public Circle getCircle() {
        return circle;
    }

}
