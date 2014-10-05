package org.gaem.core.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import org.gaem.core.model.overworld.Player;
import org.gaem.core.model.overworld.time.TileTransformEvent;
import org.gaem.core.screen.OverworldScreen;

/**
 * Created by Johan on 2014-09-27.
 */
public class InputManager {


    private Player player;
    private TiledMap map;
    private boolean inventoryPressed;
    private boolean hasPressedMovement;
    private boolean hasPressedMenu;

    //Controls
    private int KEY_UP = Input.Keys.UP;
    private int KEY_RIGHT = Input.Keys.RIGHT;
    private int KEY_DOWN = Input.Keys.DOWN;
    private int KEY_LEFT = Input.Keys.LEFT;
    private int KEY_INVENTORY = Input.Keys.I;
    private int KEY_MENU = Input.Keys.ENTER;
    private int KEY_INTERACT = Input.Keys.SPACE;
    private boolean LOLTEST = false;
    public InputManager(Player player, TiledMap map) {
        this.player = player;
        this.map = map;
        inventoryPressed = false;
        hasPressedMovement = false;
        hasPressedMenu = false;

    }

    public void refresh() {
        this.map = OverworldScreen.mapManager.getCurrentMap();
        this.player = OverworldScreen.manager.getPlayer();
    }

    public void update(float delta) {
        //refresh();
        if(!OverworldScreen.inventoryWindow.showWindow) {
            handlePlayerInput();
        }
        else{
            if(!hasPressedMovement) {
                if ((Gdx.input.isKeyPressed(KEY_LEFT))) {
                    OverworldScreen.inventoryWindow.cursorPosX--;
                }
                if ((Gdx.input.isKeyPressed(KEY_RIGHT))) {
                    OverworldScreen.inventoryWindow.cursorPosX++;
                }
                if ((Gdx.input.isKeyPressed(KEY_UP))) {
                    OverworldScreen.inventoryWindow.cursorPosY--;
                }
                if ((Gdx.input.isKeyPressed(KEY_DOWN))) {
                    OverworldScreen.inventoryWindow.cursorPosY++;
                }
                hasPressedMovement = true;
            }
        }

        if((!Gdx.input.isKeyPressed(KEY_INVENTORY))){
            inventoryPressed = false;
        }

        if((!Gdx.input.isKeyPressed(KEY_MENU))){
            hasPressedMenu = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (!LOLTEST) {
                OverworldScreen.timeManager.addEvent(new TileTransformEvent(18, 15, 5, 1));
                LOLTEST = true;
            }
        }
        if((Gdx.input.isKeyPressed(KEY_INVENTORY)) && !inventoryPressed){
            inventoryPressed = true;
            OverworldScreen.inventoryWindow.toggle();
        }

        if((Gdx.input.isKeyPressed(KEY_MENU)) && !hasPressedMenu){
            hasPressedMenu = true;
            OverworldScreen.overworldMenuWindow.visible = !OverworldScreen.overworldMenuWindow.visible;
        }

        if(!(Gdx.input.isKeyPressed(KEY_UP) || Gdx.input.isKeyPressed(KEY_RIGHT) || Gdx.input.isKeyPressed(KEY_DOWN) || Gdx.input.isKeyPressed(KEY_LEFT))){
            hasPressedMovement = false;
        }
    }

    private void handlePlayerInput() {
        if ((Gdx.input.isKeyPressed(KEY_LEFT))) {
            player.move(-1, 0, map);
        }
        if ((Gdx.input.isKeyPressed(KEY_RIGHT))) {
            player.move(1, 0, map);
        }
        if ((Gdx.input.isKeyPressed(KEY_UP))) {
            player.move(0, 1, map);
        }
        if ((Gdx.input.isKeyPressed(KEY_DOWN))) {
            player.move(0, -1, map);
        }
        if ((Gdx.input.isKeyPressed(KEY_INTERACT))) {
            player.interactWithFace();
        }
    }
}
