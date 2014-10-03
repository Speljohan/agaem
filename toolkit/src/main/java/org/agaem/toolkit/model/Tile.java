package org.agaem.toolkit.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Johan on 2014-10-03.
 */
public class Tile {

    private int tileIdx;

    public Tile(int tileIdx) {
        this.tileIdx = tileIdx;
    }

    public Tile() {
        this.tileIdx = -1;
    }

    public int getTileIdx() {
        return tileIdx;
    }

    public void render(float delta, int x, int y, int tileWidth, int tileHeight, SpriteBatch batch) {
        if (tileIdx != -1) {
            batch.draw((Texture) null, x * tileWidth, y * tileHeight);
        }
    }
}
