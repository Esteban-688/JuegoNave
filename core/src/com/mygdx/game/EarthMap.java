package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;


public class EarthMap {
    private Texture[] earthTextures; 
    private Sprite earthSprite; 
    private Texture starTexture;
    private Sprite[] stars;
    private boolean mundoCargado;
    private static EarthMap instance;
    
    private EarthMap() { 
    	mundoCargado = false;
        starTexture = new Texture(Gdx.files.internal("star.png"));
    }
    
    public static EarthMap getInstance() {
    	
        if (instance == null) {
            instance = new EarthMap();
        }
        return instance;
    }
    
    public void update(float porcentaje) {
        int index = (int) (porcentaje / 100f * earthTextures.length);
        if(porcentaje <=100) {
        	earthSprite.setTexture(earthTextures[index]);
        }
    }


    public void render(SpriteBatch batch, Camera camera) {
    	 float scale = 0.4f;
    	
        float x = camera.position.x - earthSprite.getWidth() / 2;
        float y = camera.position.y - earthSprite.getHeight() / 2;
        
        earthSprite.setPosition(x, y);
        earthSprite.setScale(scale);
        earthSprite.draw(batch);
        
     // Dibujar las estrellas
        for (Sprite star : stars) {
            star.draw(batch);
        }
    }
    
    public void createStars(int ancho, int alto) {
        int numStars = 10000;
        stars = new Sprite[numStars];

        for (int i = 0; i < numStars; i++) {
            Sprite star = new Sprite(starTexture);

            // Posicionar la estrella de manera aleatoria en el mapa
            float x = MathUtils.random(ancho);
            float y = MathUtils.random(alto);

            star.setPosition(x, y);
            stars[i] = star;
        }
    }
    
    public boolean CargarMundo() {
    	
    	if(!mundoCargado) {
    		
    		mundoCargado = true;
    		
	    	earthTextures = new Texture[100];
	    	
	        for (int i = 0; i < 100; i++) {
	        	int x = i+1;
	            earthTextures[i] = new Texture(Gdx.files.internal("earth" + x + ".png"));
	        }
	        return true;
    	}
        return false;
    }

    public void iniciar() {
    	// Crear el sprite del planeta Tierra y posicionarlo en el centro de la pantalla
        earthSprite = new Sprite(earthTextures[0]);
        earthSprite.setPosition((Gdx.graphics.getWidth() - earthSprite.getWidth()) / 2,
                (Gdx.graphics.getHeight() - earthSprite.getHeight()) / 2);
    }
    
    public void dispose() {
        // Liberar las texturas del planeta Tierra
        for (Texture texture : earthTextures) {
            texture.dispose();
        }
        starTexture.dispose();
    }
}
