package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class BossFinal implements Enemigo {
	
	private boolean destruida = false;
	private int vida = 15000;
	private Sprite spr;
	private Nave4 nave;
	private Texture txBalaNormal;
    private Sound soundBala;
    private float rotacion = 90;
    private float tiempo = 0.0f;
    private boolean moviendoArriba = true;
    private float xSpeed;
    private float ySpeed;
    private float yEnemigo;
    private int yUp;
    private int yDown;
    private int xDer;
    
	public BossFinal(int x, int y, int xSpeed, float ySpeed, Texture tx, Nave4 nave1, Texture txBala, Sound soundBala) {
		
		spr = new Sprite(tx);
		spr.setPosition(x, y);
		nave = nave1;
		
		
        
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        
        this.soundBala = soundBala;
        txBalaNormal = txBala;
        spr.setSize(150,150);
        spr.setRotation(rotacion);
	}
	
	
	 public void draw(SpriteBatch batch) {
	    	// Dibujar el sprite del enemigo
	        spr.draw(batch);
	    }
	
	 
	public void atacar(SpriteBatch batch, PantallaJuego juego) {
		tiempo += Gdx.graphics.getDeltaTime();
        moverse();
        if (tiempo >= 1) {
            tiempo = 0.0f;
            float balaVelocidad = 10; // Velocidad de la bala
            float balaDireccionX = -MathUtils.sinDeg(rotacion); // Componente X de la dirección de la bala (invertido)
            float balaDireccionY = MathUtils.cosDeg(rotacion); // Componente Y de la dirección de la bala 
            
            float balaInicialX = spr.getX();
            float balaInicialY = spr.getY();
            
            BalaNormal bala = new BalaNormal(
                    balaInicialX,
                    balaInicialY,
                    balaDireccionX * balaVelocidad,
                    balaDireccionY * balaVelocidad,
                    txBalaNormal,
                    false
                );
            
            bala.setVelocity(balaDireccionX * balaVelocidad, balaDireccionY * balaVelocidad);

            // Girar la textura de la bala según la rotación de la nave
            bala.getSprite().setRotation(rotacion);

           // juego.agregarBala(bala);
            //soundBala.play();
            
            bala.up(Gdx.graphics.getDeltaTime());
            
            juego.agregarBala(bala);
            soundBala.play();
            soundBala.setVolume(1, 0.5f);
            bala.draw(batch);
        }
	}
	
	
	public void moverse() {
		if(spr.getY() >= yUp) {
    		yEnemigo = spr.getY() - ySpeed;
    		moviendoArriba = true;
    		
    	}
    	else if(spr.getY() <= yDown) {
    		yEnemigo = spr.getY() + ySpeed;
    		moviendoArriba = false;
    	}
    	else if(moviendoArriba) {
    		yEnemigo = spr.getY() - ySpeed;
    	}
    	else if(!moviendoArriba) {
    		yEnemigo = spr.getY() + ySpeed;
    	}
    	
    	spr.setPosition(xDer-100, yEnemigo);
		//spr.setPosition(nave.getX()+300, nave.getY());
	}
	public boolean checkCollision(Ball2 ball) {
        
        return true;
    }
    public boolean checkCollision(Bullet balaa) {
        
	if (balaa instanceof BalaNormal) {
    	
        BalaNormal bala = (BalaNormal) balaa;
        if(bala.getMia()) {
            if (bala.getSprite().getBoundingRectangle().overlaps(spr.getBoundingRectangle())) {
                vida-=50;
                if (vida <= 0) {
                    destruida = true;
                }
                return true;
            }
        }
        else if(balaa.getSprite().getBoundingRectangle().overlaps(spr.getBoundingRectangle())) {
        	vida-=500;
        	if (vida <= 0) {
                destruida = true;
            }
        	return true;
        }
    }
    return false;
    }
	public void setLimite(int yup, int ydown, int  xder) {
		 yUp = yup;
		 yDown = ydown;
		  xDer= xder;
	}
	public int getX() {return (int) spr.getX();}
    public int getY() {return (int) spr.getY();}
    public int getVida() {return vida;}
    public Sprite getSprite() {return spr;}
    public boolean isDestruida() {return destruida;}
}
