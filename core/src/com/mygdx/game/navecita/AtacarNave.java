package com.mygdx.game.navecita;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.balas.BalaEspecial;
import com.mygdx.game.balas.BalaNormal;
import com.mygdx.game.balas.Bullet;

public class AtacarNave {
	private Sprite spr;
	private Sound soundBala, soundBalaEspecial;
	private Texture texturaBalaNormal,texturaBalaEspecial;

	private boolean disparoEspecial;

	private float tiempo, tiempoEntreDisparo;

	private int daño;

	public AtacarNave(Sprite sprite, Texture txBala, Sound sndBala, Texture txBalaEspecial, Sound sndBalaEspecial) {
		spr = sprite;

		soundBala = sndBala;
		texturaBalaNormal = txBala;

		texturaBalaEspecial = txBalaEspecial;
		soundBalaEspecial = sndBalaEspecial;

		disparoEspecial = true;
		tiempo = 0.0f;
		daño = 50;
		tiempoEntreDisparo = 0.0f;
	}

	public void atacar(SpriteBatch batch, PantallaJuego juego, float rotacion) {
		// Disparos

		if (disparoEspecial && Gdx.input.isKeyPressed(Input.Keys.E)) {
			// Disparar bala especial
			disparoEspecial = false;

			// Calcular la posición inicial de la bala en el centro de la nave
			float balaInicialX = spr.getX() + 25;// + spr.getWidth() / 2 - 5;
			float balaInicialY = spr.getY() + 15;// + spr.getHeight() / 2- 5;

			Bullet balaEspecial = new BalaEspecial(150, balaInicialX, balaInicialY, texturaBalaEspecial, true);

			juego.agregarBala(balaEspecial);
			soundBalaEspecial.play();

		}

		// tiempo de espera antes de otro disparo especial;
		if (disparoEspecial == false) {
			tiempo += Gdx.graphics.getDeltaTime();

			if (tiempo >= 10) {
				disparoEspecial = true;
				tiempo = 0.0f;
			}
		}

		tiempoEntreDisparo += Gdx.graphics.getDeltaTime();

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && tiempoEntreDisparo > 0.2) {
			tiempoEntreDisparo = 0.0f;
			// disparo normal

			float balaVelocidad = 40; // Velocidad de la bala
			float balaDireccionX = -MathUtils.sinDeg(rotacion);
			float balaDireccionY = MathUtils.cosDeg(rotacion);

			// Calcular la posición inicial de la bala en el centro de la nave
			float balaInicialX = spr.getX() + spr.getWidth() / 2 - 5;
			float balaInicialY = spr.getY() + spr.getHeight() / 2 - 27;

			BalaNormal bala = new BalaNormal(daño, balaInicialX, balaInicialY, balaDireccionX * balaVelocidad,
					balaDireccionY * balaVelocidad, texturaBalaNormal, true);

			bala.setVelocity(balaDireccionX * balaVelocidad, balaDireccionY * balaVelocidad);

			// Girar la textura de la bala según la rotación de la nave
			bala.getSprite().setRotation(rotacion);

			juego.agregarBala(bala);
			soundBala.play();
		}
	}
}