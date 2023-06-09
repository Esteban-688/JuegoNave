package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.navecita.Nave4;

public class PantallaGameOver implements Screen {
	private Nave4 nave;
	private Perfil perfil;
	private SpriteBatch batch;
	private SpaceNavigation game;
	private Texture backgroundTexture;
	private OrthographicCamera camera;

	// Constructor pantalla de game over
	public PantallaGameOver(SpaceNavigation juego, Nave4 navecita, Perfil miPerfil) {
		perfil = miPerfil;
		game = juego;
		nave = navecita;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1600, 900);
		batch = new SpriteBatch();
		backgroundTexture = new Texture("gameover.png");
	}

	// Renderizado
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(backgroundTexture, 0, 0);
		batch.end();

		if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
			Screen ss = new PantallaMenu(game, nave, perfil);
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
	}

}