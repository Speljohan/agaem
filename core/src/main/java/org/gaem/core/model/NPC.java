package org.gaem.core.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.AGame;

/**
 * Created by Joar on 2014-09-27.
 */
public class NPC extends Mobile {

   public Sprite sprite;

    public NPC(float x, float y) {
        tileX = MathUtils.floor(x / 16);
        tileY = MathUtils.floor(y / 16);

        realX = x;
        realY = y;


        this.sprite = new Sprite(AGame.ASSETS.get("sprites/player.png", Texture.class));
        this.sprite.setCenter(16, 16);
       // this.velocity = new Vector2();
    }

    public void update(float delta) {

    }
}
