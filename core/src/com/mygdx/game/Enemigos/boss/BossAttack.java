package com.mygdx.game.Enemigos.boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.navecita.Nave4;



public class BossAttack {
	
	private BossEstrategy ataqueEstrategy;
    private Sprite sprite;
    private float rotation;
    private Nave4 nave;
   
    
    public BossAttack(Sprite spr, Nave4 nave1) {
        sprite = spr;
        nave = nave1;
        rotation = 90;
        
        //se define el ataque por defecto
       ataqueEstrategy = new AtaqueRecto(new Texture(Gdx.files.internal("ataqueNormalBoss.png")),
        									Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")),
        									2);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
    public void atacar(float delta, PantallaJuego juego, SpriteBatch batch) {
    	ataqueEstrategy.atacar(this, nave, juego, batch);
    	
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotacion) {
        rotation = rotacion;
    }
    
    //patron Strategy
    public void setAtaqueStrategy(BossEstrategy ataqueStrategi) {
        ataqueEstrategy = ataqueStrategi;
    }

	

	
}
