package com.mygdx.game.Enemigos.enemigoComun;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Config;
import com.mygdx.game.diccionaInterfaces.Moverse;

public class ArtilleroMover implements Moverse {
	private boolean moviendoArriba;

	private float ySpeed;

	public ArtilleroMover() {
		ySpeed = 5;
		moviendoArriba = true;
	}

	// Método propio de Artillero para moverse
	public void moverse(Sprite spr) {
		float yEnemigo = 0;

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

		spr.setPosition(Config.naveGetX() - 460, yEnemigo);
	}
}