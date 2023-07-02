package com.mygdx.game.Tienda;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.navecita.Nave4;

public class SkinItem {
	private Texture skin, txSkinBloqueada, txSkinDesbloqueada;

	private boolean skinDesbloqueada;

	private int id;
	private int precio;

	// Constructor
	public SkinItem(int iD) {
		id = iD;
		skinDesbloqueada = false;
		precio = 0;
	}

	public boolean setSkinNave(Nave4 nave) {
		nave.setTx(skin);
		return true;
	}

	public void setSkin(Texture tx) {
		skin = tx;
	}

	public void setTxSkinlock(Texture tx) {
		txSkinBloqueada = tx;
	}

	public void setTxSkinUnlock(Texture tx) {
		txSkinDesbloqueada = tx;
	}

	public void setSkinDesbloqueada(boolean a) {
		skinDesbloqueada = a;
	}

	public void setPrecio(int price) {
		precio = price;
	}
	
	public Texture getCurrentTexture() {
		if (skinDesbloqueada) {
			return txSkinDesbloqueada;
		} else {
			return txSkinBloqueada;
		}
	}

	public int gettId() {
		return id;
	}

	public boolean getSkinDesbloqueada() {
		return skinDesbloqueada;
	}

	public int getPrecio() {
		return precio;
	}
}
