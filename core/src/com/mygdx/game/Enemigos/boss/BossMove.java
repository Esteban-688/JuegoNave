package com.mygdx.game.Enemigos.boss;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Nave4;

public class BossMove {
    private Sprite sprite;
    private float speed;
    private float xPosition;
    private float yPosition;
   /* private float minX;
    private float maxX;
    private float minY;
    private float maxY;*/
    private Nave4 nave;

    public BossMove(Sprite sprite, float speed, Nave4 nave) {
        this.sprite = sprite;
        this.speed = speed;
       /* this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;*/
        this.nave = nave;
        //randomizePosition();
    }

    public void move( float minX, float maxX, float maxY, float minY) {
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

    	    // Actualizar la posición del sprite
    	    sprite.setPosition(currentX + movementX, currentY + movementY);

    	    // Verificar si el sprite ha alcanzado la posición objetivo y generar una nueva posición aleatoria
    	    if (Math.abs(deltaX) < speed && Math.abs(deltaY) < speed) {
    	        randomizePosition(minX, maxX, minY, maxY);
    	    }
    	}


    private void randomizePosition(float minX, float maxX, float minY, float maxY) {
        xPosition = MathUtils.random(minX, maxX);
        yPosition = MathUtils.random(minY, maxY);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
