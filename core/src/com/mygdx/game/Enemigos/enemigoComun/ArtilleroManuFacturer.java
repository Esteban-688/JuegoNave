package com.mygdx.game.Enemigos.enemigoComun;

import com.mygdx.game.diccionaInterfaces.Atacar;
import com.mygdx.game.diccionaInterfaces.Moverse;

public class ArtilleroManuFacturer extends ActionCompany {

	@Override
	public Atacar createAtaque() {
		return new ArtilleroAtacar();
	}

	@Override
	public Moverse createMovimiento() {
		return new ArtilleroMover();
	}

}
