package com.mygdx.game.navecita;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Ball2;
import com.mygdx.game.PantallaJuego;
import com.mygdx.game.Enemigos.boss.BossFinal;
import com.mygdx.game.balas.Bullet;


public class Nave4 implements Nave{
	
	private boolean destruida = false;
    private int vida;
    private Sprite spr;
    private Sound sonidoHerido;
    private boolean herido = false;
    private int tiempoHeridoMax=50;
    private int tiempoHerido;
    private float rotacion = -90;
    private AtacarNave ataque;
	private MoverNave mover;
    
    public Nave4(int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala,Texture txBalaEspecial, Sound soundBalaEspecial) {
    	
    	vida = 50000;
    	
    	sonidoHerido = soundChoque;
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
    	
    	spr.setOrigin(spr.getWidth() / 2, spr.getHeight() / 2);
    	
    	//spr.setOriginCenter();
    	spr.setBounds(x, y, 45, 45);
    	
    	spr.setRotation(rotacion);
    	
    	ataque= new AtacarNave(spr,txBala, soundBala, txBalaEspecial, soundBalaEspecial);
    	
    	mover = new MoverNave(spr);
    	
    }
    
    
    
    public void draw(SpriteBatch batch, PantallaJuego juego) {
      
    	spr.draw(batch);
    	
        moverse();
    	atacar(batch, juego);
        
		}
	
    public void atacar(SpriteBatch batch, PantallaJuego juego) {
    	ataque.atacar(batch, juego, mover.getRotacion());
    }
    
    public void moverse() {

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
        	/* rebote
            if (xVel == 0) xVel += b.getXSpeed()/2;
            if (b.getXSpeed() ==0) b.setXSpeed(b.getXSpeed() + (int)xVel/2);
            xVel = - xVel;
            b.setXSpeed(-b.getXSpeed());
            
            if (yVel ==0) yVel += b.getySpeed()/2;
            if (b.getySpeed() ==0) b.setySpeed(b.getySpeed() + (int)yVel/2);
            yVel = - yVel;
            b.setySpeed(- b.getySpeed());
            // despegar sprites
      /*      int cont = 0;
            while (b.getArea().overlaps(spr.getBoundingRectangle()) && cont<xVel) {
               spr.setX(spr.getX()+Math.signum(xVel));
            }   */
        	//actualizar vidas y herir
            vida-=100;
            herido = true;
  		    tiempoHerido=tiempoHeridoMax;
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
	                //herido = true;
	                tiempoHerido = tiempoHeridoMax;
	                sonidoHerido.play();
	                if (vida <= 0) {
	                    destruida = true;
	                }
	                return true;
	            }
            }
        return false;
    }
    public boolean checkCollision(BossFinal boss) {
    	
    	 if (!herido && boss.getSprite().getBoundingRectangle().overlaps(spr.getBoundingRectangle())) {
    		 vida -= 50;
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
	
}
