package org.gaem.core.ui.inventory.items;

import com.badlogic.gdx.math.Vector2;
import org.gaem.core.model.overworld.time.CropGrowthEvent;
import org.gaem.core.screen.OverworldScreen;

/**
 * Created by Johan on 2014-10-05.
 */
public class SeedBag extends Item {

    public SeedBag() {
        super(ID_SEEDBAG);
    }

    @Override
    public void update(int delta) {

    }

    @Override
    public void useAt(Vector2 tile) {
        OverworldScreen.timeManager.addEvent(new CropGrowthEvent(5, (int) tile.x, (int) tile.y, 0));
    }
}
