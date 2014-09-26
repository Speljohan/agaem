package org.gaem.core.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.gaem.core.AGame;

/**
 * Created by Johan on 2014-09-26.
 */
public class Player extends Mobile {

    public Sprite sprite;

    public Player() {
        this.sprite = new Sprite(AGame.ASSETS.get("sprites/player.png", Texture.class));
    }

    public void update() {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.x -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.x += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.y += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.y -= 1;
        }
    }

}
