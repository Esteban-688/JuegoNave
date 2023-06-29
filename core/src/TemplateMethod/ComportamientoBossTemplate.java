package TemplateMethod;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Enemigos.boss.AtaqueRecto;
import com.mygdx.game.Enemigos.boss.BossEstrategy;
import com.mygdx.game.Enemigos.boss.BossFinal;

public abstract class ComportamientoBossTemplate {
	private BossEstrategy ataqueEstrategy;
	
	public ComportamientoBossTemplate() {
		
	}
	public void comportamiento(BossFinal boss) {
		stepOne(boss);
		stepTwo(boss);
		stepThree(boss);
	}
	public void stepOne(BossFinal boss) {
	       ataqueEstrategy = new AtaqueRecto(new Texture(Gdx.files.internal("ataqueNormalBoss.png")),
	        									Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")),
	        									2);
	       if(boss.getVidaPorcentaje()==100) { boss.setAtaqueStrategy(ataqueEstrategy);}
	      
	}
	public abstract void stepTwo(BossFinal boss);
	public abstract void stepThree(BossFinal boss);
	public BossEstrategy getAtaqueEstrategy() {
		return ataqueEstrategy;
	}
	public void setAtaqueEstrategy(BossEstrategy ataqueStrategy) {
		ataqueEstrategy = ataqueStrategy;
	}
}
