package org.gaem.core.model.overworld;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.engine.SpriteSheet;

/**
 * Created by Johan on 2014-09-26.
 */
public abstract class Mobile extends Entity {

    public int facing = 0;
    public int targetX, targetY;
    public Vector2 velocity;

    public Animation[] animations;
    public Animation currentAnimation;
    private float elapsed;

    public Mobile(SpriteSheet atlas) {
        loadAnimation(atlas);
        this.currentAnimation = animations[0];
        this.currentAnimation.setPlayMode(Animation.PlayMode.LOOP);
        this.elapsed = 0;
    }

    protected void loadAnimation(SpriteSheet atlas) {
        animations = new Animation[4];
        animations[0] = new Animation(0.5f, atlas.get(0), atlas.get(1));
        animations[1] = new Animation(0.5f, atlas.get(2), atlas.get(3));
        animations[2] = new Animation(0.5f, atlas.get(4), atlas.get(5));
        animations[3] = new Animation(0.5f, atlas.get(6), atlas.get(7));
    }

    public void render(float delta, SpriteBatch batch) {
        batch.draw(currentAnimation.getKeyFrame(elapsed), realX, realY);
    }



    public void update(float delta) {
        this.elapsed += delta;
        if (animations[facing] != currentAnimation) {
            currentAnimation = animations[facing];
            currentAnimation.setPlayMode(Animation.PlayMode.LOOP);
        }
    }


}
