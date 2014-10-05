package org.gaem.core.ui.inventory.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.AGame;
import org.gaem.core.engine.SpriteSheet;

/**
 * Created by Joar on 2014-10-02.
 */
public abstract class Item { //Bör kunna laddas in via Json.
    public static final int ID_AXE = 0;
    public static final int ID_POWERGLOVE = 1;
    public static final int ID_SEEDBAG = 2;
    public static final int ID_HOE = 3;
    public static final int ID_WATERINGCAN = 4;

    public int id;
    private SpriteSheet sprites;

    public Item(int id) {
        this.id = id;
        sprites = new SpriteSheet(AGame.ASSETS.get("sprites/items.png", Texture.class), 16, 16);
    }

    public abstract void update(int delta);

    public void render(int x, int y, Vector2 origin, SpriteBatch batch) {
        batch.draw(sprites.get(id), origin.x + x, origin.y + y);
    }

    public abstract void useAt(Vector2 tile);
}
