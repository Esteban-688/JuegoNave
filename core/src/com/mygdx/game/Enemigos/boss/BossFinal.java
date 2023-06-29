package com.mygdx.game.Enemigos.boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.Enemigos.Enemigo;
import com.mygdx.game.navecita.Nave4;

public class BossFinal implements Enemigo {
    
	private Texture originalTx, heridoTx;
	private boolean destruida;
    private float tiempo = Gdx.graphics.getDeltaTime();
    private int vida;
    private Sprite spr;
    private Nave4 nave;
    private BossMove movement;
    private BossAttack ataque;
    private int xIzq, xDer, yUp, yDown;
    private int maxVida;
    private float vidaPorcentaje;
    

    public BossFinal(int x, int y, float speed, Texture txOriginal, Texture txHerido, Nave4 nave1) {
        // Inicializa los atributos
        destruida = false;
        vida = 5000;
        maxVida = vida;
        vidaPorcentaje = 100f;
        spr = new Sprite(txOriginal);
        
        spr.setPosition(x, y);
        nave = nave1;

        originalTx = txOriginal;
        heridoTx = txHerido;
        
        // Crea las instancias de las nuevas clases
        movement = new BossMove(spr, speed, nave, x , y);
        ataque = new BossAttack(spr, nave);//  , balaVelocidad, 1.0f);
        
        //barrera del boss
        xIzq = 0;
        xDer = 0;
        yUp = 0;
        yDown = 0;
    }

    public void draw(SpriteBatch batch, PantallaJuego juego) {
        spr.draw(batch);
       // moverse(spr);
        //atacar(batch, juego, spr);
    }

    public void atacar(SpriteBatch batch, PantallaJuego juego, Sprite spr) {
        ataque.atacar(tiempo, juego, batch); 
    }

    public void moverse(Sprite spr) {
        movement.move(xIzq, xDer, yDown, yUp, Gdx.graphics.getDeltaTime());
       
    }

    public boolean setBarreraBoss(float xizq, float xder, float ydown, float yup) {
    	xIzq = (int)xizq;
        xDer = (int)xder;
        yUp = (int)yup;
        yDown = (int)ydown;
    	return true;
    }
    public void setPosition(int x, int y) {
    	spr.setPosition(x, y);
    }
    
    public float getVidaPorcentaje() {
        return vidaPorcentaje;
    }
    public void setVidaPorcentaje(float a) {
    	vidaPorcentaje = a;
    }
    public void restarVida(int a) {
    	vida -= a;
    }
    public void setAtaqueStrategy(BossEstrategy ataqueStrategy) {
        ataque.setAtaqueStrategy(ataqueStrategy);
    }
    
    public void texturaHerida() {
	    	//se reemplaza la tx
	   	 spr.setTexture(heridoTx);
	   	 float heridoDuration = 0.1f;//se crea el contador
	   	 Timer.schedule(new Timer.Task() {
	            @Override
	            public void run() {
	                spr.setTexture(originalTx);
	            }
	        }, heridoDuration);
    }
	public int getX() {return (int) spr.getX();}
    public int getY() {return (int) spr.getY();}
    public int getVida() {return vida;}
    public int getMaxVida() {return maxVida;}
    public Sprite getSprite() {return spr;}
    public boolean isDestruida() {return destruida;}
    public void setDestruida(boolean a) {destruida=a;}
}
