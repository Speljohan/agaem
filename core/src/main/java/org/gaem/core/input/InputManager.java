package org.gaem.core.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.gaem.core.model.overworld.Player;
import org.gaem.core.screen.OverworldScreen;

/**
 * Created by Johan on 2014-09-27.
 */
public class InputManager {

    private Player player;
    private TiledMap map;
    private boolean enterPressed;
    private boolean hasPressedArrow;

    public InputManager(Player player, TiledMap map) {
        this.player = player;
        this.map = map;
        enterPressed = false;
        hasPressedArrow = false;
    }

    public void refresh() {
        this.map = OverworldScreen.mapManager.getCurrentMap();
        this.player = OverworldScreen.manager.getPlayer();
    }

    public void update(float delta) {

        if(!OverworldScreen.inventoryWindow.showWindow) {
            handlePlayerInput();
        }
        else{
            if(!hasPressedArrow) {
                if ((Gdx.input.isKeyPressed(Input.Keys.LEFT))) {
                    OverworldScreen.inventoryWindow.cursorPosX--;
                }
                if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT))) {
                    OverworldScreen.inventoryWindow.cursorPosX++;
                }
                if ((Gdx.input.isKeyPressed(Input.Keys.UP))) {
                    OverworldScreen.inventoryWindow.cursorPosY--;
                }
                if ((Gdx.input.isKeyPressed(Input.Keys.DOWN))) {
                    OverworldScreen.inventoryWindow.cursorPosY++;
                }
                hasPressedArrow = true;
            }
        }

        if((!Gdx.input.isKeyPressed(Input.Keys.ENTER))){
            enterPressed = false;
        }

        if((Gdx.input.isKeyPressed(Input.Keys.ENTER)) && !enterPressed){
            enterPressed = true;
            OverworldScreen.inventoryWindow.toggle();
        }

        if((!Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.DOWN) && !Gdx.input.isKeyPressed(Input.Keys.LEFT))){
            hasPressedArrow = false;
        }
    }

    private void handlePlayerInput() {
        if ((Gdx.input.isKeyPressed(Input.Keys.LEFT))) {
            player.move(-1, 0, (TiledMapTileLayer) map.getLayers().get("MainLayer"));
        }
        if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT))) {
            player.move(1, 0, (TiledMapTileLayer) map.getLayers().get("MainLayer"));
        }
        if ((Gdx.input.isKeyPressed(Input.Keys.UP))) {
            player.move(0, 1, (TiledMapTileLayer) map.getLayers().get("MainLayer"));
        }
        if ((Gdx.input.isKeyPressed(Input.Keys.DOWN))) {
            player.move(0, -1, (TiledMapTileLayer) map.getLayers().get("MainLayer"));
        }
        if ((Gdx.input.isKeyPressed(Input.Keys.SPACE))) {
            player.interactWithFace();
        }
    }
}
