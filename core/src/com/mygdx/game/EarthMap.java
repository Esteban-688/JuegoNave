package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;

public class EarthMap extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Sprite earthSprite;
    private float rotationSpeed;
    private float cameraDistance = 400f;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // Cargar la textura del fondo del espacio
        backgroundTexture = new Texture("background.jpg");

        // Cargar la textura del planeta Tierra
        Texture earthTexture = new Texture("mapamundi-satelital.jpeg");
        earthSprite = new Sprite(earthTexture);
        earthSprite.setOriginCenter(); // Establecer el origen de rotación en el centro del sprite

        rotationSpeed = 10.0f; // Velocidad de rotación del planeta
    }

    @Override
    public void render() {
        // Limpiar el búfer de pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Actualizar la rotación del planeta
        earthSprite.rotate(rotationSpeed * Gdx.graphics.getDeltaTime());

        batch.begin();

        // Dibujar el fondo del espacio
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Calcular la posición y escala del sprite para el efecto 3D
        float aspectRatio = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        float spriteScale = cameraDistance / (cameraDistance + earthSprite.getY() + earthSprite.getHeight() / 2);
        earthSprite.setScale(spriteScale);
        earthSprite.setPosition((Gdx.graphics.getWidth() - earthSprite.getWidth()) / 2, (Gdx.graphics.getHeight() - earthSprite.getHeight()) / 2);
        
        // Dibujar el planeta Tierra
        earthSprite.draw(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        earthSprite.getTexture().dispose();
    }
}
