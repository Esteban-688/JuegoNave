package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.navecita.Nave4;

public class PantallaCarga implements Screen {
	private Nave4 nave;
	private EarthMap map;
	private Perfil perfil;
	private SpriteBatch batch;
	private SpaceNavigation game;
	private String consejoAleatorio;
	private OrthographicCamera camera;
	private Texture backgroundTexture;

	private float time;

	// Almacén de consejos en pantalla de carga
	private ArrayList<String> consejos = new ArrayList<>();

	// Constructor
	public PantallaCarga(SpaceNavigation juego, Nave4 navecita, Perfil miPerfil) {
		game = juego;
		nave = navecita;
		time = 0;
		perfil = miPerfil;
		rellenarConsejos();
		map = EarthMap.getInstance();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1600, 900);
		batch = new SpriteBatch();
		backgroundTexture = new Texture("pantallaCarga.png");
		consejo();
	}

	// Renderizado
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(backgroundTexture, 0, 0);
		game.getFont().draw(batch, consejoAleatorio, 800, 162);
		batch.end();

		time += Gdx.graphics.getDeltaTime();

		if (time >= 1) {
			nave.inicio((500 + Config.getDer()) - Config.getDer(), (Config.getUp() - (Config.getUp() / 2)));
			if (time > 3 && map.CargarMundo()) {
				time = 0;
				Screen ss = new PantallaJuego(game, nave, perfil);

				game.setScreen(ss);
				dispose();
			}
		}

	}

	// Obteniendo el consejo de forma aleatoria (solo uno)
	private void consejo() {
		Random random = new Random();
		consejoAleatorio = consejos.get(random.nextInt(consejos.size()));
		game.getFont().getData().setScale(2f);

	}

	// Consejos para ArrayList
	private void rellenarConsejos() {
		consejos.add("Puedes curarte con el Ataque Especial, hasta 800.");
		consejos.add("Para ganar debes matar al boss.");
		consejos.add("Un piloto requiere un gran habilidad espacial.");
		consejos.add("Sé uno solo con la nave.");
		consejos.add("Puedes moverte con las flechas como alternativa.");
		consejos.add("La tecla E puede salvarte la vida.");
		consejos.add("El turbo puede ser de gran ayuda.");
		consejos.add("Una nave y un boss, ¿quién ganara?");
		consejos.add("Ni la NASA tiene tus habilidades.");
		consejos.add("Ostia que has llegado lejos.");
		consejos.add("Derrota muchas naves para ganar EduCoins.");
		consejos.add("Las EduCoins te ayudaran a comprar Skins.");
		consejos.add("Ni el Warden te hará daño como los EduNianos.");
		consejos.add("¿Podrás contra los EduNianos?");
		consejos.add("El Universo se formó hace 13.800 millones de años.");
		consejos.add("Java es un Lenguaje Orientado a objetos.");
		consejos.add("El primer programador fue un matemático.");
		consejos.add("El primer virus informático se remonta a 1986.");
		consejos.add("SpaceWar fue primer juego de ordenador gratis.");
	}

	// Liberación
	public void dispose() {
		batch.dispose();
		backgroundTexture.dispose();
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
}
