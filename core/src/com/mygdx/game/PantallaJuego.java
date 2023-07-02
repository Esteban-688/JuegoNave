package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Enemigos.boss.BossFinal;
import com.mygdx.game.Enemigos.enemigoComun.CreateEnemigo;
import com.mygdx.game.Enemigos.enemigoComun.EnemyComun;
import com.mygdx.game.balas.Bullet;
import com.mygdx.game.navecita.Nave4;

import TemplateMethod.ComportamientoBossEnfurecido;
import TemplateMethod.ComportamientoBossNormal;
import TemplateMethod.ComportamientoBossTemplate;

public class PantallaJuego implements Screen {
	private Nave4 nave;
	private Pause pause;
	private Perfil perfil;
	private BossFinal boss;
	private EarthMap earthMap;
	private SpriteBatch batch;
	private SpaceNavigation game;
	private Sound explosionSound;
	private EnemyComun enemigoComun;
	private CreateEnemigo crearEnemy;
	private OrthographicCamera camera;
	private Music gameMusic, battleMusic;
	private ComportamientoBossTemplate comportamientoBoss;

	private float tiempoTotal1, tiempoTotal2;
	private boolean bossActivado, bossMuerto, barreraBoolean, renderPausado;
	private int ancho, alto, anchoCamara, altoCamara, porcentaje, barreraY, barreraX, contadorDeEnemigos,
			contadorDeKill;

	private ArrayList<Bullet> balas = new ArrayList<>();
	private ArrayList<EnemyComun> enemigos = new ArrayList<>();

	// Constructor de pantalla de juego
	public PantallaJuego(SpaceNavigation juego, Nave4 navecita, Perfil miPerfil) {
		// Creación de la nave
		nave = navecita;
		Config.setnave(nave);

		perfil = miPerfil;
		bossActivado = false;
		bossMuerto = false;
		barreraBoolean = false;
		renderPausado = false;

		ancho = Config.getDer();
		alto = Config.getUp();

		game = juego;

		anchoCamara = 800;
		altoCamara = 640;
		porcentaje = 0;
		barreraX = 0;
		barreraY = 0;
		contadorDeEnemigos = 0;
		contadorDeKill = 0;

		batch = game.getBatch();

		// Cámaras
		camera = new OrthographicCamera();
		camera.setToOrtho(false, anchoCamara, altoCamara);

		// Cargar mundo
		earthMap = EarthMap.getInstance();
		earthMap.iniciar();
		earthMap.createStars(ancho, alto);

		// Inicializar assets, música de fondo y efectos de sonido
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1, 0.5f);

		battleMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound-battle.mp3"));
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Musica.mp3"));

		gameMusic.setLooping(true);
		gameMusic.setVolume(0.6f);
		gameMusic.play();

		crearEnemy = new CreateEnemigo();

		// Crear y cargar Jefe Final
		boss = new BossFinal(ancho, alto / 2, 6, nave);
	}

	// Encabezado y pie, con datos del jugador y más
	private void dibujaEncabezado() {
		int xVida, yVida, xKill, yKill, xPorcentaje, yPorcentaje;
		game.getFont().getData().setScale(2f);

		// x
		xVida = ((int) camera.position.x - 40);
		xKill = (int) camera.position.x + 250;
		xPorcentaje = ((int) camera.position.x) - 40;
		// Y
		yVida = ((int) camera.position.y) - 280;
		yKill = ((int) camera.position.y) - 280;
		yPorcentaje = ((int) camera.position.y) + 290;

		// Colocar en pantalla los encabezados
		game.getFont().setColor(Color.CHARTREUSE);
		game.getFont().draw(batch, "Vida: " + nave.getVidas(), xVida, yVida);

		if (!bossActivado || bossMuerto) {
			game.getFont().setColor(Color.GOLDENROD);
			game.getFont().draw(batch, "EduCoins: " + perfil.getEduCoins(), xVida - 330, yVida);

			game.getFont().setColor(Color.SCARLET);
			game.getFont().draw(batch, "   Kills: " + contadorDeKill, xKill, yKill);

			game.getFont().setColor(Color.LIGHT_GRAY);
			game.getFont().draw(batch, "] " + porcentaje + "%", xPorcentaje + 230, yPorcentaje - 20);
		} else if (bossActivado && !bossMuerto) {
			game.getFont().setColor(Color.RED);
			game.getFont().draw(batch, "] " + (int) boss.getVidaPorcentaje() + "%", xPorcentaje + 230,
					yPorcentaje - 20);
			game.getFont().draw(batch, " JEFE ", xPorcentaje + 10, yPorcentaje + 15);
		}

		// Barra de progreso de la nave. Cambia a la vida del Boss cuando se muestra
		// este último
		int longitudBarra = 62;
		int longitudRelleno;

		if (bossActivado && !bossMuerto) {
			game.getFont().setColor(Color.RED);
			longitudRelleno = (int) (boss.getVidaPorcentaje() / 100f * longitudBarra);
		} else {
			game.getFont().setColor(Color.LIGHT_GRAY);
			longitudRelleno = (int) (porcentaje / 100f * longitudBarra);
		}

		String barraProgreso = "";

		for (int i = 0; i < longitudBarra; i++) {
			if (i < longitudRelleno) {
				barraProgreso += "|";
			} else {
				barraProgreso += "";
			}
		}
		// Dbujar la barra de progreso
		game.getFont().draw(batch, "[" + barraProgreso, xPorcentaje - 150, yPorcentaje - 20);

		// Añadiendo consejos en pantalla
		dibujarConsejos(xPorcentaje, yPorcentaje);

		// Seteando color de letra en blanco
		game.getFont().setColor(Color.WHITE);
	}

	// Consejos para jugar
	private void dibujarConsejos(int x, int y) {
		game.getFont().getData().setScale(3f);
		game.getFont().draw(batch, "Avanza", Config.getIzq() + 200, Config.getUp() / 2 + 260);
		game.getFont().draw(batch, ">>>>>>", Config.getIzq() + 200, Config.getUp() / 2 + 224);

		if (bossActivado && boss.getVidaPorcentaje() == 100)
			game.getFont().draw(batch, "Disparale al Boss", x - 130, y - 110);
	}

	// Movimiento de la cámara
	private void actualizarCamara() {

		// valida derecha y izquierda
		if (nave.getX() > ancho - 320 || nave.getX() < 320) {
			// no actualiza la camara
		} else if (bossActivado) {// eje x
			if (bossMuerto) {
				camera.position.x = nave.getX();
			}
		} else {
			camera.position.x = nave.getX();
		}

		if (nave.getY() > alto - 400 || nave.getY() < 400) {
			// no actualiza
		} else if (bossActivado) {// eje Y
			if (bossMuerto) {
				camera.position.y = nave.getY();
			}
		} else {
			camera.position.y = nave.getY();
		}

		camera.update();

		porcentaje = (int) ((nave.getX() / (float) ancho) * 100);

		batch.setProjectionMatrix(camera.combined);
	}

	private void porcentajeDeBarera() {
		// Inicio
		if (porcentaje <= 90 && !bossActivado) {
			nave.inmune(6);
			nave.bordeNave(0, ancho, alto, 0);
			// Nivel "medio"
		} else if (porcentaje > 90 && !bossActivado) {

			if (!barreraBoolean) {

				barreraBoolean = true;
				barreraX = nave.getX();
			}

			nave.bordeNave(barreraX + 290, barreraX + 500, alto, 0);

			// Si se han dado de baja al menos 3 enemigos, se puede entrar a la zona del
			// Boss
			if (contadorDeKill >= 3)
				bossActivado = true;

			// Nivel final (zona Boss)
		} else if (bossActivado) {
			// Sólo sucede una vez
			if (barreraBoolean) {
				nave.setPosition(ancho - 500, alto / 2);
				camera.position.x = ancho - 500;
				camera.position.y = alto / 2;
				barreraBoolean = false;
				barreraX = ancho - 500;
				barreraY = alto / 2;

				// Barrera del Boss
				boss.setBarreraBoss(barreraX - 380, barreraX + 250, barreraY - 305, barreraY + 350);

				gameMusic.pause();
				battleMusic.setLooping(true);
				battleMusic.setVolume(1.0f);
				battleMusic.play();

				// Limpieza del mapa antes de la aparición del Boss
				enemigos.clear();
				balas.clear();

			}

			// Dibujando al Boss
			if (!boss.isDestruida()) {
				boss.draw(batch, this);
				boss.atacar(batch, this, boss.getSprite());
				boss.moverse(boss.getSprite());

				// Utilización del patrón Strategy en conjunto con patrón Template Method en
				// BossFinal.
				if (boss.getVidaPorcentaje() > 49) {
					comportamientoBoss = new ComportamientoBossNormal();
				} else {
					comportamientoBoss = new ComportamientoBossEnfurecido();
				}

				comportamientoBoss.comportamiento(boss);

				nave.bordeNave(barreraX - 305, barreraX + 290, barreraY + 390, barreraY - 405);
			} else {
				// Cuando el Boss ha muerto, se ganan 10 monedas
				if (!bossMuerto) {
					battleMusic.stop();
					gameMusic.setLooping(true);
					gameMusic.setVolume(0.6f);
					gameMusic.play();
					bossMuerto = true;
					perfil.sumarEduCoins(10);
				}
				nave.bordeNave(barreraX - 280, barreraX + 880, barreraY + 400, barreraY - 400);

			}

			// Volvemos al menú inicial
			if (porcentaje >= 100 && bossMuerto) {
				Screen ss = new PantallaMenu(game, nave, perfil);
				game.setScreen(ss);
				dispose();

			}

		}

	}

	// Pantalla de game over
	private void gameOver() {
		if (nave.estaDestruido()) {
			Screen ss = new PantallaGameOver(game, nave, perfil);
			game.setScreen(ss);
			dispose();
		}
	}

	// Colisiones
	private void noEstaHeridoLaNave() {
		if (!nave.estaHerido()) {
			// Colisiones entre balas y su destrucción
			for (int i = 0; i < balas.size(); i++) {
				Bullet b = balas.get(i);

				for (int f = 0; f < enemigos.size(); f++) {
					EnemyComun z = enemigos.get(f);
					balas.get(i).checkCollision(z);
					if (z.isDestruida()) {
						enemigos.remove(z);
						contadorDeKill++;
						perfil.sumarEduCoins(1);
					}
				}
				nave.checkCollision(boss, camera);
				balas.get(i).checkCollision(nave);
				balas.get(i).checkCollision(boss);

				b.update((int) camera.position.x, (int) camera.position.y, anchoCamara, altoCamara, nave.getX() + 25,
						nave.getY() + 15);

				if (b.isDestroyed()) {
					balas.remove(b);
					i--; // para no saltarse 1 tras eliminar del arraylist
				}
			}

		}
	}

	// Dibujando naves enemigas
	private void enemigoComunDraw() {
		for (int x = 0; x < enemigos.size(); x++) {
			EnemyComun nuevo = enemigos.get(x);
			nuevo.draw(batch, this);
		}
	}

	// Creando las naves enemigas
	private void createEnemigo() {

		tiempoTotal1 += Gdx.graphics.getDeltaTime();
		tiempoTotal2 += Gdx.graphics.getDeltaTime();

		if (tiempoTotal2 > 2.2 && // cada cuanto se generan
				enemigos.size() < 4 && // cuantos enemigos por mapa
				contadorDeEnemigos <= 40 && // maxima cantidad de enemigos
				!bossActivado) {// si el boss no esta activo se generan
			tiempoTotal2 = 0.0f;
			enemigoComun = crearEnemy.crearArtillero(nave.getX() - 360, alto / 2);
			// añadir
			enemigos.add(enemigoComun);
			contadorDeEnemigos++;
		}
		if (tiempoTotal1 > 2.2 && // cada cuanto se generan
				enemigos.size() < 5 && // cuantos enemigos por mapa
				contadorDeEnemigos <= 40 && // maxima cantidad de enemigos
				!bossActivado) {// si el boss no esta activo se generan

			tiempoTotal1 = 0.0f;

			enemigoComun = crearEnemy.crearLanzamisiles(nave.getX() + 200, alto / 2);
			// añadir
			enemigos.add(enemigoComun);
			contadorDeEnemigos++;
		}
	}

	// Renderizado
	public void render(float delta) {

		// se usa para detener el render en la pausa
		if (renderPausado) {
			return;
		}
		// verificar Pausa
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			pause();
		}

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		// Actualizar movimiento de la cámara
		actualizarCamara();

		// Iniciar el dibujado de los elementos
		batch.begin();

		// Dibujar el mapa de la Tierra
		if (porcentaje < 100) {
			earthMap.update(porcentaje);
			earthMap.render(batch, camera);
		}

		// Dibujar el encabezado
		dibujaEncabezado();

		// Validar que la nave no salga del límite del mapa
		porcentajeDeBarera();

		// Comprobar colisiones de balas, y su destrucción, si no está herido
		noEstaHeridoLaNave();

		// Dibujar balas
		for (Bullet bala : balas) {
			bala.draw(batch);
		}

		// Dibujar y atacar enemigo común
		createEnemigo();
		enemigoComunDraw();

		// Dibujar nave
		nave.draw(batch, this);

		// Verificar si su vida llego a 0
		gameOver();

		batch.end();

	}

	public boolean agregarBala(Bullet bb) {
		return balas.add(bb);
	}

	public void setRender(boolean a) {
		renderPausado = a;
	}

	public int getContadorkill() {
		return contadorDeKill;
	}

	// Liberación
	public void dispose() {
		this.earthMap.dispose();
		this.explosionSound.dispose();
		this.gameMusic.dispose();
		this.battleMusic.dispose();
	}

	// Pantalla de pausa
	public void pause() {
		pause = new Pause(game, this, nave, perfil);
		renderPausado = true;
		game.setScreen(pause);
	}

	@Override
	public void show() {

		gameMusic.play();
	}

	@Override
	public void resize(int width, int height) {
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
