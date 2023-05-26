package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BossFinal implements Enemigo {
	private int vida = 1000;
	private Sprite spr;
	private Nave4 nave;
	
	public BossFinal(int x, int y, Texture tx, Nave4 nave1) {
		
		spr = new Sprite(tx);
		spr.setPosition(x, y);
		nave = nave1;
	}
	
	
	
	public void atacar(SpriteBatch batch, PantallaJuego juego) {
		
	}
	public void moverse() {
		
	}
	
	public int getX() {return (int) spr.getX();}
    public int getY() {return (int) spr.getY();}
}
