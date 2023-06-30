package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;


public class EarthMap {
    private ArrayList<Texture> earthTextures; 
    private Sprite earthSprite; 
    private Texture starTexture;
    private Sprite[] stars;
    private static EarthMap instance;
    
    private EarthMap() { 
       //constructor privado para el uso del singleton
    }
    
    public static EarthMap getInstance() {
    	
        if (instance == null) {
            instance = new EarthMap();
        }
        return instance;
    }
    
    public void update(float porcentaje) {
        int index = (int) (porcentaje / 100f * earthTextures.size());
        if(porcentaje <= 100) {
        	earthSprite.setTexture(earthTextures.get(index));
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
            earthTextures = new ArrayList<>();

            for (int i = 1; i <= 100; i++) {
                earthTextures.add(new Texture(Gdx.files.internal("earth" + i + ".png")));
            }
            starTexture = new Texture(Gdx.files.internal("star.png"));
            return true;
    }

    public void iniciar() {
    	// Crear el sprite del planeta Tierra y posicionarlo en el centro de la pantalla
        earthSprite = new Sprite(earthTextures.get(1));
        earthSprite.setPosition((Gdx.graphics.getWidth() - earthSprite.getWidth()) / 2,
                (Gdx.graphics.getHeight() - earthSprite.getHeight()) / 2);
    }
    
    public void dispose() {
        // Liberar las texturas del planeta Tierra
        for (int i = 0; i < earthTextures.size();i++ ) {
        	earthTextures.get(i).dispose();
        }
        starTexture.dispose();
    }
}
