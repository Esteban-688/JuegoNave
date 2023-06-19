package com.mygdx.game.Enemigos.boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Ball2;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.Enemigos.Enemigo;
import com.mygdx.game.balas.BalaNormal;
import com.mygdx.game.balas.Bullet;
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
        vida = 15000;
        maxVida = vida;
        vidaPorcentaje= 100f;
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
    public boolean checkCollision(Bullet bala) {
    	
        if(bala.getMia()) {
            if (bala.getSprite().getBoundingRectangle().overlaps(spr.getBoundingRectangle())) {
                vida-=bala.getDaño();
             // Actualizar la vida en porcentaje
                vidaPorcentaje = (vida * 100f) / maxVida;
                
                if (vida <= 0) {
                    destruida = true;
                    return false;
                }else {
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
                return true;
            }
        }
        
      return false;
    }
    
    public float getVidaPorcentaje() {
        return vidaPorcentaje;
    }
    
    public void setAtaqueStrategy(BossEstrategy ataqueStrategy) {
        ataque.setAtaqueStrategy(ataqueStrategy);
    }
    
	public int getX() {return (int) spr.getX();}
    public int getY() {return (int) spr.getY();}
    public int getVida() {return vida;}
    public Sprite getSprite() {return spr;}
    public boolean isDestruida() {return destruida;}
}
