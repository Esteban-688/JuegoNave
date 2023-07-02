package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.navecita.Nave4;

public class Pause implements Screen {
	private Nave4 nave;
	private Perfil perfil;
	private BitmapFont font;
	private SpriteBatch batch;
	private Vector3 touchPoint;
	private PantallaJuego juego;
	private SpaceNavigation game;
	private OrthographicCamera camera;
	private Texture backgroundTexture;
	private Sprite resume, reintentar, menuPrincipal, sprNave;

	// Constructor
	public Pause(SpaceNavigation game1, PantallaJuego juego1, Nave4 navecita, Perfil miPerfil) {
		juego = juego1;
		game = game1;
		nave = navecita;
		perfil = miPerfil;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1600, 900);
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.getData().setScale(2f);

		backgroundTexture = new Texture("pantallapausa.png");
		resume = new Sprite(new Texture("reanudar.png"));
		reintentar = new Sprite(new Texture("reintentar.png"));
		menuPrincipal = new Sprite(new Texture("salir.png"));
		sprNave = new Sprite(nave.getTx());

		touchPoint = new Vector3();

		resume.setSize(307, 78);
		resume.setPosition(290, 470);

		reintentar.setSize(307, 78);
		reintentar.setPosition(290, 350);

		menuPrincipal.setSize(307, 78);
		menuPrincipal.setPosition(290, 230);

		sprNave.setSize(150, 150);
		sprNave.setPosition(1130, 230);
	}

	// Renderizado
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(backgroundTexture, 0, 0);
		resume.draw(batch);
		reintentar.draw(batch);
		menuPrincipal.draw(batch);
		sprNave.draw(batch);
		estadisticasDraw(batch);
		batch.end();

		// Acciones al tocar uno de los botones
		if (Gdx.input.isTouched()) {
			touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPoint);

			// Seguir jugando
			if (resume.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
				game.setScreen(juego);
				juego.setRender(false);
				dispose();
			}
			// Reintentar
			else if (reintentar.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
				Screen ss = new PantallaCarga(game, nave, perfil);
				game.setScreen(ss);
				juego.dispose();
				dispose();
			}
			// Ir al menú principl
			else if (menuPrincipal.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
				Screen ss = new PantallaMenu(game, nave, perfil);
				game.setScreen(ss);
				juego.dispose();
				dispose();
			}

		}

		// Seguir jugando
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			game.setScreen(juego);
			juego.setRender(false);
			dispose();
		}
	}

	// Estadísticas del jugador
	private void estadisticasDraw(SpriteBatch batch) {
		game.getFont().getData().setScale(2f);
		game.getFont().draw(batch, perfil.getNombre(), 1120, 562);
		game.getFont().draw(batch, Integer.toString(nave.getVidas()), 1120, 528);
		game.getFont().draw(batch, Integer.toString(juego.getContadorkill()), 1269, 485);

	}

	// Liberación
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {

	}
}
