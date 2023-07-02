package TemplateMethod;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Enemigos.boss.AtaqueRecto;
import com.mygdx.game.Enemigos.boss.AtaqueSpike;
import com.mygdx.game.Enemigos.boss.BossFinal;

public class ComportamientoBossNormal extends ComportamientoBossTemplate {
	public void stepTwo(BossFinal boss) {
		setAtaqueEstrategy(new AtaqueSpike(new Texture(Gdx.files.internal("ataqueSpike.png")),
				Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")), 6, 1.5f));

		if (boss.getVidaPorcentaje() == 70) {
			boss.setAtaqueStrategy(getAtaqueEstrategy());
		}
	}

	public void stepThree(BossFinal boss) {
		setAtaqueEstrategy(new AtaqueRecto(new Texture(Gdx.files.internal("ataqueNormalBoss.png")),
				Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")), 1.4f));

		if (boss.getVidaPorcentaje() == 50) {
			boss.setAtaqueStrategy(getAtaqueEstrategy());
		}
	}
}