package com.mygdx.game.Enemigos.boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Nave4;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.balas.BalaNormal;



public class BossAttack {
    private Sprite sprite;
    private float rotation;
    private float time;
    private Sound sound;
    private Texture bulletTexture;
    private Nave4 nave;
    private int speed = 10;
    
    public BossAttack(Sprite sprite, Texture bulletTexture, Sound sound, Nave4 nave1) {
        this.sprite = sprite;
        this.bulletTexture = bulletTexture;
        this.sound = sound;
        nave = nave1;
        rotation = 90;
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
    public void atacar(float delta, PantallaJuego juego, SpriteBatch batch) {
        time += delta;
        if (time >= 1) {
            time = 0;

            // Obtener la posición actual de la nave
            float naveX = nave.getX();
            float naveY = nave.getY();

            // Calcular la dirección de la bala hacia la nave
            float bulletDirectionX = naveX - sprite.getX();
            float bulletDirectionY = naveY - sprite.getY();

            // Normalizar la dirección
            float magnitude = (float) Math.sqrt(bulletDirectionX * bulletDirectionX + bulletDirectionY * bulletDirectionY);
            bulletDirectionX /= magnitude;
            bulletDirectionY /= magnitude;

            // Establecer la velocidad de la bala
            float bulletSpeed = 10; // Velocidad de la bala (ajusta según tus necesidades)
            float bulletSpeedX = bulletDirectionX * bulletSpeed;
            float bulletSpeedY = bulletDirectionY * bulletSpeed;

            // Crear la bala con la dirección y textura adecuadas
            BalaNormal bala = new BalaNormal(sprite.getX(), sprite.getY(), bulletSpeedX, bulletSpeedY, bulletTexture, false);

            // Agregar la bala al juego y reproducir el sonido
            juego.agregarBala(bala);
            sound.play();
            
            //bala.draw(batch); // Dibujar la bala (asegúrate de dibujarla en el lugar adecuado)
        }
    }


    public void playSound() {
        sound.play();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

	

	
}
