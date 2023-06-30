package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.navecita.Nave4;




public class SpaceNavigation extends Game {
	private Nave4 nave;
	private SaveLoad saveload;
	private Perfil perfil;
	private String nombreJuego = "Space Navigation el regreso de edu skyWalker ";
	private SpriteBatch batch;
	private BitmapFont font;	

	public void create() {
		
		
		// cargar imagen de la nave, 64x64   
        nave = new Nave4(((500+Config.getDer())-Config.getDer()),
                        (Config.getUp()-(Config.getUp()/2)),
                        new Texture(Gdx.files.internal("naveMala1.png")),
                        Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")), 
                        new Texture(Gdx.files.internal("Rocket2.png")), //bala normal
                        Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")),//sonido bala normal
                        new Texture(Gdx.files.internal("anilloEspecial.png")),//bala especial
                        Gdx.audio.newSound(Gdx.files.internal("soundBalaespecial.mp3"))//sonido bala especial
                        );
        //cargar perfil desde 0
        
        perfil = new Perfil(this,"Nombre Por Defecto", 10000);
		saveload = new SaveLoad(perfil);
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.getData().setScale(2f);
		Screen ss = new PantallaMenu(this, nave, saveload);
		this.setScreen(ss);
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public BitmapFont getFont() {
		return font;
	}
}