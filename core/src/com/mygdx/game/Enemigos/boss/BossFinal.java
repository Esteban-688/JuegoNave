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
    private float tiempo = Gdx.graphics.getDeltaTime();
    private int vida;
    private Sprite spr;
    private Nave4 nave;
    private BossMove movement;
    private BossAttack ataque;
    private int xIzq, xDer, yUp, yDown;
    

    public BossFinal(int x, int y, float speed, Texture tx, Nave4 nave1, Texture txBala, Sound soundBala) {
        // Inicializa los atributos
        destruida = false;
        vida = 15000;
        spr = new Sprite(tx);
        spr.setPosition(x, y);
        nave = nave1;

        // Crea las instancias de las nuevas clases
        movement = new BossMove(spr, speed, nave, x , y);
        ataque = new BossAttack(spr, txBala, soundBala, nave);//  , balaVelocidad, 1.0f);
        
        //barrera del boss
        xIzq = 0;
        xDer = 0;
        yUp = 0;
        yDown = 0;
    }

    public void draw(SpriteBatch batch) {
        spr.draw(batch);
    }

    public void atacar(SpriteBatch batch, PantallaJuego juego) {
        ataque.atacar(tiempo, juego, batch); 
    }

    public void moverse() {
        movement.move(xIzq, xDer, yDown, yUp, Gdx.graphics.getDeltaTime());
       
    }

    public boolean setBarreraBoss(float xizq, float xder, float ydown, float yup) {
    	xIzq = (int)xizq;
        xDer = (int)xder;
        yUp = (int)yup;
        yDown = (int)ydown;
    	return true;
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
       /* else if(balaa.getSprite().getBoundingRectangle().overlaps(spr.getBoundingRectangle())) {
        	vida-=500;
        	if (vida <= 0) {
                destruida = true;
            }
        	return true;
        }*/
    }
    return false;
    }
	public int getX() {return (int) spr.getX();}
    public int getY() {return (int) spr.getY();}
    public int getVida() {return vida;}
    public Sprite getSprite() {return spr;}
    public boolean isDestruida() {return destruida;}
}
