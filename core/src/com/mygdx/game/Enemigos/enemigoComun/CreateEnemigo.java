package com.mygdx.game.Enemigos.enemigoComun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.diccionaInterfaces.Atacar;
import com.mygdx.game.diccionaInterfaces.Moverse;

public class CreateEnemigo {
	private EnemyComun enemigoComun;
	private ActionCompany lanzamisiles, artillero;
	private Atacar ataqueArtillero, ataqueLanzamisiles;
	private Moverse movimientoArtillero, movimientoLanzamisiles;

	public CreateEnemigo() {
		enemigoComun = null;

		// Lanzamisiles
		artillero = new ArtilleroManuFacturer();
		ataqueArtillero = artillero.createAtaque();
		movimientoArtillero = artillero.createMovimiento();

		// artillero
		lanzamisiles = new LanzamisilesManuFacturer();// company
		ataqueLanzamisiles = lanzamisiles.createAtaque();// atacar
		movimientoLanzamisiles = lanzamisiles.createMovimiento();// moverse

	}

	public EnemyComun crearLanzamisiles(int x, int y) {
		enemigoComun = new EnemyComun(x, // x
				y, //
				new Texture(Gdx.files.internal("TxLanzamisilesNave.png")), ataqueLanzamisiles, // tipo de ataque
				movimientoLanzamisiles// tipo de moviento
		);

		return enemigoComun;
	}

	public EnemyComun crearArtillero(int x, int y) {
		enemigoComun = new EnemyComun(x, // x
				y, new Texture(Gdx.files.internal("TxArtilleroNave.png")), ataqueArtillero, // tipo de ataque
				movimientoArtillero// tipo de movimiento
		);
		return enemigoComun;
	}

	public EnemyComun CrearKamikaze(int x, int y) {

		return enemigoComun;
	}
}
