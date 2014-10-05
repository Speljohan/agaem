package org.gaem.core.ui.inventory.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.AGame;
import org.gaem.core.model.overworld.time.CropGrowthEvent;
import org.gaem.core.screen.OverworldScreen;

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

    @Override
    public void useAt(Vector2 tile) {
        OverworldScreen.timeManager.addEvent(new CropGrowthEvent(5, (int) tile.x, (int) tile.y, 0));
    }
}
