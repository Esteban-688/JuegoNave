package com.mygdx.game.Tienda;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.navecita.Nave4;

public class SkinItem {
	private Texture skin, txSkinBloqueada,txSkinDesbloqueada;
	private int id;
	private boolean skinDesbloqueada;
	private Sprite spr;
	
	public SkinItem(int iD) {
		id = iD;
		skinDesbloqueada = false;
	}
	
	public boolean setSkinNave(Nave4 nave) {
		nave.setTx(skin);
		return true;
	}
	public void setSkin(Texture tx) {
		skin = tx;
		spr = new Sprite(tx);
	}
	public void setPositionSpr(int x, int y) {
		spr.setPosition(x, y);
	}
	public void setTxSkinlock(Texture tx) {
		txSkinBloqueada= tx;
	}
	public Texture getTxSkinlock() {
		return txSkinBloqueada;
	}
	public void setTxSkinUnlock(Texture tx) {
		txSkinDesbloqueada= tx;
	}
	public Texture getTXSkinUnlock() {
		return txSkinDesbloqueada;
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
}
