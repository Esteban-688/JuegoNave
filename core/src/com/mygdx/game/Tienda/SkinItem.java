package com.mygdx.game.Tienda;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.navecita.Nave4;

public class SkinItem {
	private Texture skin, txSkinBloqueada,txSkinDesbloqueada;
	private int id;
	private boolean skinDesbloqueada;
	private int precio;
	
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
		txSkinBloqueada= tx;
	}
	public void setTxSkinUnlock(Texture tx) {
		txSkinDesbloqueada= tx;
	}
	public int gettId() {
		return id;
	}
	
	public void setSkinDesbloqueada(boolean a) {
		skinDesbloqueada = a;
	}
	public boolean getSkinDesbloqueada() {
		return skinDesbloqueada;
	}
	public Texture getCurrentTexture() {
	    if(skinDesbloqueada) {
	    	return txSkinDesbloqueada;
	    }else {
	    	return txSkinBloqueada;
	    }
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int price) {
		precio = price;
	}
}
