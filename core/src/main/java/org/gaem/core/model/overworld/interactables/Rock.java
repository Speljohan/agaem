package org.gaem.core.model.overworld.interactables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.model.overworld.Interactable;
import org.gaem.core.model.overworld.Player;

/**
 * Created by Johan on 2014-09-28.
 */
public class Rock extends Interactable {

    private Texture intT;
    private Sprite sp;

    public Rock(float x, float y) {
        super(x, y);
        intT = new Texture("assets/sprites/rock.png");
        sp = new Sprite(intT);
    }

    public void interact(Player player) {
        System.out.println("I AM INTERACTED WITH");
        Vector2 v = player.getLookDirection();
        tileY += (int) v.y;
        tileX += (int) v.x;
        realX += 16 * (int) v.x;
        realY += 16 * (int) v.y;
    }

    public void render(float delta, SpriteBatch batch) {
        batch.draw(sp, realX, realY);
    }


}
