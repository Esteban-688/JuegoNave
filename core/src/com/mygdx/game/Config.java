package com.mygdx.game;

import com.mygdx.game.navecita.Nave4;

public class Config {
	private static final int derJuego = 20000;
	private static final int izqJuego = -75;
	private static final int upJuego = 1200 ;
	private static final int downJuego = 115;
	private static Nave4 nave;
	
	public static int getDer() {
		return derJuego;
	}
	public static int getIzq() {
		return izqJuego;
	}
	public static int getUp() {
		return upJuego;
	}
	public static int getDown() {
		return downJuego;
	}
	public static void setnave(Nave4 navee) {
		nave = navee;
	}
	public static int naveGetX() {
		return nave.getX();
	}
	public static int naveGetY() {
		return nave.getY();
	}
}
