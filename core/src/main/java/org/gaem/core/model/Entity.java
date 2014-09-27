package org.gaem.core.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Johan on 2014-09-26.
 */
public abstract class Entity {

    public int tileX, tileY;
    public float realX, realY;

    public abstract void render(float delta, SpriteBatch batch);

}
