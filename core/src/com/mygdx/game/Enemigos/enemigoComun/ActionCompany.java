package com.mygdx.game.Enemigos.enemigoComun;

import com.mygdx.game.diccionaInterfaces.Atacar;
import com.mygdx.game.diccionaInterfaces.Moverse;

public abstract class ActionCompany {
	public abstract Atacar createAtaque();
	public abstract Moverse createMovimiento();
}
