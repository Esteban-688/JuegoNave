package com.mygdx.game.balas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Config;
import com.mygdx.game.Enemigos.boss.BossFinal;
import com.mygdx.game.Enemigos.enemigoComun.EnemyComun;
import com.mygdx.game.navecita.Nave4;
import com.badlogic.gdx.math.Intersector;

public class BalaEspecial extends Bullet {
    
    private float sizeBala = 150;
    private Circle circle;
    private Sprite sprite;
    private float tiempoDeBala = 0.0f;
   
    public BalaEspecial(int daño, float x, float y, Texture tx, boolean balaMia) {
       super(daño, balaMia);
       
    	circle = new Circle(x, y, sizeBala / 2);
        sprite = new Sprite(tx);
        sprite.setSize(sizeBala, sizeBala);
        }

    public void draw(SpriteBatch batch) {
    	sprite.setPosition(circle.x - circle.radius, circle.y - circle.radius);
        sprite.draw(batch);
        
    }

    public void update(int posicionXCamara, int posicionYCamara, int anchoCamara, int altoCamara, int x, int y) {
        circle.setPosition(x, y);
        sprite.setPosition(x, y);
        tiempoDeBala += Gdx.graphics.getDeltaTime();
      
        if(tiempoDeBala >= 4) {
        	setDestroyed(true);
        }
    }
    public boolean checkCollision(BossFinal boss) {
		if(getMia()) {
            if (boss.getSprite().getBoundingRectangle().overlaps(sprite.getBoundingRectangle())) {
                boss.restarVida(getDaño());
                boss.setPosition(Config.getDer()-300, Config.getUp()/2);
             // Actualizar la vida en porcentaje
                float vidaPorcentaje = (boss.getVida() * 100f) / boss.getMaxVida();
                boss.setVidaPorcentaje(vidaPorcentaje);
                setDestroyed(true);
                if (boss.getVida() <= 0) {
                    boss.setDestruida (true);
                    return false;
                }else {
                	boss.texturaHerida();
                }
                return true;
            }
        }
        
      return false;
	}
    @Override
	public boolean checkCollision(EnemyComun enemigo) {
		if(getMia()) {
            if (enemigo.getSpr().getBoundingRectangle().overlaps(sprite.getBoundingRectangle())) {
                enemigo.restarVida(getDaño());
                setDestroyed(true);
                enemigo.setDestruida(true);
                return true;
            }
        
    }
		return false;
	}
    public boolean checkCollision(Nave4 nave) {
		if(getMia()) {
            if (!nave.estaHerido() && nave.getSpr().getBoundingRectangle().overlaps(sprite.getBoundingRectangle())) {
                nave.SumarVida(1);
                return true;
            }
        }
    return false;
	}

    public Circle getCircle() {
        return circle;
    }
    public Sprite getSprite() {
        return sprite;
    }

}
