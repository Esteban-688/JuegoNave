package com.mygdx.game.Enemigos.boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Ball2;
import com.mygdx.game.Nave4;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.Enemigos.Enemigo;
import com.mygdx.game.balas.BalaNormal;
import com.mygdx.game.balas.Bullet;

public class BossFinal implements Enemigo {
    private boolean destruida;
    private int vida;
    private Sprite spr;
    private Nave4 nave;
    private BossMove movement;
    private BossAttack attack;

    public BossFinal(int x, int y, int xSpeed, float ySpeed, Texture tx, Nave4 nave1, Texture txBala, Sound soundBala) {
        // Inicializa los atributos
        destruida = false;
        vida = 15000;
        spr = new Sprite(tx);
        spr.setPosition(x, y);
        nave = nave1;

        // Crea las instancias de las nuevas clases
        movement = new BossMove(spr, ySpeed, yUp, yDown);
        attack = new BossAttack(spr, txBala, soundBala, rotacion, balaVelocidad, 1.0f);
    }

    public void draw(SpriteBatch batch) {
        // Dibuja el sprite del enemigo
        spr.draw(batch);
    }

    public void atacar(SpriteBatch batch, PantallaJuego juego) {
        // Llama al método de ataque de la clase BossAttack
        attack.attack(batch, juego);
    }

    public void moverse() {
        // Llama al método de movimiento de la clase BossMovement
        movement.move();
    }

    


	public boolean checkCollision(Ball2 ball) {
        
        return true;
    }
    public boolean checkCollision(Bullet balaa) {
        
	if (balaa instanceof BalaNormal) {
    	
        BalaNormal bala = (BalaNormal) balaa;
        if(bala.getMia()) {
            if (bala.getSprite().getBoundingRectangle().overlaps(spr.getBoundingRectangle())) {
                vida-=50;
                if (vida <= 0) {
                    destruida = true;
                }
                return true;
            }
        }
        else if(balaa.getSprite().getBoundingRectangle().overlaps(spr.getBoundingRectangle())) {
        	vida-=500;
        	if (vida <= 0) {
                destruida = true;
            }
        	return true;
        }
    }
    return false;
    }
	public void setLimite(int yup, int ydown, int  xder) {
		 yUp = yup;
		 yDown = ydown;
		  xDer= xder;
	}
	public int getX() {return (int) spr.getX();}
    public int getY() {return (int) spr.getY();}
    public int getVida() {return vida;}
    public Sprite getSprite() {return spr;}
    public boolean isDestruida() {return destruida;}
}
