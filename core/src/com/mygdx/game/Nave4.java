package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Enemigos.boss.BossFinal;
import com.mygdx.game.balas.BalaEspecial;
import com.mygdx.game.balas.BalaNormal;
import com.mygdx.game.balas.Bullet;


public class Nave4 {
	
	private boolean destruida = false;
    private int vida = 30000;
    private float xVel = 0;
    private float yVel = 0;
    private Sprite spr;
    private Sound sonidoHerido;
    private Sound soundBala;
    private Sound soundBalaEspecial;
    private Texture txBalaNormal;
    private Texture txBalaEspecial;
    private boolean herido = false;
    private int tiempoHeridoMax=50;
    private int tiempoHerido;
    private float rotacion = -90;
    private boolean disparoEspecial = true;
    private float tiempo = 0.0f;
    
    
    public Nave4(int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala,Texture txBalaEspecial, Sound soundBalaEspecial) {
    	sonidoHerido = soundChoque;
    	this.soundBala = soundBala;
    	txBalaNormal = txBala;
    	
    	this.txBalaEspecial = txBalaEspecial;
    	this.soundBalaEspecial = soundBalaEspecial;
    	
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
    	
    	spr.setOrigin(spr.getWidth() / 2, spr.getHeight() / 2);
    	
    	//spr.setOriginCenter();
    	spr.setBounds(x, y, 45, 45);
    	
    	spr.setRotation(rotacion);

    }
    
    
    
    public void draw(SpriteBatch batch, PantallaJuego juego) {
      
    	float x = spr.getX();
        float y = spr.getY();
        
       

        if (!herido) {
            // Que se mueva con teclado
        	
        	//mover adelante
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
                xVel -= 0.5f * MathUtils.sinDeg(rotacion);
                yVel += 0.5f * MathUtils.cosDeg(rotacion);
                //turbo
                if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                    xVel -= 0.5f * MathUtils.sinDeg(rotacion);
                    yVel += 0.5f * MathUtils.cosDeg(rotacion);
                }
            }
            
            
            //mover atras
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
                xVel += 0.5f * MathUtils.sinDeg(rotacion);
                yVel -= 0.5f * MathUtils.cosDeg(rotacion);
            }

            // Que rote con teclado
            
            //sentido antihorario
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.A)) {
                rotacion += 4f; // Incrementa la rotación en sentido antihorario
            }
                //sentido horario
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)||Gdx.input.isKeyPressed(Input.Keys.D)) {
                rotacion -= 4f; // Incrementa la rotación en sentido horario
            }
            // Disminuir gradualmente la velocidad de desplazamiento
            float reduccionVelocidad = 0.95f; // Factor de reducción
            xVel *= reduccionVelocidad;
            yVel *= reduccionVelocidad;

            // Actualizar la posición de la nave en función de los componentes de velocidad
            x += xVel; //
            y += yVel; //

         /*
            // Mantener la nave dentro de los bordes de la ventana
            if (x < 0)
                x = 0;
            if (x + spr.getWidth() > Gdx.graphics.getWidth())
                x = Gdx.graphics.getWidth() - spr.getWidth();
            if (y < 0)
                y = 0;
            if (y + spr.getHeight() > Gdx.graphics.getHeight())
                y = Gdx.graphics.getHeight() - spr.getHeight();
         */
            spr.setPosition(x, y);
            spr.setRotation(rotacion); // Establecer la rotación de la nave
            spr.draw(batch);
        } else {
            spr.setX(spr.getX() + MathUtils.random(-2, 2));
            spr.draw(batch);
            spr.setX(x);
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }
        
        
         // Disparos 
        
		if (disparoEspecial && Gdx.input.isKeyPressed(Input.Keys.E)) {
		        // Disparar bala especial
		         disparoEspecial = false;
				
		        // Calcular la posición inicial de la bala en el centro de la nave
	            float balaInicialX = spr.getX()+25;//  + spr.getWidth() / 2 - 5;
	            float balaInicialY = spr.getY()+15;// + spr.getHeight() / 2- 5;
	        	    
	            BalaEspecial balaEspecial = new BalaEspecial(
	                balaInicialX,
	                balaInicialY,
	                txBalaEspecial
	            );
	
	            juego.agregarBala(balaEspecial);
	            soundBalaEspecial.play();
	            
	            
	            
		}
		
		//tiempo de espera antes de otro disparo especial;
		if(disparoEspecial == false) {
			tiempo += Gdx.graphics.getDeltaTime();
			
			if(tiempo >= 15) {
				disparoEspecial = true;
				tiempo = 0.0f;
			}
		}
			
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
		
			//disparo normal 
			 
		    float balaVelocidad = 40; // Velocidad de la bala
            float balaDireccionX = -MathUtils.sinDeg(rotacion); 
            float balaDireccionY = MathUtils.cosDeg(rotacion); 

            // Calcular la posición inicial de la bala en el centro de la nave
            float balaInicialX = spr.getX() + spr.getWidth() / 2 - 5;
            float balaInicialY = spr.getY() + spr.getHeight() / 2 - 5;

            BalaNormal bala = new BalaNormal(
                balaInicialX,
                balaInicialY,
                balaDireccionX * balaVelocidad,
                balaDireccionY * balaVelocidad,
                txBalaNormal,
                true
            );
            
            bala.setVelocity(balaDireccionX * balaVelocidad, balaDireccionY * balaVelocidad);

            // Girar la textura de la bala según la rotación de la nave
            bala.getSprite().setRotation(rotacion);

            juego.agregarBala(bala);
            soundBala.play();
			}
		
			
		}
		   
    
    public boolean checkCollision(Ball2 b) {
        if(!herido && b.getArea().overlaps(spr.getBoundingRectangle())){
        	// rebote
            if (xVel ==0) xVel += b.getXSpeed()/2;
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
    
    public boolean checkCollision(Bullet balaa) {
    	
        if (balaa instanceof BalaNormal) {
        	
            BalaNormal bala = (BalaNormal) balaa;
            if(!bala.getMia()) {
	            if (!herido && bala.getSprite().getBoundingRectangle().overlaps(spr.getBoundingRectangle())) {
	                vida-=300;
	                //herido = true;
	                tiempoHerido = tiempoHeridoMax;
	                sonidoHerido.play();
	                if (vida <= 0) {
	                    destruida = true;
	                }
	                return true;
	            }
            }
        }
        return false;
    }
    public boolean checkCollision(BossFinal boss) {
    	
    	 if (!herido && boss.getSprite().getBoundingRectangle().overlaps(spr.getBoundingRectangle())) {
    		// vida -=500;
             //herido = true;
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
	
}
