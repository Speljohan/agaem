package org.gaem.core.ui.inventory.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.AGame;

/**
 * Created by Joar on 2014-10-02.
 */
public class PowerGlove extends Item{


    public PowerGlove() {
        super();
        gfx = new Sprite(AGame.ASSETS.get("sprites/items/powerglove.png", Texture.class));
        id = ID_POWERGLOVE;
    }

    @Override
    public void update(int delta) {

    }
}
