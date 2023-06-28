package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Tienda.SkinItem;

public class Perfil {
	
	private Sprite coins;
	private int precioProducto;
	private String name;
	private int eduCoins;
	private SkinItem skinItem;
	private ArrayList<SkinItem> skinsItems = new ArrayList<>();
	private SpaceNavigation game;
	
	public Perfil(SpaceNavigation game, String nombre, int coin) {
		this.game = game;
		name = nombre;
		eduCoins = coin;
		precioProducto = 0;
		rellenarSkinsPerfil();
		coins = new Sprite(new Texture(Gdx.files.internal("eduCoins.png")));
		
	}
	public void dibujarCoins(SpriteBatch batch) {
		game.getFont().getData().setScale(2f);
		game.getFont().draw(batch, Integer.toString(eduCoins), 800, 515 );
		game.getFont().draw(batch, name, 710, 563 );
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
	public SkinItem getSkinPerfil (int i) {
		return skinsItems.get(i);
	}
	private void rellenarSkinsPerfil() {
		for(int i = 0; i < 6; i++) {
			int x = i+1;
			
            skinItem = new SkinItem(i);
            skinItem.setSkin(new Texture(Gdx.files.internal("skin" + x + ".png")));
            skinItem.setTxSkinlock(new Texture(Gdx.files.internal("locked" + x + ".png")));
            skinItem.setTxSkinUnlock(new Texture(Gdx.files.internal("unlocked" + x + ".png")));
            skinItem.setPrecio(precioProducto);
            skinsItems.add(i,skinItem);
            precioProducto += 150;
		}
	}
	public void setSkinsItem(SkinItem skin, int index) {
		skinsItems.set(index, skin);
	}
	public boolean getSkinDesbloqueada(int index) {
		return skinsItems.get(index).getSkinDesbloqueada();
	}
	public void setSkinDesbloqueada( int index, boolean a) {
		skinsItems.get(index).setSkinDesbloqueada(a);
	}
	public void setNombre(String nombre) {
		name = nombre;
	}
	public String getNombre() {
		return name;
	}
	public void setEduCoins(int a) {
		eduCoins = a;
	}
	public int getEduCoins() {
		return eduCoins;
	} 
	public int size() {
		return skinsItems.size();
	}
}
