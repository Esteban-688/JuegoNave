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
	
	private BossEstrategy ataqueEstrategy;
    private Sprite sprite;
    private float rotation;
    private float time;
    private Nave4 nave;
    private int speed = 10;
   
    
    public BossAttack(Sprite sprite, Nave4 nave1) {
        this.sprite = sprite;
  
        nave = nave1;
        rotation = 90;
        
        //se define el ataque por defecto
        ataqueEstrategy = new AtaqueRecto(new Texture(Gdx.files.internal("ataqueNormalBoss.png")),
        		Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
    public void atacar(float delta, PantallaJuego juego, SpriteBatch batch) {
    	ataqueEstrategy.atacar(this, nave, delta, juego, batch);
    	
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
    
    //patron Strategy
    public void setAtaqueStrategy(BossEstrategy ataqueStrategy) {
        this.ataqueEstrategy = ataqueStrategy;
    }

	

	
}
