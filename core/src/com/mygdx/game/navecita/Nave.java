package com.mygdx.game.navecita;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.diccionaInterfaces.Atacar;
import com.mygdx.game.diccionaInterfaces.Moverse;

public interface Nave extends Atacar, Moverse{
	
	void atacar(SpriteBatch batch, PantallaJuego juego);
	void moverse();
}
