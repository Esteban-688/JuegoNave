package com.mygdx.game.Enemigos.boss;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Nave4;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.balas.BalaNormal;

public class AtaqueRecto implements BossEstrategy {
	 private float time;
	 private int daño = 500 ;
	 private Texture bulletTexture;
	 private Sound sound;
	 
	 
	 public AtaqueRecto(Texture bullet, Sound sonido) {
		 bulletTexture = bullet;
		 sound = sonido;
	 }
	
	
	 public void atacar(BossAttack bossAttack, Nave4 nave, float delta, PantallaJuego juego, SpriteBatch batch) {
		 
		 	time += delta;
	     	if (time >= 1) {
	     		
	            time = 0;

	            // Obtener la posición actual de la nave
	            float naveX = nave.getX();
	            float naveY = nave.getY();

	            // Calcular la dirección de la bala hacia la nave
	            float bulletDirectionX = naveX - bossAttack.getSprite().getX();
	            float bulletDirectionY = naveY - bossAttack.getSprite().getY();

	            // mantener o hacer que siga recta
	            float magnitude = (float) Math.sqrt(bulletDirectionX * bulletDirectionX + bulletDirectionY * bulletDirectionY);
	            bulletDirectionX /= magnitude;
	            bulletDirectionY /= magnitude;

	            // Establecer la velocidad de la bala
	            float bulletSpeed = 12; // Velocidad de la bala
	            float bulletSpeedX = bulletDirectionX * bulletSpeed;
	            float bulletSpeedY = bulletDirectionY * bulletSpeed;

	            BalaNormal bala = new BalaNormal(daño ,bossAttack.getSprite().getX(), bossAttack.getSprite().getY(), bulletSpeedX, bulletSpeedY, bulletTexture, false);

	            juego.agregarBala(bala);
	            sound.play();
	          
	        }
	    }
}
