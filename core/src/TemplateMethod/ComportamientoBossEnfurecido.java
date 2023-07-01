package TemplateMethod;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Enemigos.boss.AtaqueRecto;
import com.mygdx.game.Enemigos.boss.AtaqueSpike;
import com.mygdx.game.Enemigos.boss.BossFinal;

public class ComportamientoBossEnfurecido extends ComportamientoBossTemplate {

	@Override
	public void stepTwo(BossFinal boss) {
		setAtaqueEstrategy(new AtaqueRecto(new Texture(Gdx.files.internal("ataqueNormalBoss.png")),
				Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")),
				0.8f)
		); 
		if(boss.getVidaPorcentaje()== 25) {
        boss.setAtaqueStrategy(getAtaqueEstrategy());
		}
		
	}

	@Override
	public void stepThree(BossFinal boss) {
		setAtaqueEstrategy(new AtaqueSpike(new Texture(Gdx.files.internal("ataqueSpike.png")),
                Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")),
                10,
                0.8f)
		); 
		if(boss.getVidaPorcentaje()== 10) {
        boss.setAtaqueStrategy(getAtaqueEstrategy());
		}
		
	}

}
