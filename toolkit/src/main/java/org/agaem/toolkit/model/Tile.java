package org.agaem.toolkit.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Johan on 2014-10-03.
 */
public class Tile {

    private int spriteIdx;

    public Tile(int spriteIdx) {
        this.spriteIdx = spriteIdx;
    }

    public Tile() {
        this.spriteIdx = -1;
    }

    public void render(float delta, int x, int y, int tileWidth, int tileHeight, SpriteBatch batch) {
        if (spriteIdx != -1) {
            batch.draw((Texture) null, x * tileWidth, y * tileHeight);
        }
    }
}
