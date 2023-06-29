package com.mygdx.game.Enemigos.enemigoComun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.balas.BalaNormal;
import com.mygdx.game.diccionaInterfaces.Atacar;

public class LanzamisilesAtacar implements Atacar {
	 private float tiempo;
	 private int daño;
	 private Texture txBalaNormal;
	 private Sound soundBala;
	 private float rotacion;
	 
	 public LanzamisilesAtacar() {
		 tiempo = 0.0f;
		 daño = 10;
		 rotacion = 90;
		 txBalaNormal = new Texture(Gdx.files.internal("Txlanzamisiles.png"));
		 soundBala = Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"));
	 }
	 
	@Override
	public void atacar(SpriteBatch batch, PantallaJuego juego, Sprite spr) {
		tiempo += Gdx.graphics.getDeltaTime();
		
        if (tiempo >= 0.5) {
        	
            tiempo = 0.0f;
            float balaVelocidad = 15; // Velocidad de la bala
            float balaDireccionX = -MathUtils.sinDeg(rotacion); // Componente X de la dirección de la bala (invertido)
            float balaDireccionY = MathUtils.cosDeg(rotacion); // Componente Y de la dirección de la bala 
            
            float balaInicialX = spr.getX();
            float balaInicialY = spr.getY();
            
            BalaNormal bala = new BalaNormal(
            		daño,
                    balaInicialX,
                    balaInicialY,
                    balaDireccionX * balaVelocidad,
                    balaDireccionY * balaVelocidad,
                    txBalaNormal,
                    false
                );
            
            bala.setVelocity(balaDireccionX * balaVelocidad, balaDireccionY * balaVelocidad);

            //bala.getSprite().setRotation(rotacion);
            
            bala.up(Gdx.graphics.getDeltaTime());
            
            juego.agregarBala(bala);
            soundBala.play();
            soundBala.setVolume(1, 0.5f);
            bala.draw(batch);
        }
		
	}

}
