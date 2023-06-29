 package com.mygdx.game.Enemigos.enemigoComun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.Enemigos.Enemigo;
import com.mygdx.game.balas.Bullet;
import com.mygdx.game.diccionaInterfaces.Atacar;
import com.mygdx.game.diccionaInterfaces.Moverse;


public class EnemyComun implements Enemigo{
    
	private boolean destruida = false;
	private int vida;
    private Sprite spr;
   
    private Atacar ataque;
    private Moverse moverse;
    
    public EnemyComun(int x, int y, Texture tx, Atacar atacar, Moverse mover) {
        spr = new Sprite(tx);
        spr.setSize(50, 60);
        spr.setPosition(x, y);
        vida = 200;
        spr.setRotation(90);
        ataque = atacar;
        moverse = mover;
    }
    
    public void draw(SpriteBatch batch, PantallaJuego juego) {
    	// Dibujar el sprite del enemigo
        spr.draw(batch);
        moverse(spr);
        atacar(batch, juego, spr);
    }
    public void atacar(SpriteBatch batch, PantallaJuego juego, Sprite spr) {
    	
    	ataque.atacar(batch, juego, spr);
    
    }

    public void moverse(Sprite spr) {  	
       moverse.moverse(spr);
    	
    }
    
    public void destruirTodo() {
    	destruida = true;
    }

    public int getX() {
        return (int) spr.getX();
    }

    public int getY() {
        return (int) spr.getY();
    }
    public Sprite getSpr(){return spr;}
    public int getVida(){return vida;}
    public void restarVida(int a){vida-=a;}
    public boolean isDestruida() {return destruida;}
    public void setDestruida(boolean a){destruida=a;}
}
