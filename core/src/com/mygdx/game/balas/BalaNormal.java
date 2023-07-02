package com.mygdx.game.balas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Enemigos.boss.BossFinal;
import com.mygdx.game.Enemigos.enemigoComun.EnemyComun;
import com.mygdx.game.navecita.Nave4;

public class BalaNormal extends Bullet {
	private float xSpeed, ySpeed;
	private float tiempoVida = 5;
	private float tiempoTranscurrido;

	public BalaNormal(int daño, float x, float y, float xSpeed1, float ySpeed1, Texture tx, boolean balamia) {

		super(daño, balamia, tx);
		getSprite().setPosition(x, y);
		xSpeed = xSpeed1;
		ySpeed = ySpeed1;
	}

	public void draw(SpriteBatch batch) {
		getSprite().draw(batch);
	}

	public void up(float deltaTime) {
		if (!isDestroyed()) {
			float deltaPositionX = xSpeed * deltaTime;
			float deltaPositionY = ySpeed * deltaTime;

			getSprite().setPosition(getSprite().getX() + deltaPositionX, getSprite().getY() + deltaPositionY);

			tiempoTranscurrido += deltaTime;
			if (tiempoTranscurrido >= tiempoVida) {
				setDestroyed(true);
			}
		}
	}

	public void update(int posicionXCamara, int posicionYCamara, int anchoCamara, int altoCamara, int x, int y) {
		getSprite().setPosition(getSprite().getX() + xSpeed, getSprite().getY() + ySpeed);

		if (getSprite().getX() < ((anchoCamara / 2) - posicionXCamara) || // izquierda
				getSprite().getX() > ((anchoCamara / 2) + posicionXCamara) || // derecha
				getSprite().getY() > ((altoCamara / 2) + posicionYCamara) || // arriba
				getSprite().getY() < ((altoCamara / 2) - posicionYCamara)) { // abajo
			setDestroyed(true);
		}
	}

	public void setVelocity(float xSpeed, float ySpeed) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	public boolean checkCollision(BossFinal boss) {
		if (getMia()) {
			if (boss.getSprite().getBoundingRectangle().overlaps(getSprite().getBoundingRectangle())) {
				boss.restarVida(getDaño());
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
				if (enemigo.getVida() <= 0) {
					enemigo.setDestruida(true);
				}
				return true;
			}

		}
		return false;
	}

	public boolean checkCollision(Nave4 nave) {
		if (!getMia()) {
			if (!nave.estaHerido()
					&& nave.getSpr().getBoundingRectangle().overlaps(getSprite().getBoundingRectangle())) {
				nave.restarVida(getDaño());
				setDestroyed(true);
				nave.sonidoHerido();
				if (nave.getVidas() <= 0) {
					nave.setDestruida(true);
				}
				return true;
			}
		}
		return false;
	}

}