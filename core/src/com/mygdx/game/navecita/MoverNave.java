package com.mygdx.game.navecita;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class MoverNave {
	private Sprite spr;

	private float xVel;
	private float yVel;
	private float rotacion;

	public MoverNave(Sprite sprite) {
		spr = sprite;
		xVel = 0;
		yVel = 0;
		rotacion = -90;
	}

	public void mover() {

		float x = spr.getX();
		float y = spr.getY();
		// Que se mueva con el teclado
		// Mover adelante
		if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
			xVel -= 0.4f * MathUtils.sinDeg(rotacion);
			yVel += 0.4f * MathUtils.cosDeg(rotacion);

			// Turbo
			if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT) || (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))) {
				xVel -= 0.2f * MathUtils.sinDeg(rotacion);
				yVel += 0.2f * MathUtils.cosDeg(rotacion);
			}
		}

		// Mover hacia atrás
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
			xVel += 0.4f * MathUtils.sinDeg(rotacion);
			yVel -= 0.4f * MathUtils.cosDeg(rotacion);
		}

		// Que rote con el teclado
		// Sentido antihorario
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
			rotacion += 6f; // Incrementa la rotación en sentido antihorario
		}
		
		// Sentido horario
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
			rotacion -= 6f; // Incrementa la rotación en sentido horario
		}
		
		// Disminuir gradualmente la velocidad de desplazamiento
		float reduccionVelocidad = 0.95f; // Factor de reducción
		xVel *= reduccionVelocidad;
		yVel *= reduccionVelocidad;

		// Actualizar la posición de la nave en función de los componentes de velocidad
		x += xVel;
		y += yVel;
		spr.setPosition(x, y);
		spr.setRotation(rotacion); // Establecer la rotación de la nave

	}

	public float getRotacion() {
		return rotacion;
	}
}