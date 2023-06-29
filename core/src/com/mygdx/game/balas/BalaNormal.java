package com.mygdx.game.balas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Enemigos.boss.BossFinal;
import com.mygdx.game.Enemigos.enemigoComun.EnemyComun;
import com.mygdx.game.navecita.Nave4;

public class BalaNormal extends Bullet {
	
	private float xSpeed;
    private float ySpeed;
    private Sprite spr;
    private float tiempoVida = 5;
    private float tiempoTranscurrido;
    
	public BalaNormal(int daño, float x, float y, float xSpeed, float ySpeed, Texture tx, boolean balamia) {
		
		super(daño, balamia);
		
		spr = new Sprite(tx);
        spr.setPosition(x, y);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	
	public void draw(SpriteBatch batch) {
        spr.draw(batch);
    }
	
	public void up(float deltaTime) {
        if (!isDestroyed()) {
            float deltaPositionX = xSpeed * deltaTime;
            float deltaPositionY = ySpeed * deltaTime;
            
            spr.setPosition(spr.getX() + deltaPositionX, spr.getY() + deltaPositionY);
    
            tiempoTranscurrido += deltaTime;
            if (tiempoTranscurrido >= tiempoVida) {
            	setDestroyed(true);
            }
        }
    }
	public void update(int posicionXCamara, int posicionYCamara, int anchoCamara, int altoCamara, int x, int y) {
		
		spr.setPosition(spr.getX() + xSpeed, spr.getY() + ySpeed);

        if (spr.getX() < ((anchoCamara / 2) - posicionXCamara) || // izquierda
            spr.getX() > ((anchoCamara / 2) + posicionXCamara) || // derecha
            spr.getY() > ((altoCamara / 2) + posicionYCamara) ||  // arriba
            spr.getY() < ((altoCamara / 2) - posicionYCamara)) {   // abajo
            setDestroyed(true);
        }
	}
	
	public void setVelocity(float xSpeed, float ySpeed) {
	
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
	
	public Sprite getSprite() {
        return spr;
    }

	@Override
	public boolean checkCollision(BossFinal boss) {
		if(getMia()) {
            if (boss.getSprite().getBoundingRectangle().overlaps(spr.getBoundingRectangle())) {
                boss.restarVida(getDaño());
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
            if (enemigo.getSpr().getBoundingRectangle().overlaps(spr.getBoundingRectangle())) {
                enemigo.restarVida(getDaño());
                setDestroyed(true);
                if (enemigo.getVida() <= 0) {
                    enemigo.setDestruida(true);
                }
                return true;
            }
        
    }
		return false;
	}

	@Override
	public boolean checkCollision(Nave4 nave) {
		if(!getMia()) {
            if (!nave.estaHerido() && nave.getSpr().getBoundingRectangle().overlaps(spr.getBoundingRectangle())) {
                nave.restarVida(getDaño());
                setDestroyed(true);
                nave.sonidoHerido();
                if (nave.getVidas() <= 0) {
                    nave.setDestruida(true);
                }
                return true;
            }
        }
    return false;
	}
	
}
