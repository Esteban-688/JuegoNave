 package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;


public class EnemyComun implements Enemigo {
    
	private boolean destruida = false;
	private int vida = 200;
    private Sprite spr;
    private Nave4 nave;
    private Texture txBalaNormal;
    private Sound soundBala;
    private float rotacion = 90;
    private float tiempo = 0.0f;
    private boolean moviendoArriba = true;
    private float velocidadMovimiento = 0.2f; 
    private float deltaY ;//= 200.0f; 
    private float tiempoMovimiento = 0.0f;
    
    public EnemyComun(int x, int y, Texture tx, Nave4 nave1, Texture txBala, Sound soundBala, float delta) {
        spr = new Sprite(tx);
        spr.setPosition(x, y);
        nave = nave1;
        deltaY=delta;
        
        this.soundBala = soundBala;
        txBalaNormal = txBala;
        spr.setRotation(rotacion);
    }
    public void draw(SpriteBatch batch) {
    	// Dibujar el sprite del enemigo
        spr.draw(batch);
    }
    public void atacar(SpriteBatch batch, PantallaJuego juego) {
        tiempo += Gdx.graphics.getDeltaTime();
        moverse();
        if (tiempo >= 1) {
            tiempo = 0.0f;
            float balaVelocidad = 10; // Velocidad de la bala
            float balaDireccionX = -MathUtils.sinDeg(rotacion); // Componente X de la dirección de la bala (invertido)
            float balaDireccionY = MathUtils.cosDeg(rotacion); // Componente Y de la dirección de la bala 
            
            float balaInicialX = spr.getX();
            float balaInicialY = spr.getY();
            
            BalaNormal bala = new BalaNormal(
                    balaInicialX,
                    balaInicialY,
                    balaDireccionX * balaVelocidad,
                    balaDireccionY * balaVelocidad,
                    txBalaNormal,
                    false
                );
            
            bala.setVelocity(balaDireccionX * balaVelocidad, balaDireccionY * balaVelocidad);

            // Girar la textura de la bala según la rotación de la nave
            bala.getSprite().setRotation(rotacion);

           // juego.agregarBala(bala);
            //soundBala.play();
            
            bala.up(Gdx.graphics.getDeltaTime());
            
            juego.agregarBala(bala);
            soundBala.play();
            bala.draw(batch);
        }

        
    }

    public void moverse() {
    	tiempoMovimiento += Gdx.graphics.getDeltaTime();
    	
    	 float naveY = nave.getY(); // Obtener la posición Y de la nave
	     float newY = moviendoArriba ? naveY + deltaY : naveY - deltaY;
    	
    	if(tiempoMovimiento >= 1.5) {
    		tiempoMovimiento = 0.0f;
	        // Limitar el movimiento del enemigo dentro del rango establecido por deltaY
	        if (newY >= naveY + deltaY) {
	            newY = naveY + deltaY;
	            moviendoArriba = false;
	        } else if (newY <= naveY - deltaY) {
	            newY = naveY - deltaY;
	            moviendoArriba = true;
	        }
    	}
        spr.setPosition(nave.getX() + 300, newY);
    }

    public boolean checkCollision(Ball2 ball) {
        
        return true;
    }
public boolean checkCollision(Bullet balaa) {
        
	if (balaa instanceof BalaNormal) {
    	
        BalaNormal bala = (BalaNormal) balaa;
        if(bala.getMia()) {
            if (bala.getSprite().getBoundingRectangle().overlaps(spr.getBoundingRectangle())) {
                vida-=50;
                if (vida <= 0) {
                    destruida = true;
                }
                return true;
            }
        }
    }
    return false;
    }

    public int getX() {
        return (int) spr.getX();
    }

    public int getY() {
        return (int) spr.getY();
    }
    public boolean isDestruida() {return destruida;}
}
