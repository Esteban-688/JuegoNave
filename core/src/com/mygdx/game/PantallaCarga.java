package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaCarga implements Screen {
	
	private SpaceNavigation game;
	private OrthographicCamera camera;
	
	 private SpriteBatch batch;
	 private Texture backgroundTexture;
	
	private EarthMap map;
	private float time;
	
	public PantallaCarga(SpaceNavigation game) {
		this.game = game;
		
        time = 0;
        
        
        map = EarthMap.getInstance();
       
        
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1200);

        batch = new SpriteBatch();
        
        
        backgroundTexture = new Texture("PantallaDeCarga.png");
        
	}
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		time+= Gdx.graphics.getDeltaTime();
		
        batch.begin();
        
        batch.draw(backgroundTexture, 0, 0);
        
        batch.end();
       
        if(time >=1) {
        	map.CargarMundo();
        }
        
		if (time >=5 ) {
			time = 0;
			 
			Screen ss = new PantallaJuego(game,1,1,15);
			game.setScreen(ss);
			dispose();
		}
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

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
   
}
