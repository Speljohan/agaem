package org.gaem.core.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import org.gaem.core.AGame;
import org.gaem.core.screen.OverworldScreen;

/**
 * Created by Joar on 2014-09-27.
 */
public class NPC extends Mobile {

   public Sprite sprite;

    public NPC(float x, float y) {
        super(AGame.ASSETS.get("sprites/player.pack", TextureAtlas.class));
        tileX = MathUtils.floor(x / 16);
        tileY = MathUtils.floor(y / 16);

        realX = x;
        realY = y;


        this.sprite = new Sprite(AGame.ASSETS.get("sprites/player.png", Texture.class));
        this.sprite.setCenter(16, 16);
    }

    public void interact() {
        OverworldScreen.DIALOGUEMANAGER.createDialogue("LOL KEK U R NUBCAKE K");
    }

    public void update(float delta) {

    }
}
