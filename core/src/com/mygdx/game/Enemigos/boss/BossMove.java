package com.mygdx.game.Enemigos.boss;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Nave4;

public class BossMove {
    private Sprite sprite;
    private float speed;
    private int xPosition;
    private int yPosition;
    private Nave4 nave;
    private float elapsedTime;
    private float maxTime;

    public BossMove(Sprite sprite1, float speed1, Nave4 nave1, int x , int y) {
        sprite = sprite1;
        speed = speed1;
        nave = nave1;
        maxTime = 3.0f; // Tiempo máximo en segundos
        xPosition = x;
        yPosition = y;
    }

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

        // Verificar si el sprite ha alcanzado la posición objetivo y genera una nueva posición aleatoria
        if (Math.abs(deltaX) < speed && Math.abs(deltaY) < speed) {
            elapsedTime += deltaTime;
            if (elapsedTime >= maxTime) {
                randomizePosition(minX, maxX, minY, maxY);
                elapsedTime = 0;
            }
        }
    }



    private void randomizePosition(int minX, int maxX, int minY, int maxY) {
       
    	 int margin = (int)sprite.getWidth();
    	    
    	 xPosition = MathUtils.random(minX + margin, maxX - margin);
         yPosition = MathUtils.random(minY + margin, maxY - margin);   
    	
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
