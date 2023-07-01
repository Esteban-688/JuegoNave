package com.mygdx.game;

import javax.swing.JOptionPane;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Tienda.Tienda;
import com.mygdx.game.navecita.Nave4;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


public class PantallaMenu implements Screen {
	
	 	private SpaceNavigation game;
	    private OrthographicCamera camera;
	    private SpriteBatch batch;
	    private Texture backgroundTexture;
	    private Sprite playButtonSprite, storeButtonSprite, guardar, cargar, cambiarName;
	    private Vector3 touchPoint;
	    private Nave4 nave;
	    private Perfil perfil;
	    private SaveLoad saveLoad;

	    public PantallaMenu(SpaceNavigation game, Nave4 navecita, SaveLoad saveload) {
	        this.game = game;
	        perfil = saveload.getPerfil();
	        saveLoad = saveload;
	        nave= navecita;
	        
	        camera = new OrthographicCamera();
	        camera.setToOrtho(false, 1024, 576);

	        batch = new SpriteBatch();

	        backgroundTexture = new Texture("inicio1.png");
	        playButtonSprite = new Sprite(new Texture("botonPlay.png"));
	        storeButtonSprite = new Sprite(new Texture("store.png"));
	        guardar = new Sprite(new Texture("botonGuardar.png"));
	        cargar = new Sprite(new Texture("botonCargar.png"));
	        cambiarName = new Sprite(new Texture("Cnombre.png"));

	        touchPoint = new Vector3();

	        // boton play
	        playButtonSprite.setSize(170, 49);
	        playButtonSprite.setPosition(70,350);
	        
	        //boton guardar
	        guardar.setSize(170, 49);
	        guardar.setPosition(70,280);
	        
	        //boton cargar
	        cargar.setSize(170, 49);
	        cargar.setPosition(70,210);
	        
	        //boton tienda
	        storeButtonSprite.setSize(170, 49);
	        storeButtonSprite.setPosition(70, 140);
	        
	        //boton cambiar nombre
	        cambiarName.setSize(41, 39);
	        cambiarName.setPosition(660, 530);
	    }
	    public PantallaMenu(SpaceNavigation game, Nave4 navecita, Perfil miPerfil) {
	        this.game = game;
	        perfil = miPerfil;
	        saveLoad = new SaveLoad(miPerfil);
	        nave= navecita;
	        camera = new OrthographicCamera();
	        camera.setToOrtho(false, 1000,600);

	        batch = new SpriteBatch();

	        backgroundTexture = new Texture("inicio1.png");
	        playButtonSprite = new Sprite(new Texture("botonPlay.png"));
	        storeButtonSprite = new Sprite(new Texture("store.png"));
	        guardar = new Sprite(new Texture("botonGuardar.png"));
	        cargar = new Sprite(new Texture("botonCargar.png"));
	        cambiarName = new Sprite(new Texture("Cnombre.png"));
	        

	        touchPoint = new Vector3();

	        // boton play
	        playButtonSprite.setSize(170, 49);
	        playButtonSprite.setPosition(70,350);
	        
	        //boton guardar
	        guardar.setSize(170, 49);
	        guardar.setPosition(70,280);
	        
	        //boton cargar
	        cargar.setSize(170, 49);
	        cargar.setPosition(70,210);
	        
	        //boton tienda
	        storeButtonSprite.setSize(170, 49);
	        storeButtonSprite.setPosition(70, 140);
	        //boton cambiar nombre
	        cambiarName.setSize(41, 39);
	        cambiarName.setPosition(660, 530);
	    }

	    @Override
	    public void render(float delta) {
	        ScreenUtils.clear(0, 0, 0, 1);

	        camera.update();
	        batch.setProjectionMatrix(camera.combined);

	        batch.begin();
	        
	        
	        batch.draw(backgroundTexture, 0, 0);
	        
	        perfil.dibujarCoins(batch);
	        playButtonSprite.draw(batch);
	        storeButtonSprite.draw(batch);
	        guardar.draw(batch);
	        cargar.draw(batch);
	        cambiarName.draw(batch);
	        
	        
	        batch.end();

	        if (Gdx.input.isTouched()) {
	            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	            camera.unproject(touchPoint);

	            //  "Jugar"
	            if (playButtonSprite.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
	                Screen ss = new PantallaCarga(game, nave, perfil);
	                game.setScreen(ss);
	                dispose();
	            }
	            
	            if (guardar.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
	               SaveLoad.saved(perfil);
	            }
	            if (cargar.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
		           SaveLoad.load(perfil);
		            }
	            if (cambiarName.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
	            	
	            	String inputText = JOptionPane.showInputDialog("Ingrese Nombre");
	            	perfil.setNombre(inputText);
			      }

	            // "Tienda"
	            if (storeButtonSprite.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
	            	
	            	game.setScreen(new Tienda(game, nave, perfil));
	                dispose();
	            }
	        }
	    }



    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
       // playButtonTexture.dispose();
        //levelsButtonTexture.dispose();
        //storeButtonTexture.dispose();
    }
	
	
	
	
	
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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

	/*@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}*/
   
}