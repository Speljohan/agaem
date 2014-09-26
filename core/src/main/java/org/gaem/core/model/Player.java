package org.gaem.core.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.AGame;
import org.gaem.core.util.TileUtils;

/**
 * Created by Johan on 2014-09-26.
 */
public class Player extends Mobile {

    public Sprite sprite;
    private boolean isMoving;

    public Player() {
        this.sprite = new Sprite(AGame.ASSETS.get("sprites/player.png", Texture.class));
        this.velocity = new Vector2();
        this.isMoving = false;
    }

    public void update(float delta) {

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                move(-1, 0);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                move(1, 0);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                move(0, 1);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                move(0, -1);
            }


        if (isMoving) {
            Vector2 v = TileUtils.tileForPos(realX, realY);
            System.out.println(v);
            if (v.x == targetX && v.y == targetY) {
                this.tileX = targetX;
                this.tileY = targetY;
                this.velocity.set(0, 0);
                isMoving = false;
                return;
            }
            this.realX += velocity.x;
            this.realY += velocity.y;
        }

    }


    private void move(int x, int y) {
        if (isMoving) return;
        this.targetX = tileX + x;
        this.targetY = tileY + y;
        velocity.set(x, y);
        isMoving = true;
    }

}
