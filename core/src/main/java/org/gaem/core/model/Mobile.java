package org.gaem.core.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Johan on 2014-09-26.
 */
public abstract class Mobile extends Entity {

    public int facing = 0;
    public int targetX, targetY;
    public Vector2 velocity;

    public abstract void interact();


}
