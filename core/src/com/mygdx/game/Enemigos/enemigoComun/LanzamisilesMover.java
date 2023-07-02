package com.mygdx.game.Enemigos.enemigoComun;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Config;
import com.mygdx.game.diccionaInterfaces.Moverse;

public class LanzamisilesMover implements Moverse {
	private boolean moviendoArriba;
	private boolean moviendoAdelante;
	
	private float yEnemigo;
	private float xEnemigo;
	private float ySpeed, xSpeed;

	public LanzamisilesMover() {
		ySpeed = 5;
		xSpeed = 10;
		yEnemigo = 0;
		xEnemigo = 0;
		moviendoArriba = true;
		moviendoAdelante = true;

	}

	// Método propio de Lanzamisiles para moverse
	public void moverse(Sprite spr) {

		// Arriba y abajo
		if (spr.getY() >= Config.getUp() - 115) {
			yEnemigo = spr.getY() - ySpeed;
			moviendoArriba = true;

		} else if (spr.getY() <= Config.getDown() - 40) {
			yEnemigo = spr.getY() + ySpeed;
			moviendoArriba = false;
		} else if (moviendoArriba) {
			yEnemigo = spr.getY() - ySpeed;
		} else if (!moviendoArriba) {
			yEnemigo = spr.getY() + ySpeed;
		}

		// Adelante y atrás
		if (spr.getX() >= Config.naveGetX() + 301) {
			xEnemigo = spr.getX() - xSpeed;
			moviendoAdelante = true;
		} else if (spr.getX() <= Config.naveGetX() + 10) {
			xEnemigo = spr.getX() + xSpeed;
			moviendoAdelante = false;
		} else if (moviendoAdelante) {
			xEnemigo = spr.getX() - xSpeed;
		} else if (!moviendoAdelante) {
			xEnemigo = spr.getX() + xSpeed;
		}

		spr.setPosition(xEnemigo, yEnemigo);
	}
}