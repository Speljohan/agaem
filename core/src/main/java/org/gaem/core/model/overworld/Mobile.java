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
    protected boolean idle;

    public Mobile(SpriteSheet atlas) {
        loadAnimation(atlas);
        this.currentAnimation = animations[0];
        this.currentAnimation.setPlayMode(Animation.PlayMode.LOOP);
        this.elapsed = 0;
        this.idle = true;
    }

    protected void loadAnimation(SpriteSheet atlas) {
        animations = new Animation[4];
        animations[0] = new Animation(0.1f, atlas.get(0), atlas.get(1), atlas.get(2), atlas.get(3));
        animations[1] = new Animation(0.1f, atlas.get(4), atlas.get(5), atlas.get(6), atlas.get(7));
        animations[2] = new Animation(0.1f, atlas.get(8), atlas.get(9), atlas.get(10), atlas.get(11));
        animations[3] = new Animation(0.1f, atlas.get(12), atlas.get(13), atlas.get(14), atlas.get(15));
    }

    public void render(float delta, SpriteBatch batch) {
        batch.draw(currentAnimation.getKeyFrame(elapsed), realX, realY);
    }



    public void update(float delta) {
        if (!idle) {
            this.elapsed += delta;
            if (animations[facing] != currentAnimation) {
                currentAnimation = animations[facing];
                currentAnimation.setPlayMode(Animation.PlayMode.LOOP);
            }                    }

    }


}
