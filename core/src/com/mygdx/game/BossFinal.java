package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BossFinal implements Enemigo {
	private int vida = 100000;
	private Sprite spr;
	private Nave4 nave;
	private Texture txBalaNormal;
    private Sound soundBala;
    private float rotacion = 90;
    private float tiempo = 0.0f;
    private boolean moviendoArriba = true;
    private float distancia;
    private float ySpeed;
    private float yEnemigo;
    private int alto;
    private int ancho;
    
	public BossFinal(int x, int y, int xDistancia, float ySpeed, Texture tx, Nave4 nave1, Texture txBala, Sound soundBala, int altoY, int anchoX) {
		
		spr = new Sprite(tx);
		spr.setPosition(x, y);
		nave = nave1;
		
		alto= altoY;
        ancho = anchoX;
        
        distancia = xDistancia;
        this.ySpeed = ySpeed;
        
        this.soundBala = soundBala;
        txBalaNormal = txBala;
        spr.setRotation(rotacion);
	}
	
	
	 public void draw(SpriteBatch batch) {
	    	// Dibujar el sprite del enemigo
	        spr.draw(batch);
	    }
	
	 
	public void atacar(SpriteBatch batch, PantallaJuego juego) {
		
	}
	public void moverse() {
		
	}
	
	public int getX() {return (int) spr.getX();}
    public int getY() {return (int) spr.getY();}
}
