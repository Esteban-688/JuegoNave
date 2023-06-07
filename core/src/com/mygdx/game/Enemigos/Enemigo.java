package com.mygdx.game.Enemigos;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.PantallaJuego;

public interface Enemigo {

void atacar(SpriteBatch batch, PantallaJuego juego);
void moverse();

}
