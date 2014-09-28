package org.gaem.core.model.overworld;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Johan on 2014-09-28.
 */
public abstract class Triggerable extends Entity {

    public Triggerable(float x, float y) {
        realX = x;
        realY = y;
        tileX = MathUtils.floor(realX / 16);
        tileY = MathUtils.floor(realY / 16);
    }

    public void render(float delta, SpriteBatch batch) {

    }

    public void interact(Player player) {

    }

    public abstract void trigger(Player player);
}
