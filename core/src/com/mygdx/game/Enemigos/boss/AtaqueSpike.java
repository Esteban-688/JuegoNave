package com.mygdx.game.Enemigos.boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.balas.BalaNormal;
import com.mygdx.game.navecita.Nave4;

public class AtaqueSpike implements BossEstrategy{
	
	 private float time;
	 private int daño ;
	 private Texture bulletTexture;
	 private Sound sound;
	 private int spikeCount, a;
	 private float angleIncrement;
	 
	 public AtaqueSpike(Texture bullet, Sound sonido, int spike) {
		 
		 bulletTexture = bullet;
		 sound = sonido;
		 
		 daño = 1000 ;
		 a = spike; // Número de espinas a lanzar
		 
		 
	 }

	@Override
	public void atacar(BossAttack bossAttack, Nave4 nave, PantallaJuego juego, SpriteBatch batch) {
		 //separacion
		spikeCount=a;
		angleIncrement = 360f / spikeCount;
		
		time += Gdx.graphics.getDeltaTime();
		
        if (time >= 1) {
            time = 0.0f ;

            float initialAngle = bossAttack.getRotation(); // Ángulo inicial de las espinas

            // Lanzar las espinas en las direcciones de las puntas de la estrella
            for (int i = 0; i < spikeCount; i++) {
                float angle = initialAngle + i * angleIncrement; // Ángulo de la esquina de la estrella
                
                // Calcular la dirección de la espina
                float spikeDirectionX = MathUtils.cosDeg(angle);
                float spikeDirectionY = MathUtils.sinDeg(angle);

                // Establecer la velocidad de la espina
                float spikeSpeed = 10f; // Velocidad de la espina
                float spikeSpeedX = spikeDirectionX * spikeSpeed;
                float spikeSpeedY = spikeDirectionY * spikeSpeed;

               
                BalaNormal espina = new BalaNormal(daño ,bossAttack.getSprite().getX()+20, bossAttack.getSprite().getY(), spikeSpeedX, spikeSpeedY, bulletTexture, false);
                juego.agregarBala(espina);
                sound.play();
            }
        }
    }


}
