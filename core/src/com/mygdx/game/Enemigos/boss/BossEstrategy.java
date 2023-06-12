package com.mygdx.game.Enemigos.boss;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Nave4;
import com.mygdx.game.PantallaJuego;

public interface BossEstrategy {
	 void atacar(BossAttack bossAttack, Nave4 nave, float delta, PantallaJuego juego, SpriteBatch batch);
}
