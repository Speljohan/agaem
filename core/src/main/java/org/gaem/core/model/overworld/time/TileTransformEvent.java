package org.gaem.core.model.overworld.time;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.gaem.core.screen.OverworldScreen;

/**
 * Created by Johan on 2014-10-04.
 */
public class TileTransformEvent extends SingleExecutionTimedEvent {

    private int x, y, tileId;

    public TileTransformEvent(int x, int y, int tileId) {
        super();
        this.x = x;
        this.y = y;
        this.tileId = tileId;
    }

    public void execute() {
        TiledMapTileLayer layer = (TiledMapTileLayer) OverworldScreen.mapManager.currentMap.getLayers().get("timeLayer");
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(OverworldScreen.mapManager.currentMap.getTileSets().getTile(tileId));
        layer.setCell(x, y, cell);
    }
}
