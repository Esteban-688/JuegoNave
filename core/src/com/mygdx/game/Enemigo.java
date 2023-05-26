package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Enemigo {

void atacar(SpriteBatch batch, PantallaJuego juego);
void moverse();

}
