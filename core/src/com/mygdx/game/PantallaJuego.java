package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Enemigos.boss.AtaqueRecto;
import com.mygdx.game.Enemigos.boss.AtaqueSpike;
import com.mygdx.game.Enemigos.boss.BossEstrategy;
import com.mygdx.game.Enemigos.boss.BossFinal;
import com.mygdx.game.Enemigos.enemigoComun.ActionCompany;
import com.mygdx.game.Enemigos.enemigoComun.ArtilleroManuFacturer;
import com.mygdx.game.Enemigos.enemigoComun.EnemyComun;
import com.mygdx.game.Enemigos.enemigoComun.LanzamisilesManuFacturer;
import com.mygdx.game.balas.Bullet;
import com.mygdx.game.diccionaInterfaces.Atacar;
import com.mygdx.game.diccionaInterfaces.Moverse;
import com.mygdx.game.navecita.Nave4;


public class PantallaJuego implements Screen {

	private SpaceNavigation game;
	private OrthographicCamera camera, originalCamera;	
	private OrthographicCamera secondCamera;
	
	private EarthMap earthMap;
	
	private BossEstrategy ataqueEstrategy;

	private SpriteBatch batch;
	private Sound explosionSound;
	private Music gameMusic, battleMusic;
	private int velXAsteroides, velYAsteroides, cantAsteroides;
	private int ancho;
	private int alto;
	private int anchoCamara;
	private int altoCamara;
	private int porcentaje = 0;
	private float tiempoTotal1 = 0.0f;
	private boolean barreraBoolean = false;
	private int barreraX = 0, barreraY = 0;
	private Nave4 nave;
	private int contadorDeEnemigos = 0;
	private int contadorDeKill = 0;
	private EnemyComun enemigoComun;
	private BossFinal boss;
	private boolean bossActivado = false;
	private boolean bossMuerto = false;
	
	
	private ActionCompany lanzamisiles, artillero;
	private Atacar ataqueLanzamisiles, ataqueArtillero;
	private Moverse movimientoLanzamisiles, movimientoArtillero;
	
	
	private  ArrayList<Ball2> balls1 = new ArrayList<>();
	private  ArrayList<Ball2> balls2 = new ArrayList<>();
	private  ArrayList<Bullet> balas = new ArrayList<>();
	private  ArrayList<EnemyComun> enemigos = new ArrayList<>();
	

	public PantallaJuego( SpaceNavigation game, int velXAsteroides, int velYAsteroides, int cantAsteroides ){
		
		
		ancho = Config.getDer();
		alto = Config.getUp();
		
		this.game = game;
		this.velXAsteroides = velXAsteroides;
		this.velYAsteroides = velYAsteroides;
		this.cantAsteroides = cantAsteroides;
		
		anchoCamara = 800;
		altoCamara = 640;
		
		batch = game.getBatch();
		
		//camaras
		camera = new OrthographicCamera();	
		camera.setToOrtho(false, anchoCamara, altoCamara);
		originalCamera = camera;
		secondCamera = new OrthographicCamera();
		secondCamera.setToOrtho(false, 1800, 1600);

		//cargar mapa
		earthMap = EarthMap.getInstance();
		earthMap.iniciar();
		earthMap.createStars(ancho, alto);
		
		//inicializar assets; musica de fondo y efectos de sonido
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,0.5f);
		
		//gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav")); //
		battleMusic = Gdx.audio.newMusic (Gdx.files.internal("Sound-battle.mp3"));
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("musicaFondo.mp3")); 
		
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.6f);
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
       Config.setnave(nave);
        
	    
	  //crear y cargar boss
	    boss = new BossFinal(ancho,
        		alto/2,
        		6,
        		new Texture(Gdx.files.internal("MiniBossMarciano.png")),
        		new Texture(Gdx.files.internal("MiniBossMarcianoHerido.png")),
        		nave
        		);
        
        //crear asteroides
       /* Random r = new Random();
	    for (int i = 0; i < cantAsteroides; i++) {
	        Ball2 bb = new Ball2(r.nextInt((int)Gdx.graphics.getWidth()),
	  	            50+r.nextInt((int)Gdx.graphics.getHeight()-50),
	  	            20+r.nextInt(10), velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4), 
	  	            new Texture(Gdx.files.internal("aGreyMedium4.png")),
	  	            ancho,alto);	   
	  	    balls1.add(bb);
	  	    balls2.add(bb);
	  	    
	  	}*/
	   
	   
	}
	
	//accesorios
	private void dibujaEncabezado() {
		CharSequence str = "Vida: "+ nave.getVidas()+" Nivel: "+ 1;
		 int xVida, yVida,xVidaBoss,yVidaBoss,xKill,yKill,xPorcentaje, yPorcentaje; 
		game.getFont().getData().setScale(2f);
		
		// x
		xVida = ((int)camera.position.x-370);
		xVidaBoss = ((int)camera.position.x+240);
		xKill = (int)camera.position.x;
		xPorcentaje = ((int)camera.position.x)-40;	
		//Y

		yVida = ((int)camera.position.y)-280;
		yVidaBoss = ((int)camera.position.y)-280;
		yKill =	((int)camera.position.y)-280;	
		yPorcentaje =((int)camera.position.y)+290;
		
		//colocar en pantalla vidas y ronda
		game.getFont().draw(batch, str,xVida,yVida);
		game.getFont().draw(batch, "kill: "+contadorDeKill, xKill,yKill);
		game.getFont().draw(batch, "] "+ porcentaje + "%", xPorcentaje+ 230, yPorcentaje);
		
		if(bossActivado && !bossMuerto)game.getFont().draw(batch, " Jefe: "+(int)boss.getVidaPorcentaje()+" %", xVidaBoss-100,yVidaBoss);
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
		  }else if(bossActivado){//eje x
			  if(bossMuerto) {
				  camera.position.x = nave.getX();
			  }
		  }
			  else {
			  camera.position.x = nave.getX();
		  }
	
	
		  if(nave.getY() > alto-400 || nave.getY() < 400){
			  //no actualiza
		  }
		  else if(bossActivado){//eje Y
			  if(bossMuerto) {
				  camera.position.y = nave.getY();
			  }
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
		 
		//inicio
		if(porcentaje <= 90 && !bossActivado) {
				nave.bordeNave(0, ancho, alto, 0);
			//	System.out.println("principio");
				//medio nivel
	    }else if(porcentaje > 90  && !bossActivado) {
		    	  
		    	  if(!barreraBoolean) {
		    		  
		    		  barreraBoolean = true;
		    		  barreraX = nave.getX();
		    		  //barreraY = nave.getY();
		    	  }
		    	  
		    	  nave.bordeNave(barreraX + 290 , barreraX + 500, alto, 0);
		    	  //System.out.println("medio");
		    	  if(contadorDeKill >= 3) bossActivado=true;
		    
		    	  
		      }else  if(bossActivado) {
		    	  
		    	  
		    		
		    		 System.out.println(" x "+nave.getX() +" y "+ nave.getY());
		    		 
		    		 
						if(barreraBoolean) {//solo sucede una vez
						  nave.setPosition(ancho-500 ,alto/2);
						  camera.position.x = ancho-500;
						  camera.position.y = alto/2;
			    		  barreraBoolean = false;
			    		  barreraX = ancho-500;
			    		  barreraY = alto/2;
			    	        
			    		  //barrera boss
			    		  boss.setBarreraBoss(barreraX - 380, barreraX + 250, barreraY -305 , barreraY + 350);
			    		 
			    		  gameMusic.pause();
			    		  battleMusic.setLooping(true);
			    		  battleMusic.setVolume(1.0f);
			    		  battleMusic.play();
			    		  
			    		  	//limpiar enemigos
				    		  for(int x = 0; x < enemigos.size(); x++) {
				    	  		  enemigos.get(x).destruirTodo();
				    	  		  
				    	  	  }
			    		  }
					
		    		  if(!boss.isDestruida()) {
		    			  boss.draw(batch,this);
			    		 
			    		 nave.bordeNave(barreraX-305, barreraX +290, barreraY + 390 , barreraY -405);
			    		 
			    		
		    		  }
		    		  else {
		    			 // Screen ss = new PantallaMenu(game, 800, 600);
		    	    	  //ss.resize(1200, 800);
		    	    	  //game.setScreen(ss);
		    	    	  //dispose();
		    			  if(!bossMuerto) {
		    				  	battleMusic.stop();
		    				  	gameMusic.setLooping(true);
		    					gameMusic.setVolume(0.6f);
		    					gameMusic.play();
		    					bossMuerto = true;
		    			  }
		    			  nave.bordeNave(barreraX -280  , barreraX +880 , barreraY + 400 , barreraY -400);
		    			  
		    			  
		    		  }
		    		   if(porcentaje >= 100  && bossMuerto) {
		    	    	  Screen ss = new PantallaMenu(game, 800, 600);
		    	    	 // ss.resize(1200, 800);
		    	    	  game.setScreen(ss);
		    	    	  dispose();
		    	      
		    		  }
		    		  //System.out.println("bossFinal");
		    	  }//else if(!bossActivado) {
		    		 // nave.bordeNave(0, ancho, alto, 0);
		    	  //}
		    		 
		      }
	

	private void gameOver() {
		if (nave.estaDestruido()) {
  			//if (score > game.getHighScore())
  				//game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
  			ss.resize(1200, 800);
  			game.setScreen(ss);
  			dispose();
  		  }
	}

	//colisiones
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
		            		contadorDeKill ++;
		            		//System.out.println("hola");
		            	}
		            }
		            
		           //colision boss
		            boss.checkCollision(balas.get(i));
		            nave.checkCollision(boss);
		            
		            if(nave.checkCollision(balas.get(i))){
		            	balas.remove(i);
		            	if(bossActivado){
		            		nave.dañoVida(1000);
		            	}
		            }
		            
		            b.update((int)camera.position.x,(int)camera.position.y, anchoCamara, altoCamara, nave.getX()+25, nave.getY()+15);
		            for (int j = 0; j < balls1.size(); j++) {    
		 
		            	  if (b.checkCollision(balls1.get(j)) || nave.checkCollision(balls1.get(j))) {

		            	 explosionSound.play();
		            	 balls1.remove(j);
		            	 balls2.remove(j);
		            	 j--;
		            	 
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
  		  nuevo.draw(batch, this);
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
		
		
		//uso abstract factory
		lanzamisiles = new LanzamisilesManuFacturer();//company
		ataqueLanzamisiles = lanzamisiles.createAtaque();//atacar
		movimientoLanzamisiles = lanzamisiles.createMovimiento();//moverse
		
		artillero = new ArtilleroManuFacturer();
		ataqueArtillero = artillero.createAtaque();
		movimientoArtillero = artillero.createMovimiento();
		
		if(tiempoTotal1 > 2 && enemigos.size()< 5 && contadorDeEnemigos <= 100 && !bossActivado ) {
			
			tiempoTotal1= 0.0f;
			
			int randomNumber = numeroRandom.generateRandomNumber();
			
			if(randomNumber == 1) {
				
			enemigoComun = new EnemyComun (nave.getX()+200,//x
					alto/2,//
					new Texture(Gdx.files.internal("TxLanzamisilesNave.png")),
					ataqueLanzamisiles,//tipo de ataque
					movimientoLanzamisiles//tipo de moviento
					);
			}
			else if(randomNumber == 2) {
				enemigoComun = new EnemyComun (nave.getX()-300,//x
						alto/2,
						new Texture(Gdx.files.internal("TxArtilleroNave.png")),
						ataqueArtillero,//tipo de ataque
						movimientoArtillero//tipo de movimiento
						);
			}
			//añadir 
			if(enemigoComun != null) {
			enemigos.add(enemigoComun);
			contadorDeEnemigos ++;
			}
		}
    }
	
	@Override
	public void render(float delta) {
	    Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	  //  batch.setProjectionMatrix(camera.combined);

	    // Actualizar movimiento de la cámara
	    actualizarCamara();

	    // Iniciar el dibujado de los elementos
	    batch.begin();
	    
	    // Dibujar el mapa de la Tierra
	    earthMap.update(porcentaje);
	    earthMap.render(batch, camera);
	    
	    // Dibujar el encabezado
	    dibujaEncabezado();
	    
	    // Validar que la nave no salga del límite del mapa 
	    porcentajeDeBarera();
	      
	    // Comprobar colisiones de asteroides y balas, y su destrucción si no está herido
	    noEstaHeridoLaNave();
	    
	    // Dibujar balas
	    for (Bullet bala : balas) {       
	        bala.draw(batch);
	    }
	    
	    // Dibujar y atacar enemigo común
	    createEnemigo();
	    enemigoComunDraw();
	    
	    // Dibujar nave
	    nave.draw(batch, this);
	    
	    // Dibujar asteroides y manejar colisión con nave
	    colisioNaveAsteroide();
	      
	    // Verificar si se acabaron las vidas
	    gameOver();
	    
	    // Prueba de ataque del jefe
	    if (Gdx.input.isKeyPressed(Input.Keys.L)) {
	    	
	        ataqueEstrategy = new AtaqueSpike(new Texture(Gdx.files.internal("ataqueSpike.png")),
	                Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")),
	                6);
	        boss.setAtaqueStrategy(ataqueEstrategy);
	    }
	    if (Gdx.input.isKeyPressed(Input.Keys.K)) {
	        ataqueEstrategy = new AtaqueRecto(new Texture(Gdx.files.internal("ataqueNormalBoss.png")),
	                Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));
	        boss.setAtaqueStrategy(ataqueEstrategy);
	    }

	    batch.end();
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
		this.earthMap.dispose();
		this.explosionSound.dispose();
		this.gameMusic.dispose();
		this.battleMusic.dispose();
	}
   
}
