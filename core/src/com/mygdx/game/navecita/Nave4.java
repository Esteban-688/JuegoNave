package com.mygdx.game.navecita;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Ball2;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.Enemigos.boss.BossFinal;
import com.mygdx.game.balas.Bullet;


public class Nave4 implements Nave{
	
	private boolean destruida;
    private int vida;
    private Sprite spr;
    private Sound sonidoHerido;
    private boolean herido;
    private int tiempoHeridoMax = 15;
    private int tiempoHerido;
    private float rotacion;
    private AtacarNave ataque;
	private MoverNave mover;
    
    public Nave4(int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala,Texture txBalaEspecial, Sound soundBalaEspecial) {
    	
    	vida = 50000;
    	destruida = false;
    	herido = false;
    	sonidoHerido = soundChoque;
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
    	rotacion = -90;
    	spr.setOrigin(spr.getWidth() / 2, spr.getHeight() / 2);
    	
    	//spr.setOriginCenter();
    	spr.setBounds(x, y, 45, 45);
    	
    	spr.setRotation(rotacion);
    	
    	ataque= new AtacarNave(spr,txBala, soundBala, txBalaEspecial, soundBalaEspecial);
    	
    	mover = new MoverNave(spr);
    	
    }
    
    public void inicio(int x, int y) {
    	vida = 50000;
    	spr.setPosition(x, y);
    	spr.setOrigin(spr.getWidth() / 2, spr.getHeight() / 2);
    	spr.setBounds(x, y, 45, 45);
    	spr.setRotation(rotacion);
    	destruida = false;
    	 herido = false;
    	 rotacion = -90;
    	
    }
    
    public void draw(SpriteBatch batch, PantallaJuego juego) {
      
    	spr.draw(batch);
    	
        moverse(spr);
    	atacar(batch, juego,spr);
        
		}
	
    public void atacar(SpriteBatch batch, PantallaJuego juego, Sprite spr) {
    	ataque.atacar(batch, juego, mover.getRotacion());
    }
    
    public void moverse(Sprite sprite) {

    	if (!herido) {
    		mover.mover();
        } else {
    	        spr.setX(spr.getX() + MathUtils.random(-2, 2));
    	        //spr.draw(batch);
    	        spr.setX(spr.getX());
    	        tiempoHerido--;
    	        if (tiempoHerido <= 0) herido = false;
    	    }
    	
    }
    
    public void inmune() {
    	
    }
    
    public boolean checkCollision(Ball2 b) {
        if(!herido && b.getArea().overlaps(spr.getBoundingRectangle())){
            vida-=100;
            herido = true;
  		    tiempoHerido = tiempoHeridoMax;
  		    sonidoHerido.play();
            if (vida<=0) 
          	    destruida = true; 
            return true;
        }
        return false;
    }
    
    public boolean checkCollision(Bullet bala) {
            if(!bala.getMia()) {
	            if (!herido && bala.getSprite().getBoundingRectangle().overlaps(spr.getBoundingRectangle())) {
	                vida-=bala.getDaño();
	                sonidoHerido.play();
	                if (vida <= 0) {
	                    destruida = true;
	                }
	                return true;
	            }
            }
        return false;
    }
    public boolean checkCollision(BossFinal boss, Camera camera) {
    	
    	 if (!herido && boss.getSprite().getBoundingRectangle().overlaps(spr.getBoundingRectangle())) {
    		 vida -= 300;
    		 herido = true;
    		 spr.setPosition(camera.position.x-250,camera.position.y);
    		 boss.setPosition(getX()+500, getY());
   		    tiempoHerido = tiempoHeridoMax;
             sonidoHerido.play();
             if (vida <= 0) {
                 destruida = true;
             }
             return true;
    	 }
    	 
    	return false;
    }
    
    public boolean estaDestruido() {
       return !herido && destruida;
    }
    public boolean estaHerido() {
 	   return herido;
    }
    
    public void bordeNave(int xBordeIzq, int xBordeDer, int yBordeUp, int yBordeDown) {
    	
    	float x = spr.getX();
        float y = spr.getY();
       
        
    	 // Mantener la nave dentro de los bordes del mapa
        if (x < xBordeIzq - 75)
            x = xBordeIzq - 75;
        
        if (x + spr.getWidth() > (xBordeDer+80))
            x = (xBordeDer+80) - spr.getWidth();
        
        if (y < yBordeDown + 115)
            y = yBordeDown + 115;
        
        if (y + spr.getHeight() > (yBordeUp-105))
            y = (yBordeUp-105) - spr.getHeight();
       
        
        spr.setPosition(x, y);
        
    	
    }
    public int getVidas() {return vida;}
    public boolean isDestruida() {return destruida;}
    public int getX() {return (int) spr.getX();}
    public int getY() {return (int) spr.getY();}
	public void dañoVida(int vida2) {vida = vida-vida2;}
	public void setPosition(float x, float y) {spr.setPosition(x, y);}
	
	public void setTx(Texture skin) {
		 spr.setTexture(skin);
	}
	
}
