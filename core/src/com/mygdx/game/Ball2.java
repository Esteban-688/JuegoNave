package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Ball2 {
	private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private Sprite spr;
    private int heart = 10;

    public Ball2(int x, int y, int size, int xSpeed, int ySpeed, Texture tx, int bordeX, int bordeY, int t) {
    	spr = new Sprite(tx);
    	this.x = x; 
    	/*
        //validar que borde de esfera no quede fuera
    	if (x-size < 0) this.x = x+size;
    	if (x+size > Gdx.graphics.getWidth())this.x = x-size;
         
        this.y = y;
        //validar que borde de esfera no quede fuera
    	if (y-size < 0) this.y = y+size;
    	if (y+size > Gdx.graphics.getHeight())this.y = y-size;
    	
        spr.setPosition(x, y);
        */
    	bordeBall(x,y,size, bordeX,bordeY);
       this.setXSpeed(xSpeed);
       this.setySpeed(ySpeed);
        
    }
    /**
     *  se encarga de actualizar la posición del objeto
     *  en función de su velocidad, y también se asegura
     *  de que el objeto rebote dentro de los límites de
     *  la pantalla en caso de que esté a punto de salir por alguno de los bordes.
     * 
     */
    /*public void update() {
        x += getXSpeed();
        y += getySpeed();
        
        if (x+getXSpeed() < 0 || x+getXSpeed()+spr.getWidth() > Gdx.graphics.getWidth())
        	setXSpeed(getXSpeed() * -1);
        if (y+getySpeed() < 0 || y+getySpeed()+spr.getHeight() > Gdx.graphics.getHeight())
        	setySpeed(getySpeed() * -1);
        spr.setPosition(x, y);
    }*/
    public void update(int anchoMapa, int altoMapa) {
        x += getXSpeed();
        y += getySpeed();
        
        if (x < -100 || x + spr.getWidth() > (anchoMapa+100))
            setXSpeed(getXSpeed() * -1);
        if (y < -100 || y + spr.getHeight() > (altoMapa-100))
            setySpeed(getySpeed() * -1);
        
        spr.setPosition(x, y);
    }

    public Rectangle getArea() {
    	return spr.getBoundingRectangle();
    }
    
    
    public void draw(SpriteBatch batch) {
    	spr.draw(batch);
    }
    
    /*
    public void checkCollision(Ball2 b2) {
        if(spr.getBoundingRectangle().overlaps(b2.spr.getBoundingRectangle())){
        	// rebote
            if (getXSpeed() ==0) setXSpeed(getXSpeed() + b2.getXSpeed()/2);
            if (b2.getXSpeed() ==0) b2.setXSpeed(b2.getXSpeed() + getXSpeed()/2);
        	setXSpeed(- getXSpeed());
            b2.setXSpeed(-b2.getXSpeed());
            
            if (getySpeed() ==0) setySpeed(getySpeed() + b2.getySpeed()/2);
            if (b2.getySpeed() ==0) b2.setySpeed(b2.getySpeed() + getySpeed()/2);
            setySpeed(- getySpeed());
            b2.setySpeed(- b2.getySpeed()); 
        }
    }
    */
    
     public void checkCollision(Ball2 b2) {
	    if (spr.getBoundingRectangle().overlaps(b2.spr.getBoundingRectangle())) {
	        // Rebote y disminucion de heart
	        if (getXSpeed() == 0) {
	            setXSpeed(getXSpeed() + b2.getXSpeed() / 2);
	        }
	        if (b2.getXSpeed() == 0) {
	            b2.setXSpeed(b2.getXSpeed() + getXSpeed() / 2);
	        }
	        setXSpeed(-getXSpeed());
	        b2.setXSpeed(-b2.getXSpeed());
	
	        if (getySpeed() == 0) {
	            setySpeed(getySpeed() + b2.getySpeed() / 2);
	        }
	        if (b2.getySpeed() == 0) {
	            b2.setySpeed(b2.getySpeed() + getySpeed() / 2);
	        }
	        setySpeed(-getySpeed());
	        b2.setySpeed(-b2.getySpeed());
	
	        // Disminuir heart
	        heart--;
	        b2.setHeart();
	    }
     }
     private void bordeBall(int x, int y, int size, int xBorde, int yBorde) {
    	//validar que borde de esfera no quede fuera
     	if (x-size < 0) this.x = x+size;
     	if (x+size > xBorde)this.x = x-size;
          
         this.y = y;
         //validar que borde de esfera no quede fuera
     	if (y-size < 0) this.y = y+size;
     	if (y+size > yBorde)this.y = y-size;
     	
         spr.setPosition(x, y);
    	 
     }
	public int getXSpeed() {
		return xSpeed;
	}
	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	public int getySpeed() {
		return ySpeed;
	}
	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	public int getHeart() {
	    return heart;
	}
	public void setHeart() {
		heart --;
	}
	public boolean isDestroyed() {
	   if (heart == 0)return true;
	    return false;
	}
    
}
