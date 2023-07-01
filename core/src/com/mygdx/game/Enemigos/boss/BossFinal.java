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
    
	private Texture enojado, triste, curioso, heridoTx, auxTx;
	private boolean destruida, herido;
    private float tiempo = Gdx.graphics.getDeltaTime();
    private int vida;
    private Sprite spr;
    private Nave4 nave;
    private BossMove movement;
    private BossAttack ataque;
    private int xIzq, xDer, yUp, yDown, maxVida;
    private float vidaPorcentaje;
    

    public BossFinal(int x, int y, float speed, Nave4 nave1) {
        // Inicializa los atributos
        destruida = false;
        herido = false;
        vida = 5000;
        maxVida = vida;
        vidaPorcentaje = 100f;
       
        
        
        nave = nave1;

        curioso =  new Texture(Gdx.files.internal("curioso.png"));
        enojado = new Texture(Gdx.files.internal("enojado.png"));
        triste = new Texture(Gdx.files.internal("triste.png"));
        heridoTx = new Texture(Gdx.files.internal("txHeridoBoss.png"));
        spr = new Sprite(curioso);
        spr.setPosition(x, y);
        
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
        updateEmociones();
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
    	 auxTx = getTx();
    	 herido = true;
    	spr.setTexture(heridoTx);
	   	
	   	 float heridoDuration = 0.15f;//se crea el contador
	   	 
	   	 Timer.schedule(new Timer.Task() {
	            @Override
	            public void run() {
	                spr.setTexture(auxTx);
	                herido = false;
	            }
	        }, heridoDuration);
    }
    private void updateEmociones() {
    	if(!herido) {
	    	if(vidaPorcentaje > 69) {setTx(curioso);}
	    	else if(vidaPorcentaje > 49){setTx(triste);}
	    	else if(vidaPorcentaje < 26) {setTx(enojado);}
    	}
    }
	public int getX() {return (int) spr.getX();}
    public int getY() {return (int) spr.getY();}
    public int getVida() {return vida;}
    public int getMaxVida() {return maxVida;}
    public Sprite getSprite() {return spr;}
    public Texture getTx() {return spr.getTexture();}
    public void setTx(Texture a) {spr.setTexture(a);}
    public boolean isDestruida() {return destruida;}
    public void setDestruida(boolean a) {destruida=a;}
}
