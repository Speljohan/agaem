package org.gaem.core.util;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.screen.OverworldScreen;

/**
 * Created by Johan on 2014-09-27.
 */
public class TileUtils {

    public static final int TILLED_ID = 222;

    public static boolean isBlocked(int targetX, int targetY, TiledMap map) {

        if ((targetX < 0 || targetX >= OverworldScreen.mapManager.width / 16) || (targetY < 0 || targetY >= OverworldScreen.mapManager.height / 16))
            return true;

        for (MapLayer l : map.getLayers()) {
            if (l instanceof TiledMapTileLayer) {
                TiledMapTileLayer tl = (TiledMapTileLayer) l;
                if (tl.getCell(targetX, targetY) == null) // Assume not blocked
                    return false;

                MapProperties props = tl.getCell(targetX, targetY).getTile().getProperties();
                return props.containsKey("solid") && Boolean.valueOf(String.valueOf(props.get("solid")));
            }
        }
        return true;
    }

    public static TiledMapTile tileForPos(Vector2 location) {
        TiledMapTileLayer layer = (TiledMapTileLayer) OverworldScreen.mapManager.currentMap.getLayers().get("timeLayer");
        if (layer.getCell((int) location.x, (int) location.y) != null) {
            return layer.getCell((int) location.x, (int) location.y).getTile();
        }
        return null;
    }

}
