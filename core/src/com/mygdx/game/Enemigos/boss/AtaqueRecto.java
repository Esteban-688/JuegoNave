package com.mygdx.game.Enemigos.boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.balas.BalaNormal;
import com.mygdx.game.navecita.Nave4;

public class AtaqueRecto implements BossEstrategy {
	 private float time;
	 private int daño;
	 private Texture bulletTexture;
	 private Sound sound;
	 private float lapso;
	 
	 public AtaqueRecto(Texture bullet, Sound sonido, float tiempo) {
		 bulletTexture = bullet;
		 sound = sonido;
		 daño = 40;
		 lapso = tiempo;
	 }
	
	
	 public void atacar(BossAttack bossAttack, Nave4 nave, PantallaJuego juego, SpriteBatch batch) {
		 
		 	time += Gdx.graphics.getDeltaTime();
	     	if (time >= lapso) {
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

	            BalaNormal bala = new BalaNormal(daño, 
	            		bossAttack.getSprite().getX(),
	            		bossAttack.getSprite().getY(),
	            		bulletSpeedX,
	            		bulletSpeedY,
	            		bulletTexture,
	            		false);

	            juego.agregarBala(bala);
	            sound.play();
	          
	        }
	    }
}
