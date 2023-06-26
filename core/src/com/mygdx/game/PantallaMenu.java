package com.mygdx.game;

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
	    
	    private Sprite playButtonSprite;
	   // private Sprite levelsButtonSprite;
	    private Sprite storeButtonSprite;
	    private Vector3 touchPoint;
	    private Nave4 nave;
	   

	    public PantallaMenu(SpaceNavigation game, Nave4 navecita) {
	        this.game = game;
	 
	        nave= navecita;
	        camera = new OrthographicCamera();
	        camera.setToOrtho(false, 1000,600);

	        batch = new SpriteBatch();

	        backgroundTexture = new Texture("inicio1.png");
	        playButtonSprite = new Sprite(new Texture("botonPlay.png"));
	        //levelsButtonSprite = new Sprite(new Texture("botonPlay.png"));
	        storeButtonSprite = new Sprite(new Texture("store.png"));

	        touchPoint = new Vector3();

	        // boton play
	        playButtonSprite.setSize(200, 150);
	        playButtonSprite.setPosition(60,300);
	        
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
	                Screen ss = new PantallaCarga(game, nave);
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
	                 game.setScreen(new Tienda(game, nave));
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