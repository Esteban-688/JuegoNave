package com.mygdx.game.Enemigos.enemigoComun;

import com.mygdx.game.diccionaInterfaces.Atacar;
import com.mygdx.game.diccionaInterfaces.Moverse;

public class LanzamisilesManuFacturer extends ActionCompany {
	public Atacar createAtaque() {
		return new LanzamisilesAtacar();
	}

	public Moverse createMovimiento() {
		return new LanzamisilesMover();
	}
}