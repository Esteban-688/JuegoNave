package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


public class PantallaMenu implements Screen {

	/*private SpaceNavigation game;
	private OrthographicCamera camera;

	public PantallaMenu(SpaceNavigation game) {
		this.game = game;
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 800);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		
		 
		camera.update();
		game.getBatch().setProjectionMatrix(camera.combined);

		game.getBatch().begin();
		game.getFont().draw(game.getBatch(), "Bienvenido a Space Navigation !", 140, 400);
		game.getFont().draw(game.getBatch(), "Pincha en cualquier lado o presiona cualquier tecla para comenzar ...", 100, 300);
	
		game.getBatch().end();

		if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
			Screen ss = new PantallaJuego(game,1,0,1,1,20);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		}
	}
	*/
	
	 	private SpaceNavigation game;
	    private OrthographicCamera camera;
	    private SpriteBatch batch;
	    private Texture backgroundTexture;
	    private Sprite playButtonSprite;
	   // private Sprite levelsButtonSprite;
	    private Sprite storeButtonSprite;
	    private Vector3 touchPoint;
	    private int screenWidth;
	    private int screenHeight;

	    public PantallaMenu(SpaceNavigation game, int screenWidth, int screenHeight) {
	        this.game = game;
	        this.screenWidth = screenWidth;
	        this.screenHeight = screenHeight;

	        camera = new OrthographicCamera();
	        camera.setToOrtho(false, screenWidth, screenHeight);

	        batch = new SpriteBatch();

	        backgroundTexture = new Texture("fondoMenu.png");
	        playButtonSprite = new Sprite(new Texture("botonPlay.png"));
	        //levelsButtonSprite = new Sprite(new Texture("botonPlay.png"));
	        storeButtonSprite = new Sprite(new Texture("store.png"));

	        touchPoint = new Vector3();

	        // boton play
	        playButtonSprite.setSize(200, 150);
	        playButtonSprite.setPosition(300,350);
	        
	        //boton niveles
	       // levelsButtonSprite.setSize(200, 150);
	        //levelsButtonSprite.setPosition(300, 40);
	        
	        //boton tienda
	        storeButtonSprite.setSize(80, 80);//tamaño
	        storeButtonSprite.setPosition(700, 10);//position
	    }

	    @Override
	    public void render(float delta) {
	        ScreenUtils.clear(0, 0, 0, 1);

	        camera.update();
	        batch.setProjectionMatrix(camera.combined);

	        batch.begin();
	        batch.draw(backgroundTexture, 0, 0);
	        playButtonSprite.draw(batch);
	      //  levelsButtonSprite.draw(batch);
	        storeButtonSprite.draw(batch);
	        batch.end();

	        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
	            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	            camera.unproject(touchPoint);

	            //  "Jugar"
	            if (playButtonSprite.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
	                Screen ss = new PantallaJuego(game, 1, 0, 1, 1, 20);
	                game.setScreen(ss);
	                dispose();
	            }

	            // V"Niveles"
	           // if (levelsButtonSprite.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
	                // game.setScreen(new LevelsScreen(game));
	             //   dispose();
	            //}

	            // "Tienda"
	            if (storeButtonSprite.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
	                // game.setScreen(new Tienda(game));
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