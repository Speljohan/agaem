package org.gaem.core.model.overworld;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Johan on 2014-09-26.
 */
public abstract class Entity {

    public int tileX, tileY;
    public float realX, realY;

    public abstract void render(float delta, SpriteBatch batch);

    public void update(float delta) {

    }
    public abstract void interact(Player player);

}
