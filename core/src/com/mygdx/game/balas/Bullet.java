package com.mygdx.game.balas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Enemigos.boss.BossFinal;
import com.mygdx.game.Enemigos.enemigoComun.EnemyComun;
import com.mygdx.game.navecita.Nave4;

public abstract class Bullet {
	private Sprite spr;

	private boolean destroyed;
	private boolean mia;

	private int daño;

	public Bullet(int dañoAlObjeto, boolean esMia, Texture tx) {
		spr = new Sprite(tx);
		daño = dañoAlObjeto;
		destroyed = false;
		mia = esMia;
	}

	public abstract void draw(SpriteBatch batch);

	public abstract void update(int posicionXCamara, int posicionYCamara, int anchoCamara, int altoCamara, int x,
			int y);

	public abstract boolean checkCollision(BossFinal boss);

	public abstract boolean checkCollision(EnemyComun enemigo);

	public abstract boolean checkCollision(Nave4 nave);

	public void setDestroyed(boolean a) {
		destroyed = a;
	}

	public int getDaño() {
		return daño;
	}

	public Sprite getSprite() {
		return spr;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public boolean getMia() {
		return mia;
	}
}