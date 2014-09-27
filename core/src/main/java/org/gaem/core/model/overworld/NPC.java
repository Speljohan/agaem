package org.gaem.core.model.overworld;

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
   public String id;

    public NPC(float x, float y, String id) {
        super(AGame.ASSETS.get("sprites/player.pack", TextureAtlas.class));
        tileX = MathUtils.floor(x / 16);
        tileY = MathUtils.floor(y / 16);

        this.id = id;

        realX = x;
        realY = y;


        this.sprite = new Sprite(AGame.ASSETS.get("sprites/player.png", Texture.class));
        this.sprite.setCenter(16, 16);
    }

    public void interact() {
        OverworldScreen.DIALOGUEMANAGER.createDialogue(id);
        System.out.println("You dud, mah ID is " + id);
    }

    public void update(float delta) {

    }
}