package org.gaem.core.model.overworld;

import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Joar on 2014-09-28.
 */
public abstract class Interactable extends Entity {

    public Interactable(float x, float y){
        realX = x;
        realY = y;
        tileX = MathUtils.floor(realX/16);
        tileY = MathUtils.floor(realY/16);
    }

    public void  update(float delta){

    }
}
