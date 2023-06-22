package com.mygdx.game.diccionaInterfaces;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.PantallaJuego;

public interface Atacar {
	
	void atacar(SpriteBatch batch, PantallaJuego juego, Sprite spr);
	
}
