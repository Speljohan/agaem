package org.gaem.core.model.overworld.interactables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.model.overworld.Interactable;
import org.gaem.core.model.overworld.Player;
import org.gaem.core.screen.OverworldScreen;
import org.gaem.core.ui.inventory.items.Item;
import org.gaem.core.util.TileUtils;

/**
 * Created by Johan on 2014-09-28.
 */
public class Rock extends Interactable {

    private Texture intT;
    private Sprite sp;

    public Rock(float x, float y) {
        super(x, y);
        solid = true;
        intT = new Texture("assets/sprites/rock.png");
        sp = new Sprite(intT);
    }

    public void interact(Player player) {
        if(player.activeItem.id == Item.ID_POWERGLOVE) {
            TiledMapTileLayer layer = (TiledMapTileLayer) OverworldScreen.mapManager.currentMap.getLayers().get("MainLayer");
            System.out.println("I AM INTERACTED WITH");
            Vector2 v = player.getLookDirection();
            if (manager.getEntityAt((int) (tileX + v.x), (int) (tileY + v.y)) != null) {
                return;
            }
            if (layer.getCell((int) (tileX + v.x), (int) (tileY + v.y)) == null || TileUtils.isBlocked((int) (tileX + v.x), (int) (tileY + v.y), OverworldScreen.mapManager.currentMap)) {
                return;
            }
            tileY += (int) v.y;
            tileX += (int) v.x;
            realX += 16 * (int) v.x;
            realY += 16 * (int) v.y;
        }
    }

    public void render(float delta, SpriteBatch batch) {
        batch.draw(sp, realX, realY);
    }


}
