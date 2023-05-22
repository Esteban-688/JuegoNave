package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class PantallaJuego implements Screen {

	private SpaceNavigation game;
	private OrthographicCamera camera;	
	private SpriteBatch batch;
	private Sound explosionSound;
	private Music gameMusic;
	private int score;
	private int ronda;
	private int velXAsteroides; 
	private int velYAsteroides; 
	private int cantAsteroides;
	private int ancho = 2000;
	private int alto = 1200;
	
	private Nave4 nave;
	private  ArrayList<Ball2> balls1 = new ArrayList<>();
	private  ArrayList<Ball2> balls2 = new ArrayList<>();
	private  ArrayList<Bullet> balas = new ArrayList<>();


	public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score,  
			int velXAsteroides, int velYAsteroides, int cantAsteroides) {
		this.game = game;
		this.ronda = ronda;
		this.score = score;
		this.velXAsteroides = velXAsteroides;
		this.velYAsteroides = velYAsteroides;
		this.cantAsteroides = cantAsteroides;
		
		batch = game.getBatch();
		camera = new OrthographicCamera();	
		camera.setToOrtho(false, 800, 640);
		//inicializar assets; musica de fondo y efectos de sonido
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,0.5f);
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav")); //
		
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.5f);
		gameMusic.play();
		
	    // cargar imagen de la nave, 64x64   
	    nave = new Nave4((ancho-(ancho/2)), (alto-(alto/2)),new Texture(Gdx.files.internal("MainShip3.png")),
	    				Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")), 
	    				new Texture(Gdx.files.internal("Rocket2.png")), 
	    				Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"))); 
        nave.setVidas(vidas);
        //crear asteroides
        Random r = new Random();
	    for (int i = 0; i < cantAsteroides; i++) {
	        Ball2 bb = new Ball2(r.nextInt((int)Gdx.graphics.getWidth()),
	  	            50+r.nextInt((int)Gdx.graphics.getHeight()-50),
	  	            20+r.nextInt(10), velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4), 
	  	            new Texture(Gdx.files.internal("aGreyMedium4.png")),
	  	            ancho,alto);	   
	  	    balls1.add(bb);
	  	    balls2.add(bb);
	  	    
	  	}
	    //posicionar inicial
	    //camera.position.x = nave.getX();
		//camera.position.y = nave.getY();
		//camera.update();
	    nave.bordeNave(ancho, alto);
	   
	}
	
	public void dibujaEncabezado() {
		CharSequence str = "Vidas: "+nave.getVidas()+" Ronda: "+ronda;
		 int xVida, yVida,xScore,yScore,xHigh,yHigh; 
		game.getFont().getData().setScale(2f);
		
		// x
		xVida = ((int)camera.position.x-370);
		xScore = ((int)camera.position.x+240);
		xHigh = (int)camera.position.x;
			
		//Y

		yVida = ((int)camera.position.y)-280;
		yScore = ((int)camera.position.y)-280;
		yHigh =	((int)camera.position.y)-280;	
		
		
		//colocar en pantalla vidas y ronda
		game.getFont().draw(batch, str,xVida,yVida);		
		game.getFont().draw(batch, "Score:"+this.score, xScore,yScore);
		game.getFont().draw(batch, "HighScore:"+game.getHighScore(), xHigh,yHigh);
		/*
		if(nave.getX() > ancho-320 || nave.getY() > alto-400) {
			game.getFont().draw(batch, str, (nave.getX()-370)-(ancho-nave.getX()), (nave.getY()-280)-(alto-nave.getY()));
			game.getFont().draw(batch, "Score:"+this.score, (nave.getX()+240)-(ancho-nave.getX()), (nave.getY()-280)-(alto-nave.getY()));
			game.getFont().draw(batch, "HighScore:"+game.getHighScore(), nave.getX()-(ancho-nave.getX()), (nave.getY()-280)-(alto-nave.getY()));
		}else {
			game.getFont().draw(batch, str, nave.getX()-370, nave.getY()-280);
			game.getFont().draw(batch, "Score:"+this.score, nave.getX()+240, nave.getY()-280);
			game.getFont().draw(batch, "HighScore:"+game.getHighScore(), nave.getX(), nave.getY()-280);
		}
		*/
	}
    /*
	public void dibujaEncabezado() {
		CharSequence str = "Vidas: "+nave.getVidas()+" Ronda: "+ronda;
		game.getFont().getData().setScale(2f);		
		game.getFont().draw(batch, str, 10, 30);
		game.getFont().draw(batch, "Score:"+this.score, Gdx.graphics.getWidth()-150, 30);
		game.getFont().draw(batch, "HighScore:"+game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
	}
	*/
	private void actualizarCamara() {
		
		//valida derecha y izquierda
		  if (nave.getX() > ancho-320 || nave.getX() < 320) {
			 //no actualiza la camara
		  }else {
			  camera.position.x = nave.getX();
		  }
		  if(nave.getY() > alto-400 || nave.getY() < 400){
			  //no actualiza
		  }
		  else {
			  camera.position.y = nave.getY();
		  }
			camera.update();
			batch.setProjectionMatrix(camera.combined);
	}
	
	@Override
	public void render(float delta) {
		  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		  
		//actualizar movimiento de camara
		  actualizarCamara();
		  
			
			//valida que la nave no salga del limite del mapa
			nave.bordeNave(ancho, alto);
			
			batch.begin();
          
		  dibujaEncabezado();
		  
	      if (!nave.estaHerido()) {
		      // colisiones entre balas y asteroides y su destruccion  
	    	  for (int i = 0; i < balas.size(); i++) {
		            Bullet b = balas.get(i);
		            b.update();
		            for (int j = 0; j < balls1.size(); j++) {    
		              if (b.checkCollision(balls1.get(j))) {          
		            	 explosionSound.play();
		            	 balls1.remove(j);
		            	 balls2.remove(j);
		            	 j--;
		            	 score +=10;
		              }   	  
		  	        }
		                
		         //   b.draw(batch);
		            if (b.isDestroyed()) {
		                balas.remove(b);
		                i--; //para no saltarse 1 tras eliminar del arraylist
		            }
		      }
		      //actualizar movimiento de asteroides dentro del area
		      for (Ball2 ball : balls1) {
		          ball.update();
		      }
		      //colisiones entre asteroides y sus rebotes  
		      for (int i = 0; i < balls1.size();i++) {
		    	Ball2 ball1 = balls1.get(i);   
		        for (int j = 0 ; j < balls2.size();j++) {
		          Ball2 ball2 = balls2.get(j); 
		          if (i < j) {
		        	  ball1.checkCollision(ball2);
		        	  if (ball2.isDestroyed()) {
		        		 balls1.remove(j);
		        		 balls2.remove(j);
		        	  }
		          }
		        }
		      } 
	      }
	      //dibujar balas
	     for (Bullet b : balas) {       
	          b.draw(batch);
	      }
	      nave.draw(batch, this);
	      //dibujar asteroides y manejar colision con nave
	      for (int i = 0; i < balls1.size(); i++) {
	    	    Ball2 b=balls1.get(i);
	    	    b.draw(batch);
		          //perdió vida o game over
	              if (nave.checkCollision(b)) {
		            //asteroide se destruye con el choque             
	            	 balls1.remove(i);
	            	 balls2.remove(i);
	            	 i--;
              }   	  
  	        }
	      
	      if (nave.estaDestruido()) {
  			if (score > game.getHighScore())
  				game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
  			ss.resize(1200, 800);
  			game.setScreen(ss);
  			dispose();
  		  }
	      batch.end();
	      /*nivel completado
	      if (balls1.size()==0) {
			Screen ss = new PantallaJuego(game,ronda+1, nave.getVidas(), score, 
					velXAsteroides+3, velYAsteroides+3, cantAsteroides+10);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		  }
	    	*/ 
	      
	    
	}
    
    public boolean agregarBala(Bullet bb) {
    	return balas.add(bb);
    }
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		gameMusic.play();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.explosionSound.dispose();
		this.gameMusic.dispose();
	}
   
}
