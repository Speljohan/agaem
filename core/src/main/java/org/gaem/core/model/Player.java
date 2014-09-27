package org.gaem.core.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.AGame;
import org.gaem.core.util.TileUtils;

/**
 * Created by Johan on 2014-09-26.
 */
public class Player extends Mobile {

    public Sprite sprite;
    private float elapsed;
    private boolean isMoving;
    private float speed;
    private boolean moveBool;

    public Player(float x, float y) {
        tileX = MathUtils.floor(x / 16);
        tileY = MathUtils.floor(y / 16);

        realX = x;
        realY = y;

        moveBool = true;

        speed = 0.1f;

        elapsed = 0;

        this.sprite = new Sprite(AGame.ASSETS.get("sprites/player.png", Texture.class));
        this.sprite.setCenter(16, 16);
        this.velocity = new Vector2();
        this.isMoving = false;
    }

    public void update(float delta) {
        elapsed += Gdx.graphics.getDeltaTime();
        if (elapsed > speed) {
            moveBool = true;
            elapsed -= speed;
        }
        if (isMoving) {
            if (realX == targetX * 16 && realY == targetY * 16) {
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

    private boolean canMove(int targetX, int targetY, TiledMapTileLayer layer) {
        if (targetX < 0 || targetY < 0) {
            return false;
        }
        System.out.println(layer.getCell(targetX, targetY).getTile().getId());
        if (layer.getCell(targetX, targetY) == null || TileUtils.isBlocked(layer.getCell(targetX, targetY).getTile().getId())) {
            return false;
        }
        return true;
    }


    public void move(int x, int y, TiledMapTileLayer layer) {
        if (isMoving) return;
        if (moveBool) {
            if (!canMove(tileX + x, tileY + y, layer)) return;
            this.targetX = tileX + x;
            this.targetY = tileY + y;
            velocity.set(x, y);
            isMoving = true;
            moveBool = false;
            elapsed = 0;
        }
    }

}
