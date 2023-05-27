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
	private int ancho = 20000;//eje x
	private int alto = 1200;//eje y
	private int anchoCamara = 800;
	private int altoCamara = 640;
	private int porcentaje = 0;
	private float tiempoTotal = 0.0f;
	private float tiempoTotal1 = 0.0f;
	private boolean barreraBoolean = false;
	private int barrera = 0;
	private Nave4 nave;
	private int contadorDeEnemigos = 0;
	private EnemyComun enemigoComun;
	//private EnemyComun enemigoComun, enemigoComun1, enemigoComun2;
	
	private float velocidadEnemigo = 5;
	
	//private boolean vd = true;
	
	private EarthMap mapa;
	private  ArrayList<Ball2> balls1 = new ArrayList<>();
	private  ArrayList<Ball2> balls2 = new ArrayList<>();
	private  ArrayList<Bullet> balas = new ArrayList<>();
	private  ArrayList<EnemyComun> enemigos = new ArrayList<>();
	

	public PantallaJuego(SpaceNavigation game, int ronda, int vida, int score,  
			int velXAsteroides, int velYAsteroides, int cantAsteroides) {
		
		this.game = game;
		this.ronda = ronda;
		this.score = score;
		this.velXAsteroides = velXAsteroides;
		this.velYAsteroides = velYAsteroides;
		this.cantAsteroides = cantAsteroides;
		
		batch = game.getBatch();
		camera = new OrthographicCamera();	
		
		camera.setToOrtho(false, anchoCamara, altoCamara);
		//inicializar assets; musica de fondo y efectos de sonido
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,0.5f);
		
		//gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav")); //
		
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("musicaFondo.mp3")); 
		
		gameMusic.setLooping(true);
		gameMusic.setVolume(10f);
		gameMusic.play();
		
	    // cargar imagen de la nave, 64x64   
	    nave = new Nave4(((500+ancho)-ancho),
	    				(alto-(alto/2)),
	    				//new Texture(Gdx.files.internal("MainShip3.png")),
	    				new Texture(Gdx.files.internal("naveMala1.png")),
	    				Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")), 
	    				new Texture(Gdx.files.internal("Rocket2.png")), //bala normal
	    				Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")),//sonido bala normal
	    				new Texture(Gdx.files.internal("anilloEspecial.png")),//bala especial
	    				Gdx.audio.newSound(Gdx.files.internal("soundBalaespecial.mp3"))//sonido bala especial
	    				); 
        nave.setVida(vida);
        
        				
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
	    //EarthMap mapa = new EarthMap();
	    //posicionar inicial
	    //camera.position.x = nave.getX();
		//camera.position.y = nave.getY();
		//camera.update();
	    //nave.bordeNave(0,ancho, alto);
	   
	}
	
	//accesorios
	private void dibujaEncabezado() {
		CharSequence str = "Vida: "+ nave.getVidas()+" Nivel: "+ronda;
		 int xVida, yVida,xScore,yScore,xHigh,yHigh,xPorcentaje, yPorcentaje; 
		game.getFont().getData().setScale(2f);
		
		// x
		xVida = ((int)camera.position.x-370);
		xScore = ((int)camera.position.x+240);
		xHigh = (int)camera.position.x;
		xPorcentaje = ((int)camera.position.x)-40;	
		//Y

		yVida = ((int)camera.position.y)-280;
		yScore = ((int)camera.position.y)-280;
		yHigh =	((int)camera.position.y)-280;	
		yPorcentaje =((int)camera.position.y)+290;
		
		//colocar en pantalla vidas y ronda
		game.getFont().draw(batch, str,xVida,yVida);		
		game.getFont().draw(batch, "Score:"+this.score, xScore,yScore);
		game.getFont().draw(batch, "HighScore:"+game.getHighScore(), xHigh,yHigh);
		game.getFont().draw(batch, "] "+ porcentaje + "%", xPorcentaje+ 230, yPorcentaje);
		// Barra de progreso
		
		int longitudBarra = 62;
		int longitudRelleno = (int) (porcentaje / 100f * longitudBarra);
		
	    String barraProgreso = "";
	    for (int i = 0; i < longitudBarra; i++) {
	        if (i < longitudRelleno) {
	            barraProgreso += "|"; 
	        } else {
	            barraProgreso += ""; 
	        }
	    }

	    // Dibujar la barra de progreso
	    game.getFont().draw(batch, "[" + barraProgreso , xPorcentaje-150, yPorcentaje);
	}
  
	
	//camara moviendose
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
			porcentaje = (int) ((nave.getX() / (float) ancho) * 100);
			batch.setProjectionMatrix(camera.combined);
			//System.out.println(": x = " + nave.getX() + ", y = " + nave.getY());
	}
	
	private void porcentajeDeBarera() {
		 if(porcentaje <= 94) {
				nave.bordeNave(0, ancho, alto);
				
			  }
			 
			  //medio nivel
		      if(porcentaje > 94) {
		    	  
		    	  if(barreraBoolean == false) {
		    		  barreraBoolean = true;
		    		  barrera = nave.getX();
		    	  }
		    	  nave.bordeNave(barrera + 100 , ancho, alto); 
		    	  if(contadorDeEnemigos > 14) {
		    		  //createBossFinal();
		    		  //boss.atacar();
		    	  }
		      }
	}

	private void gameOver() {
		if (nave.estaDestruido()) {
  			if (score > game.getHighScore())
  				game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
  			ss.resize(1200, 800);
  			game.setScreen(ss);
  			dispose();
  		  }
	}

	private void noEstaHeridoLaNave() {
		if (!nave.estaHerido()) {
		      // colisiones entre balas y asteroides y su destruccion  
	    	  for (int i = 0; i < balas.size(); i++) {
		            Bullet b = balas.get(i);
		            
		            
		            for(int f = 0; f < enemigos.size(); f++) {
		            	EnemyComun z= enemigos.get(f);
		            	z.checkCollision(balas.get(i));
		            	if(z.isDestruida()){
		            		enemigos.remove(z);
		            		System.out.println("hola");
		            	}
		            }
		            
		            if(nave.checkCollision(balas.get(i))){
		            	balas.remove(i);
		            }
		            
		            b.update((int)camera.position.x,(int)camera.position.y, anchoCamara, altoCamara, nave.getX()+25, nave.getY()+15);
		            for (int j = 0; j < balls1.size(); j++) {    
		 
		            	  if (b.checkCollision(balls1.get(j)) || nave.checkCollision(balls1.get(j))) {

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
		          ball.update(ancho, alto);
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
	}
	
	private void enemigoComunDraw() {
		
		for(int x = 0; x < enemigos.size(); x++) {
  		  EnemyComun nuevo = enemigos.get(x);
  		  nuevo.draw(batch);
  		  nuevo.atacar(batch, this);
  	  }
	}

	private void colisioNaveAsteroide() {
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
	}

	private void createEnemigo() {
		tiempoTotal1 += Gdx.graphics.getDeltaTime();
		
		if(tiempoTotal1 > 3 && enemigos.size()< 10 && contadorDeEnemigos <= 15) {
			tiempoTotal1= 0.0f;
			enemigoComun = new EnemyComun (ancho/2,//x
					alto/2,//y
					300,
					velocidadEnemigo,
					new Texture(Gdx.files.internal("MainShip3.png")),//textura
					nave,//nave
					new Texture(Gdx.files.internal("Rocket2.png")),//bala
					Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")),//sonido
					alto// amplitud
					);
			//añadir
			enemigos.add(enemigoComun);
			contadorDeEnemigos ++;
		}
    }
	
	@Override
	public void render(float delta) {
		 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Actualizar movimiento de la cámara
	    actualizarCamara();

	    // Dibujar el fondo del espacio
	    

	    // Iniciar el dibujado de los elementos
	    batch.begin();

	    //mapa.render();//funcion que dibuja el fondo
	    
	    // Dibujar el encabezado
	    dibujaEncabezado();
	    
		//valida que la nave no salga del limite del mapa 
	    porcentajeDeBarera();
		  
	    
	    //si no esta herido comprueba colisiones de asteroides y balas, como su destruccion
	    noEstaHeridoLaNave();
	      
	      //dibujar balas
	     for (Bullet bala : balas) {       
	          bala.draw(batch);
	      }
	     
	     //dibujar y atacar de enemigo comun
	     	createEnemigo();
	     	enemigoComunDraw();
	    	 
	      nave.draw(batch, this);
	      //dibujar asteroides y manejar colision con nave
	      colisioNaveAsteroide();
		      
	      //Cuando se acaben las vidas que hacer
	      gameOver();
	      
	      batch.end();
	      
	      //nivel completado
	       
	      if(porcentaje >= 100 && enemigos.size() == 0) {
	    	  Screen ss = new PantallaMenu(game);
	    	  ss.resize(1200, 800);
	    	  game.setScreen(ss);
	    	  dispose();
	      }
	       
	}
    
    public boolean agregarBala(Bullet bb) {
    	return balas.add(bb);
    }
	
	@Override
	/*public void show() {
		// TODO Auto-generated method stub
		gameMusic.play();
	}*/
	public void show() {
        // Inicializar la instancia de EarthMap
        mapa = new EarthMap();
        mapa.create();
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
