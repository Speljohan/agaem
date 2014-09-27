package org.gaem.core.model.overworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.AGame;
import org.gaem.core.screen.OverworldScreen;
import org.gaem.core.util.TileUtils;

import java.util.ArrayList;

/**
 * Created by Johan on 2014-09-26.
 */
public class Player extends Mobile {

    private float elapsed;
    private boolean isMoving;
    private float speed;
    private boolean moveBool;
    private ArrayList<NPC> npcList;

    public Player(float x, float y, ArrayList<NPC> npcList) {
        super(AGame.ASSETS.get("sprites/player.pack", TextureAtlas.class));
        this.npcList = npcList;
        tileX = MathUtils.floor(x / 16);
        tileY = MathUtils.floor(y / 16);

        realX = x;
        realY = y;

        moveBool = true;

        speed = 0.1f;

        elapsed = 0;
        this.velocity = new Vector2();
        this.isMoving = false;
    }

    public void update(float delta) {
        super.update(delta);
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
        System.out.println("CAN MOVE; " + OverworldScreen.mapHeigth + " Target Y " + targetY + " Player pos" + realY);
       if ((targetX < 0 || targetX >= OverworldScreen.mapWidth/16 )|| (targetY < 0|| targetY >= OverworldScreen.mapHeigth/16 )) {
            return false;
        }
        System.out.println(layer.getCell(targetX, targetY).getTile().getId());
        if (layer.getCell(targetX, targetY) == null || TileUtils.isBlocked(layer.getCell(targetX, targetY).getTile().getId())) {
            return false;
        }
        for (NPC npc: npcList)
        {
            if(npc.tileX == targetX && npc.tileY == targetY)
            {
                return false;
            }
        }
        return true;
    }

    private void updateFacing(int x, int y) {
        if (y == 1) facing = 0;
        if (x == 1) facing = 1;
        if (y == -1) facing = 2;
        if (x == -1) facing = 3;
    }

    private Mobile getTargetAtFace() {
        int x = tileX, y = tileY;
        switch (facing) {
            case 0:
                y += 1;
            case 1:
                x += 1;
            case 2:
                y -= 1;
            case 3:
                y += 1;
        }

        for (NPC npc : npcList) {
            if (npc.tileX == x && npc.tileY == y) {
                return npc;
            }
        }
        return null;
    }

    public void interact() {

    }

    public void interactWithFace() {
        Mobile mobile = getTargetAtFace();

        if (mobile != null) {
            mobile.interact();
        }
    }

    public void move(int x, int y, TiledMapTileLayer layer) {
        updateFacing(x, y);
        if (isMoving) return;
        if (moveBool) {
            if (!canMove(tileX + x, tileY + y, layer)) return;
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
