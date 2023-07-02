package com.mygdx.game.balas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.mygdx.game.Config;
import com.mygdx.game.Enemigos.boss.BossFinal;
import com.mygdx.game.Enemigos.enemigoComun.EnemyComun;
import com.mygdx.game.navecita.Nave4;

public class BalaEspecial extends Bullet {
	private Circle circle;
	
	private float sizeBala = 150;
	private float tiempoDeBala = 0.0f;

	public BalaEspecial(int daño, float x, float y, Texture tx, boolean balaMia) {
		super(daño, balaMia, tx);

		circle = new Circle(x, y, sizeBala / 2);
		getSprite().setSize(sizeBala, sizeBala);
	}

	public void draw(SpriteBatch batch) {
		getSprite().setPosition(circle.x - circle.radius, circle.y - circle.radius);
		getSprite().draw(batch);

	}

	public void update(int posicionXCamara, int posicionYCamara, int anchoCamara, int altoCamara, int x, int y) {
		circle.setPosition(x, y);
		getSprite().setPosition(x, y);
		tiempoDeBala += Gdx.graphics.getDeltaTime();

		if (tiempoDeBala >= 4) {
			setDestroyed(true);
		}
	}

	public boolean checkCollision(BossFinal boss) {
		if (getMia()) {
			if (boss.getSprite().getBoundingRectangle().overlaps(getSprite().getBoundingRectangle())) {
				boss.restarVida(getDaño());
				boss.setPosition(Config.getDer() - 300, Config.getUp() / 2);
				// Actualizar la vida en porcentaje
				float vidaPorcentaje = (boss.getVida() * 100f) / boss.getMaxVida();
				boss.setVidaPorcentaje(vidaPorcentaje);
				setDestroyed(true);
				if (boss.getVida() <= 0) {
					boss.setDestruida(true);
					return false;
				} else {
					boss.texturaHerida();
				}
				return true;
			}
		}

		return false;
	}

	public boolean checkCollision(EnemyComun enemigo) {
		if (getMia()) {
			if (enemigo.getSpr().getBoundingRectangle().overlaps(getSprite().getBoundingRectangle())) {
				enemigo.restarVida(getDaño());
				setDestroyed(true);
				enemigo.setDestruida(true);
				return true;
			}

		}
		return false;
	}

	public boolean checkCollision(Nave4 nave) {
		if (getMia()) {
			if (!nave.estaHerido()
					&& nave.getSpr().getBoundingRectangle().overlaps(getSprite().getBoundingRectangle())) {
				nave.SumarVida(1);
				return false;
			}
		}
		return true;
	}

	public Circle getCircle() {
		return circle;
	}

}
