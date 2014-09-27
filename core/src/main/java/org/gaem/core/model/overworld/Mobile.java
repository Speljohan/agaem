package org.gaem.core.model.overworld;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Johan on 2014-09-26.
 */
public abstract class Mobile extends Entity {

    public int facing = 0;
    public int targetX, targetY;
    public Vector2 velocity;

    public Animation[] animations;
    public Animation currentAnimation;

    public Mobile(TextureAtlas atlas) {
        loadAnimation(atlas);
        this.currentAnimation = animations[0];
        this.currentAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    protected void loadAnimation(TextureAtlas atlas) {
        animations = new Animation[4];
        animations[0] = new Animation(500, atlas.findRegion("up0"), atlas.findRegion("up1"));
        animations[1] = new Animation(500, atlas.findRegion("right0"), atlas.findRegion("right1"));
        animations[2] = new Animation(500, atlas.findRegion("down0"), atlas.findRegion("down1"));
        animations[3] = new Animation(500, atlas.findRegion("left0"), atlas.findRegion("left1"));
    }

    public void render(float delta, SpriteBatch batch) {
        batch.draw(currentAnimation.getKeyFrame(delta), realX, realY);
    }

    public abstract void interact();

    public void update(float delta) {
        currentAnimation = animations[facing];
        currentAnimation.setPlayMode(Animation.PlayMode.LOOP);

    }


}
