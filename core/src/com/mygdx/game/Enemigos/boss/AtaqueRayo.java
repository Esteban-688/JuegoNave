package com.mygdx.game.Enemigos.boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.navecita.Nave4;

//Esta clase solo está implementada para demostrar que se pueden crear ataques libremente sin necesidad de atar el código a errores.
// Para ello se utiliza encapsulamiento y así demostrar su correcto encapsulamiento
public class AtaqueRayo implements BossEstrategy {
	private float time;
	private int daño = 500;
	private Texture warningTexture;
	private TextureRegion damageTexture;
	private Sound sound;

	public AtaqueRayo(Texture warningTx, TextureRegion damageTx, Sound snd) {
		warningTexture = warningTx;
		damageTexture = damageTx;
		sound = snd;
	}

	@Override
	public void atacar(BossAttack bossAttack, Nave4 nave, PantallaJuego juego, SpriteBatch batch) {
		time += Gdx.graphics.getDeltaTime();

		if (time >= 3.0f) {
			time = 0;

			// Obtener la posición del boss
			float bossX = bossAttack.getSprite().getX();
			float bossY = bossAttack.getSprite().getY();

			// Obtener la posición de la nave
			float naveX = nave.getX();
			float naveY = nave.getY();

			// Calcular la dirección del rayo hacia la nave
			float rayDirectionX = naveX - bossX;
			float rayDirectionY = naveY - bossY;

			// Calcular la rotación del rayo
//            float rotation = (float) Math.atan2(rayDirectionY, rayDirectionX) * 180 / Math.PI;

			// Crear el rayo de advertencia en la posición del boss
//            batch.draw(warningTexture, bossX, bossY, 0, 0, warningTexture.getWidth(), warningTexture.getHeight(), 1, 1, rotation);

			if (time >= 2.0f) {
				// Crear el rayo de daño en la posición del boss
				// batch.draw(damageTexture, bossX, bossY, 0, 0, damageTexture.getRegionWidth(),
				// damageTexture.getRegionHeight(), 1, 1, rotation);
				sound.play();
			}
		}
	}
}
