package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;



public class Nave4 {
	
	private boolean destruida = false;
    private int vidas = 13;
    private float xVel = 0;
    private float yVel = 0;
    private Sprite spr;
    private Sound sonidoHerido;
    private Sound soundBala;
    private Texture txBala;
    private boolean herido = false;
    private int tiempoHeridoMax=50;
    private int tiempoHerido;
    private float rotacion = -90;
    
    public Nave4(int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala) {
    	sonidoHerido = soundChoque;
    	this.soundBala = soundBala;
    	this.txBala = txBala;
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
    	
    	spr.setOrigin(spr.getWidth() / 2, spr.getHeight() / 2);
    	
    	//spr.setOriginCenter();
    	spr.setBounds(x, y, 45, 45);
    	
    	spr.setRotation(rotacion);

    }
    
    
    //funcion duplicada para probar rotacion
    public void draw(SpriteBatch batch, PantallaJuego juego) {
        float x = spr.getX();
        float y = spr.getY();

        if (!herido) {
            // Que se mueva con teclado
        	
        	//mover adelante
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                xVel -= 0.5f * MathUtils.sinDeg(rotacion);
                yVel += 0.5f * MathUtils.cosDeg(rotacion);
                //turbo
                if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                    xVel -= 0.5f * MathUtils.sinDeg(rotacion);
                    yVel += 0.5f * MathUtils.cosDeg(rotacion);
                }
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                xVel -= 0.5f * MathUtils.sinDeg(rotacion);
                yVel += 0.5f * MathUtils.cosDeg(rotacion);
                
                //turbo
                if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                    xVel -= 0.5f * MathUtils.sinDeg(rotacion);
                    yVel += 0.5f * MathUtils.cosDeg(rotacion);
                }
            }
            
    
            
            //mover atras
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                xVel += 0.5f * MathUtils.sinDeg(rotacion);
                yVel -= 0.5f * MathUtils.cosDeg(rotacion);
            }
            
            else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                xVel += 0.5f * MathUtils.sinDeg(rotacion);
                yVel -= 0.5f * MathUtils.cosDeg(rotacion);
            }

            // Que rote con teclado
            
            //sentido antihorario
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                rotacion += 4f; // Incrementa la rotación en sentido antihorario
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                rotacion += 4f; // Incrementa la rotación en sentido antihorario
            }
                //sentido horario
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                rotacion -= 4f; // Incrementa la rotación en sentido horario
            }
            
            else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
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

     // Disparo
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            float balaVelocidad = 40; // Velocidad de la bala
            float balaDireccionX = -MathUtils.sinDeg(rotacion); // Componente X de la dirección de la bala (invertido)
            float balaDireccionY = MathUtils.cosDeg(rotacion); // Componente Y de la dirección de la bala

            // Calcular la posición inicial de la bala en el centro de la nave
            float balaInicialX = spr.getX() + spr.getWidth() / 2 - 5;
            float balaInicialY = spr.getY() + spr.getHeight() / 2 - 5;

            Bullet bala = new Bullet(
                balaInicialX,
                balaInicialY,
                balaDireccionX * balaVelocidad,
                balaDireccionY * balaVelocidad,
                txBala
            );
            bala.setVelocity(balaDireccionX * balaVelocidad, balaDireccionY * balaVelocidad);

            // Girar la textura de la bala según la rotación de la nave
            bala.getSprite().setRotation(rotacion);

            juego.agregarBala(bala);
            soundBala.play();
        }

    }
    
    /*
    public void draw(SpriteBatch batch, PantallaJuego juego){
        float x =  spr.getX();
        float y =  spr.getY();
        if (!herido) {
	        // que se mueva con teclado
	        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) xVel--;
	        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) xVel++;
        	if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) yVel--;     
	        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) yVel++;
	        
	        //if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
	        //    rotacion += 2f; // Incrementa la rotación en sentido antihorario
	        //}
	        //if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
	        //    rotacion -= 2f; // Incrementa la rotación en sentido horario
	        //}
	        
	        //spr.setRotation(rotacion);
        	
	       // if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) spr.setRotation(++rotacion);
	       // if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) spr.setRotation(--rotacion);
	        /*
	        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
	        	xVel -=Math.sin(Math.toRadians(rotacion));
	        	yVel +=Math.cos(Math.toRadians(rotacion));
	        	System.out.println(rotacion+" - "+Math.sin(Math.toRadians(rotacion))+" - "+Math.cos(Math.toRadians(rotacion))) ;    
	        }
	        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
	        	xVel +=Math.sin(Math.toRadians(rotacion));
	        	yVel -=Math.cos(Math.toRadians(rotacion));
	        	     
	        }
	        
	        // que se mantenga dentro de los bordes de la ventana
	        if (x+xVel < 0 || x+xVel+spr.getWidth() > Gdx.graphics.getWidth())
	        	xVel*=-1;
	        if (y+yVel < 0 || y+yVel+spr.getHeight() > Gdx.graphics.getHeight())
	        	yVel*=-1;
	        
	        spr.setPosition(x+xVel, y+yVel);   
         
 		    spr.draw(batch);
        } else {
           spr.setX(spr.getX()+MathUtils.random(-2,2));
 		   spr.draw(batch); 
 		  spr.setX(x);
 		   tiempoHerido--;
 		   if (tiempoHerido<=0) herido = false;
 		 }
        // disparo
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {         
          Bullet  bala = new Bullet(spr.getX()+spr.getWidth()/2-5,spr.getY()+ spr.getHeight()-5,0,3,txBala);
	      juego.agregarBala(bala);
	      soundBala.play();
        }
       
    }
      */
    
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
            vidas--;
            herido = true;
  		    tiempoHerido=tiempoHeridoMax;
  		    sonidoHerido.play();
            if (vidas<=0) 
          	    destruida = true; 
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
    public void bordeNave(int xBorde, int yBorde) {
    	float x = spr.getX();
        float y = spr.getY();
       // System.out.println("Antes de las restricciones: x = " + x + ", y = " + y);
        
    	 // Mantener la nave dentro de los bordes del mapa
        if (x < -75)
            x = -75;
        if (x + spr.getWidth() > (xBorde+80))
            x = (xBorde+80) - spr.getWidth();
        
        if (y < 115)
            y = 115;
        if (y + spr.getHeight() > (yBorde-105))
            y = (yBorde-105) - spr.getHeight();
       // System.out.println("Después de las restricciones: x = " + x + ", y = " + y);
        
        spr.setPosition(x, y);
    	
    }
    public int getVidas() {return vidas;}
    public boolean isDestruida() {return destruida;}
    public int getX() {return (int) spr.getX();}
    public int getY() {return (int) spr.getY();}
	public void setVidas(int vidas2) {vidas = vidas2;}
	
}
