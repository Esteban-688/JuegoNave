package com.mygdx.game.Enemigos.boss;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class BossMove {
	private Sprite sprite;

	private float speed;
	private float maxTime;
	private float elapsedTime;

	private int xPosition;
	private int yPosition;

	// Constructor
	public BossMove(Sprite sprite1, float speed1, int x, int y) {
		sprite = sprite1;
		speed = speed1;
		maxTime = 3.0f; // Tiempo máximo en segundos
		xPosition = x;
		yPosition = y;
	}

	// Movimiento del Boss
	public void move(int minX, int maxX, int minY, int maxY, float deltaTime) {
		// Obtener la posición actual del sprite
		float currentX = sprite.getX();
		float currentY = sprite.getY();

		// Calcular el desplazamiento horizontal
		float deltaX = xPosition - currentX;
		float movementX = Math.signum(deltaX) * speed;

		// Calcular el desplazamiento vertical
		float deltaY = yPosition - currentY;
		float movementY = Math.signum(deltaY) * speed;

		// Verificar si el sprite se desplaza más allá de los límites horizontales
		if (movementX > 0 && currentX + movementX > maxX) {
			movementX = maxX - currentX;
		} else if (movementX < 0 && currentX + movementX < minX) {
			movementX = minX - currentX;
		}

		// Verificar si el sprite se desplaza más allá de los límites verticales
		if (movementY > 0 && currentY + movementY > maxY) {
			movementY = maxY - currentY;
		} else if (movementY < 0 && currentY + movementY < minY) {
			movementY = minY - currentY;
		}

		// Verificar si el sprite ha alcanzado la posición objetivo
		if (Math.abs(deltaX) < speed && Math.abs(deltaY) < speed) {
			movementX = 0;
			movementY = 0;
		}

		// Actualizar la posición del sprite
		sprite.setPosition(currentX + movementX, currentY + movementY);

		// Verificar si el sprite ha alcanzado la posición objetivo y genera una nueva
		// posición aleatoria
		if (Math.abs(deltaX) < speed && Math.abs(deltaY) < speed) {
			elapsedTime += deltaTime;
			if (elapsedTime >= maxTime) {
				randomizePosition(minX, maxX, minY, maxY);
				elapsedTime = 0;
			}
		}
	}

	// Posición random del Boss
	private void randomizePosition(int minX, int maxX, int minY, int maxY) {
		int margin = (int) sprite.getWidth();

		xPosition = MathUtils.random(minX + margin, maxX - margin);
		yPosition = MathUtils.random(minY + margin, maxY - margin);

	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public float getSpeed() {
		return speed;
	}
}
