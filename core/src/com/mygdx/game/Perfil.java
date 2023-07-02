package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Tienda.SkinItem;

public class Perfil {
	private String name;
	private Sprite coins;
	private SkinItem skinItem;
	private SpaceNavigation game;

	private int eduCoins, precioProducto;

	private ArrayList<SkinItem> skinsItems = new ArrayList<>();

	// Constructor de perfil
	public Perfil(SpaceNavigation juego, String nombre, int coin) {
		game = juego;
		name = nombre;
		eduCoins = coin;
		precioProducto = 0;

		rellenarSkinsPerfil();

		coins = new Sprite(new Texture(Gdx.files.internal("eduCoins.png")));
	}

	// Dibujando en pantalla la cantidad de monedas disponibles
	public void dibujarCoins(SpriteBatch batch) {
		game.getFont().getData().setScale(2f);
		game.getFont().draw(batch, Integer.toString(eduCoins), 800, 515);
		game.getFont().draw(batch, name, 710, 563);
		coins.setPosition(740, 480);
		coins.setSize(65, 50);
		coins.draw(batch);
	}

	public void sumarEduCoins(int coin) {
		eduCoins += coin;
	}

	public void restarEduCoins(int coin) {
		eduCoins -= coin;
	}

	// Setear las skins disponibles
	private void rellenarSkinsPerfil() {
		for (int i = 0; i < 6; i++) {
			int x = i + 1;

			skinItem = new SkinItem(i);
			skinItem.setSkin(new Texture(Gdx.files.internal("skin" + x + ".png")));
			skinItem.setTxSkinlock(new Texture(Gdx.files.internal("locked" + x + ".png")));
			skinItem.setTxSkinUnlock(new Texture(Gdx.files.internal("unlocked" + x + ".png")));
			skinItem.setPrecio(precioProducto);
			skinsItems.add(i, skinItem);
			precioProducto += 150;
		}
	}

	public int size() {
		return skinsItems.size();
	}

	public void setSkinsItem(SkinItem skin, int index) {
		skinsItems.set(index, skin);
	}

	public void setSkinDesbloqueada(int index, boolean a) {
		skinsItems.get(index).setSkinDesbloqueada(a);
	}

	public boolean setNombre(String nombre) {
		if(nombre != null) { name = nombre; return true; }
		return false;
	}

	public void setEduCoins(int a) {
		eduCoins = a;
	}

	public boolean getSkinDesbloqueada(int index) {
		return skinsItems.get(index).getSkinDesbloqueada();
	}

	public SkinItem getSkinPerfil(int i) {
		return skinsItems.get(i);
	}

	public String getNombre() {
		return name;
	}

	public int getEduCoins() {
		return eduCoins;
	}
}
