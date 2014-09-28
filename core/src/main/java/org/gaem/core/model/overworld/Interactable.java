package org.gaem.core.model.overworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Joar on 2014-09-28.
 */
public class Interactable extends Entity {

    private Texture intT;
    private Sprite sp;

    public Interactable(float x, float y){
        intT = new Texture("assets/sprites/rock.png");
        sp = new Sprite(intT);
        realX = x;
        realY = y;
        tileX = MathUtils.floor(realX/16);
        tileY = MathUtils.floor(realY/16);
    }

    @Override
    public void render(float delta, SpriteBatch batch) {
        batch.draw(sp,realX,realY);
    }


    public void interact(Player player) {
        System.out.println("I AM INTERACTED WITH");
        tileX += 1;
        realX += 16;
    }

    public void  update(float delta){

    }
}
