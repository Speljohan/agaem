package org.gaem.core.model.overworld.time;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import org.gaem.core.screen.OverworldScreen;

/**
 * Created by Johan on 2014-10-04.
 */
public class TileTransformEvent extends TimedEvent {

    private int x, y, tileId;

    public TileTransformEvent(int x, int y, int tileId, float interval) {
        super(interval);
        this.x = x;
        this.y = y;
        this.tileId = tileId;
    }

    @Override
    public void onTrigger() {
        TiledMapTileLayer layer = (TiledMapTileLayer) OverworldScreen.mapManager.currentMap.getLayers().get("timeLayer");
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(OverworldScreen.mapManager.currentMap.getTileSets().getTile(MathUtils.random(1, 3)));
        layer.setCell(x, y, cell);
    }
}
