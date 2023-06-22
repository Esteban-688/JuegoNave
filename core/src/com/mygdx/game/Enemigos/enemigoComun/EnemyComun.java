 package com.mygdx.game.Enemigos.enemigoComun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Ball2;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.Enemigos.Enemigo;
import com.mygdx.game.balas.Bullet;
import com.mygdx.game.diccionaInterfaces.Atacar;
import com.mygdx.game.diccionaInterfaces.Moverse;


public class EnemyComun implements Enemigo{
    
	private boolean destruida = false;
	private int vida = 200;
    private Sprite spr;
   
    private Atacar ataque;
    private Moverse moverse;
    
    public EnemyComun(int x, int y, Texture tx, Atacar atacar, Moverse mover) {
        spr = new Sprite(tx);
        spr.setSize(50, 60);
        spr.setPosition(x, y);
        //alto= altoY;
        
       // distancia = xDistancia;
       // this.ySpeed = ySpeed;
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

    public boolean checkCollision(Ball2 ball) {
        
        return true;
    }
    public boolean checkCollision(Bullet bala) {
    	
        if(bala.getMia()) {
            if (bala.getSprite().getBoundingRectangle().overlaps(spr.getBoundingRectangle())) {
                vida-=bala.getDaño();
                if (vida <= 0) {
                    destruida = true;
                }
                return true;
            }
        
    }
    return false;
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
    public boolean isDestruida() {return destruida;}
}
