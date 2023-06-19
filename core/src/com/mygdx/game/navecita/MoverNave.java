package com.mygdx.game.navecita;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
			 // Que se mueva con teclado
	    	//mover adelante
	        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
	            xVel -= 0.5f * MathUtils.sinDeg(rotacion);
	            yVel += 0.5f * MathUtils.cosDeg(rotacion);
	            //turbo
	            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
	                xVel -= 0.5f * MathUtils.sinDeg(rotacion);
	                yVel += 0.5f * MathUtils.cosDeg(rotacion);
	            }
	        }
	        
	        
	        //mover atras
	        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
	            xVel += 0.5f * MathUtils.sinDeg(rotacion);
	            yVel -= 0.5f * MathUtils.cosDeg(rotacion);
	        }
	
	        // Que rote con teclado
	        
	        //sentido antihorario
	        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.A)) {
	            rotacion += 4f; // Incrementa la rotación en sentido antihorario
	        }
	            //sentido horario
	        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)||Gdx.input.isKeyPressed(Input.Keys.D)) {
	            rotacion -= 4f; // Incrementa la rotación en sentido horario
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
