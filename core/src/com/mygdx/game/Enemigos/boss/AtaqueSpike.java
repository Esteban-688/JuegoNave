package com.mygdx.game.Enemigos.boss;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Nave4;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.balas.BalaNormal;

public class AtaqueSpike implements BossEstrategy{
	
	 private float time;
	 private int daño ;
	 private Texture bulletTexture;
	 private Sound sound;
	 private int spikeCount;
	 private float angleIncrement;
	 
	 public AtaqueSpike(Texture bullet, Sound sonido) {
		 
		 bulletTexture = bullet;
		 sound = sonido;
		 
		 daño = 1000 ;
		 spikeCount = 6; // Número de espinas a lanzar
		 angleIncrement = 360f / spikeCount; //separacion
		 
	 }

	@Override
	public void atacar(BossAttack bossAttack, Nave4 nave, float delta, PantallaJuego juego, SpriteBatch batch) {
		time += delta;
        if (time >= 1) {
            time = 0;

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
