package org.gaem.core.model.overworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.AGame;
import org.gaem.core.engine.SpriteSheet;
import org.gaem.core.screen.OverworldScreen;
import org.gaem.core.ui.inventory.items.Axe;
import org.gaem.core.ui.inventory.items.Item;
import org.gaem.core.ui.inventory.items.PowerGlove;
import org.gaem.core.ui.inventory.items.SeedBag;
import org.gaem.core.util.TileUtils;

import java.util.ArrayList;

/**
 * Created by Johan on 2014-09-26.
 */
public class Player extends Mobile {

    public ArrayList<Item> itemList;
    public Item activeItem;
    private float elapsed;
    private boolean isMoving;
    private float speed;
    private boolean moveBool;

    public Player(float x, float y) {
        super(new SpriteSheet(AGame.ASSETS.get("sprites/player_newnew.png", Texture.class), 16, 16));
        solid = true;
        tileX = MathUtils.floor(x / 16);
        tileY = MathUtils.floor(y / 16);

        realX = x;
        realY = y;

        moveBool = true;

        speed = 0.1f;

        elapsed = 0;
        this.velocity = new Vector2();
        this.isMoving = false;

        itemList = new ArrayList<Item>();
        itemList.add(new PowerGlove());
        itemList.add(new Axe());
        itemList.add(new SeedBag());

    }

    public void update(float delta) {
        super.update(delta);
        elapsed += delta;
        this.idle = !isMoving;

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
                for (Entity e : manager.getEntities()) {
                    if ((e instanceof Triggerable) && e.tileX == tileX && e.tileY == tileY) {
                        ((Triggerable) e).trigger(this);
                    }
                }
                return;
            }

            this.realX += velocity.x;
            this.realY += velocity.y;
        }

    }

    private boolean canMove(int targetX, int targetY, TiledMap map) {
        if (TileUtils.isBlocked(targetX, targetY, map)) {
            return false;
        }
        for (Entity e : manager.getEntities())
        {
            if (e.tileX == targetX && e.tileY == targetY)
            {
                return e instanceof Triggerable;
            }
        }
        return true;
    }

    public void setLocation(float x, float y) {
        tileX = MathUtils.floor(x / 16);
        tileY = MathUtils.floor(y / 16);

        realX = x;
        realY = y;
    }

    public Vector2 getLookDirection() {
        int x = 0, y = 0;
        switch (facing) {
            case 0:
                y += 1;
                break;
            case 1:
                x += 1;
                break;
            case 2:
                y -= 1;
                break;
            case 3:
                x -= 1;
                break;
        }
        return new Vector2(x, y);
    }

    public Vector2 getLookTile() {
        int x = tileX, y = tileY;
        switch (facing) {
            case 0:
                y += 1;
                break;
            case 1:
                x += 1;
                break;
            case 2:
                y -= 1;
                break;
            case 3:
                x -= 1;
                break;
        }
        return new Vector2(x, y);
    }

    private void updateFacing(int x, int y) {
        if (y == 1) facing = 0;
        if (x == 1) facing = 1;
        if (y == -1) facing = 2;
        if (x == -1) facing = 3;
    }

    private Entity getTargetAtFace() {
        int x = tileX, y = tileY;
        switch (facing) {
            case 0:
                y += 1;
                break;
            case 1:
                x += 1;
                break;
            case 2:
                y -= 1;
                break;
            case 3:
                x -= 1;
                break;
        }

        for (Entity e : manager.getEntities()) {
            if (e.tileX == x && e.tileY == y) {
                return e;
            }
        }

        return null;
    }

    public void interact(Player player) {

    }

    public void interactWithFace() {
        Entity mobile = getTargetAtFace();

        if (mobile != null) {
            mobile.interact(this);
        } else {
            activeItem.useAt(getLookTile());
        }
    }

    public void move(int x, int y, TiledMap map) {
        updateFacing(x, y);
        if (isMoving) return;
        if (moveBool) {
            if (!canMove(tileX + x, tileY + y, map)) return;
            OverworldScreen.DIALOGUEMANAGER.hideDialogue();
            this.targetX = tileX + x;
            this.targetY = tileY + y;
            velocity.set(x, y);
            isMoving = true;
            moveBool = false;
            elapsed = 0;
        }
    }

}
