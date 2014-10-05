package org.gaem.core.ui.inventory.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.AGame;

/**
 * Created by Joar on 2014-10-02.
 */
public class Axe extends Item{
    public Axe() {
        super();
        gfx = new Sprite(AGame.ASSETS.get("sprites/items/axe.png", Texture.class));
        id = ID_AXE;
    }

    @Override
    public void update(int delta) {

    }

    @Override
    public void useAt(Vector2 tile) {

    }
}
