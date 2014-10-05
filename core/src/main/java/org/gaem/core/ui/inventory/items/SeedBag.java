package org.gaem.core.ui.inventory.items;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.model.overworld.time.CropGrowthEvent;
import org.gaem.core.screen.OverworldScreen;
import org.gaem.core.util.TileUtils;

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
        TiledMapTile t = TileUtils.tileForPos(tile, "timeLayer");
        if (t != null && (t.getId() == TileUtils.TILLED_DRY_ID || t.getId() == TileUtils.TILLED_WET_ID)) {
            OverworldScreen.timeManager.addEvent(new CropGrowthEvent(5, (int) tile.x, (int) tile.y, 0));
        }
    }
}
