package com.mygdx.game.balas;



import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Ball2;

public abstract class Bullet {
	
    
    protected boolean destroyed;
    

    public Bullet() {
        
        destroyed = false;
    }
    public abstract void draw(SpriteBatch batch);
    
    public abstract void update(int posicionXCamara, int posicionYCamara, int anchoCamara, int altoCamara,int x, int y);

    public abstract boolean checkCollision(Ball2 b2);
    
    public abstract Sprite getSprite();
    
    
    public boolean isDestroyed() {
        return destroyed;
    }

    
    
}
