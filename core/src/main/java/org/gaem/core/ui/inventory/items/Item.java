package org.gaem.core.ui.inventory.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Joar on 2014-10-02.
 */
public abstract class Item { //Bör kunna laddas in via Json.
    public int id;
    protected Sprite gfx;
  //  private SpriteBatch batch;
   // private Vector2 origin;

    public static final int ID_POWERGLOVE = 1;
    public static final int ID_AXE= 2;

    public Item() {
      //  this.batch = batch;
    }

    public abstract void update(int delta);

    public void render(int x, int y, Vector2 origin,SpriteBatch batch) {
        batch.draw(gfx.getTexture(), origin.x + x, origin.y + y);
    }
}
