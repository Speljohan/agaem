package org.gaem.core.ui.inventory.items;

import com.badlogic.gdx.math.Vector2;
import org.gaem.core.model.overworld.time.TileTransformEvent;
import org.gaem.core.screen.OverworldScreen;
import org.gaem.core.util.TileUtils;

/**
 * Created by Johan on 2014-10-05.
 */
public class Hoe extends Item {

    public Hoe() {
        super(ID_HOE);
    }

    @Override
    public void update(int delta) {

    }

    @Override
    public void useAt(Vector2 tile) {
        OverworldScreen.timeManager.addEvent(new TileTransformEvent((int) tile.x, (int) tile.y, TileUtils.TILLED_DRY_ID));
    }
}
