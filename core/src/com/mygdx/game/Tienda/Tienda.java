package com.mygdx.game.Tienda;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.PantallaCarga;
import com.mygdx.game.PantallaMenu;
import com.mygdx.game.Perfil;
import com.mygdx.game.SpaceNavigation;
import com.mygdx.game.navecita.Nave4;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;


public class Tienda implements Screen {
    
	private SpaceNavigation game;
	private Perfil perfil;
	private SkinItem skinItem;
	private Texture backgroundTexture;
	private Sprite spr, volver;
    private Nave4 nave;
    private ArrayList<SkinItem> skinItems= new ArrayList<>();
    private ArrayList<Sprite> sprSkins = new ArrayList<>();
    private float time;
    private OrthographicCamera camera;
    private SpriteBatch batch;
	private Vector3 touchPoint;
    
    public Tienda(SpaceNavigation game, Nave4 navecita, Perfil miPerfil) {
    	this.game = game;
    	
    	nave = navecita;
    	perfil = miPerfil;
    	rellenarSkins();
    	camera = new OrthographicCamera();
        camera.setToOrtho(false, 1000, 600);
        
        batch = new SpriteBatch();
        backgroundTexture = new Texture("inicio1.png");
        
        volver = new Sprite(new Texture(Gdx.files.internal("flecha.png")));
        volver.setSize(100, 60);
        volver.setPosition(800, 20);
        
        touchPoint = new Vector3();
        time = 0.0f;
        

    }
    
    
    private void rellenarSkins() {
        for(int i = 0; i < perfil.size(); i++) {
            skinItem = perfil.getSkinPerfil(i);
            skinItems.add(i,skinItem);//cambiar a x por i cuando esten todas la imagenes
        }
        for(int i = 0; i < skinItems.size() ;i++) {
       		spr = new Sprite (skinItems.get(i).getCurrentTexture());
    		sprSkins.add(i,spr);
    	}
    }
    
    private void updateTxSkins() {
    	for(int i = 0; i < sprSkins.size();i++) {	
    		spr = new Sprite(skinItems.get(i).getCurrentTexture());
    		sprSkins.set(i,spr);
    	}
    	drawTxSkin();
    }
    private void drawTxSkin(){
    	float x = 50;
    	float y = 300;
    	
    	for(int i = 0; i < sprSkins.size();i++) {	
    		if(i==3){y-=200; x = 50;}
    		sprSkins.get(i).setSize(150,150);
    		sprSkins.get(i).setPosition(x, y);
    		sprSkins.get(i).draw(batch);
    		x += 250;
    	}
    }
    private void updateComprarEquipar(OrthographicCamera camera) {

    	time += Gdx.graphics.getDeltaTime();
    	if(time >= 0.5) {
	    	if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
	            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	            camera.unproject(touchPoint);
	            
	            
	            for(int i = 0; i < sprSkins.size();i++) {
	            	if (sprSkins.get(i).getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
	            		if(skinItems.get(i).getSkinDesbloqueada()) {
	            			skinItems.get(i).setSkinNave(nave);
	            		}else {
	            			if(skinItems.get(i).getPrecio()<= perfil.getEduCoins()) {
	            				skinItems.get(i).setSkinDesbloqueada(true);
	            				perfil.restarEduCoins(skinItems.get(i).getPrecio());
	            			}
	            			
	            		}
	            	}
	            }
	            if (volver.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
	                rellenarPerfil();
	            	Screen ss = new PantallaMenu(game, nave, perfil);
	                game.setScreen(ss);
	                dispose();
	            }
	            
	        }
    	}
    	
    }
    private void rellenarPerfil() {
    	
    	for (int i = 0; i < sprSkins.size();i++) {
    		perfil.setSkinsItem(skinItems.get(i),i);
    	}
    }
    
    @Override
    public void show() {
  
    }
    
    @Override
    public void render(float delta) {
       
    	 ScreenUtils.clear(0, 0, 0, 1);

	        camera.update();
	        batch.setProjectionMatrix(camera.combined);

	        batch.begin();
	        
	        
	        batch.draw(backgroundTexture, 0, 0);
	        updateTxSkins();
	        volver.draw(batch);
	        perfil.dibujarCoins(batch);
	        batch.end();
	        
	        updateComprarEquipar(camera);
    }
    
    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void dispose() {
        // TODO Auto-generated method stub
    }
}
