package com.mygdx.game.Enemigos.enemigoComun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Config;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.balas.BalaNormal;
import com.mygdx.game.diccionaInterfaces.Atacar;

public class ArtilleroAtacar implements Atacar {
	private Sound soundBala;
	private Texture txBalaNormal;

	private float time;

	private int daño;

	// Constructor del artillero
	public ArtilleroAtacar() {
		daño = 30;
		time = 0.0f;

		txBalaNormal = new Texture(Gdx.files.internal("artilleroBala.png"));
		soundBala = Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"));
	}

	// Método atacar propio del artillero
	public void atacar(SpriteBatch batch, PantallaJuego juego, Sprite spr) {
		time += Gdx.graphics.getDeltaTime();

		if (time >= 2) {
			time = 0.0f;

			// Obtener la posición actual de la nave
			float naveX = Config.naveGetX();
			float naveY = Config.naveGetY();

			// Calcular la dirección de la bala hacia la nave
			float bulletDirectionX = naveX - spr.getX();
			float bulletDirectionY = naveY - spr.getY();

			// mantener o hacer que siga recta
			float magnitude = (float) Math
					.sqrt(bulletDirectionX * bulletDirectionX + bulletDirectionY * bulletDirectionY);
			bulletDirectionX /= magnitude;
			bulletDirectionY /= magnitude;

			// Establecer la velocidad de la bala
			float bulletSpeed = 10; // Velocidad de la bala
			float bulletSpeedX = (bulletDirectionX) * bulletSpeed;
			float bulletSpeedY = bulletDirectionY * bulletSpeed;

			BalaNormal bala = new BalaNormal(daño, spr.getX() + 120, spr.getY(), bulletSpeedX, bulletSpeedY,
					txBalaNormal, false);

			juego.agregarBala(bala);
			soundBala.play();
		}
	}

}
