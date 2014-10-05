package org.gaem.core.ui.inventory.items;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.AGame;
import org.gaem.core.model.overworld.time.TileTransformEvent;
import org.gaem.core.screen.OverworldScreen;
import org.gaem.core.util.TileUtils;

/**
 * Created by Johan on 2014-10-05.
 */
public class WateringCan extends Item {

    private int charges = 0;
    private BitmapFont font;

    public WateringCan() {
        super(ID_WATERINGCAN);
        this.font = AGame.ASSETS.get("fonts/SILKWONDER.fnt", BitmapFont.class);
    }

    @Override
    public void update(int delta) {
    }

    @Override
    public void render(int x, int y, Vector2 origin, SpriteBatch batch) {
        super.render(x, y, origin, batch);
        font.setScale(0.2f);
        font.setColor(Color.BLACK);
        font.draw(batch, String.valueOf(charges), (origin.x + x) + 8, (origin.y + y) + 8);
    }

    @Override
    public void useAt(Vector2 tile) {
        TiledMapTile timeTile = TileUtils.tileForPos(tile, "timeLayer");
        TiledMapTile mainTile = TileUtils.tileForPos(tile, "MainLayer");
        if (timeTile != null) {
            int id = timeTile.getId();

            if (id == TileUtils.TILLED_DRY_ID && charges >= 1) {
                OverworldScreen.timeManager.addEvent(new TileTransformEvent((int) tile.x, (int) tile.y, TileUtils.TILLED_WET_ID));
                charges--;
                charges = MathUtils.clamp(charges, 0, 5);
                return;
            }
        }
        if (mainTile != null) {
            if (mainTile.getProperties().containsKey("type") && mainTile.getProperties().get("type").equals("water")) {
                this.charges += 1;
                charges = MathUtils.clamp(charges, 0, 5);
            }
        }
    }
}
