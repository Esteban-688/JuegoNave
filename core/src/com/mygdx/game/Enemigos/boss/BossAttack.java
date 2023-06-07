package com.mygdx.game.Enemigos.boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.balas.BalaNormal;


public class BossAttack {
    private Sprite sprite;
    private float rotation;
    private float time;
    private Sound sound;
    private Texture bulletTexture;

    public BossAttack(Sprite sprite, Texture bulletTexture, Sound sound) {
        this.sprite = sprite;
        this.bulletTexture = bulletTexture;
        this.sound = sound;
        rotation = 90;
    }

    public void update(float delta) {
        time += delta;
        if (time >= 1) {
            time = 0;

            float bulletSpeed = 10;
            float bulletDirectionX = -MathUtils.sinDeg(rotation);
            float bulletDirectionY = MathUtils.cosDeg(rotation);

            float bulletInitialX = sprite.getX();
            float bulletInitialY = sprite.getY();

            BalaNormal bullet = new BalaNormal(bulletInitialX, bulletInitialY, bulletDirectionX * bulletSpeed, bulletDirectionY * bulletSpeed, bulletTexture, false);
           
            bullet.getSprite().setRotation(rotation);

            // Aquí puedes hacer algo con la bala, como agregarla a una lista de balas del juego
        }
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
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
