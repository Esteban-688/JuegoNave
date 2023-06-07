 package com.mygdx.game.Enemigos.enemigoComun;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Ball2;
import com.mygdx.game.Nave4;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.Enemigos.Enemigo;
import com.mygdx.game.balas.BalaNormal;
import com.mygdx.game.balas.Bullet;


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
    private float distancia;
    private float ySpeed;
    private float yEnemigo;
    private int alto;
    
    public EnemyComun(int x, int y, int xDistancia, float ySpeed, Texture tx, Nave4 nave1, Texture txBala, Sound soundBala, int altoY) {
        spr = new Sprite(tx);
        spr.setPosition(x, y);
        nave = nave1;
        alto= altoY;
        
        distancia = xDistancia;
        this.ySpeed = ySpeed;
        
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
            soundBala.setVolume(1, 0.5f);
            bala.draw(batch);
        }

        
    }

    public void moverse() {
    	/*tiempoMovimiento += Gdx.graphics.getDeltaTime();
    	
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
    	}*/
       // spr.setPosition(nave.getX() + 300, newY);
    	//float balaDireccionX = -MathUtils.sinDeg(rotacion); 
        //float balaDireccionY = MathUtils.cosDeg(rotacion); 
    	
    
    	
    	if(spr.getY() >= alto) {
    		yEnemigo = spr.getY() - ySpeed;
    		moviendoArriba = true;
    		
    	}
    	else if(spr.getY() <= 75) {
    		yEnemigo = spr.getY() + ySpeed;
    		moviendoArriba = false;
    	}
    	else if(moviendoArriba) {
    		yEnemigo = spr.getY() - ySpeed;
    	}
    	else if(!moviendoArriba) {
    		yEnemigo = spr.getY() + ySpeed;
    	}
    	
    	spr.setPosition(nave.getX() + distancia, yEnemigo);
    	
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
    
    public void destruirTodo() {
    	destruida = true;
    }

    public int getX() {
        return (int) spr.getX();
    }

    public int getY() {
        return (int) spr.getY();
    }
    public boolean isDestruida() {return destruida;}
}
