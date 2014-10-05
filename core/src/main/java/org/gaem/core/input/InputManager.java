package org.gaem.core.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import org.gaem.core.model.overworld.Player;
import org.gaem.core.model.overworld.time.CropGrowthEvent;
import org.gaem.core.screen.OverworldScreen;

import java.util.ArrayList;

/**
 * Created by Johan on 2014-09-27.
 */
public class InputManager {


    private Player player;
    private TiledMap map;
    private boolean inventoryPressed;
    private boolean hasPressedMovement;
    private boolean hasPressedMenu;

    //Control Codes
    private int CODE_KEY_UP = Input.Keys.UP;
    private int CODE_KEY_RIGHT = Input.Keys.RIGHT;
    private int CODE_KEY_DOWN = Input.Keys.DOWN;
    private int CODE_KEY_LEFT = Input.Keys.LEFT;
    private int CODE_KEY_INVENTORY = Input.Keys.I;
    private int CODE_KEY_MENU = Input.Keys.ENTER;
    private int CODE_KEY_SELECT = Input.Keys.ENTER;
    private int CODE_KEY_INTERACT = Input.Keys.SPACE;
    // Keys
    private InputKey KEY_UP;
    private InputKey KEY_RIGHT;
    private InputKey KEY_DOWN;
    private InputKey KEY_LEFT;
    private InputKey KEY_INVENTORY;
    private InputKey KEY_MENU;
    private InputKey KEY_INTERACT;
    private InputKey KEY_SELECT;

    private ArrayList<InputKey> inputKeys;

    private boolean LOLTEST = false;

    public InputManager(Player player, TiledMap map) {
        this.player = player;
        this.map = map;
        inventoryPressed = false;
        hasPressedMovement = false;
        hasPressedMenu = false;

        inputKeys = new ArrayList<InputKey>();

        KEY_UP = new InputKey(CODE_KEY_UP);
        inputKeys.add(KEY_UP);

        KEY_RIGHT = new InputKey(CODE_KEY_RIGHT);
        inputKeys.add(KEY_RIGHT);

        KEY_DOWN = new InputKey(CODE_KEY_DOWN);
        inputKeys.add(KEY_DOWN);

        KEY_LEFT = new InputKey(CODE_KEY_LEFT);
        inputKeys.add(KEY_LEFT);

        KEY_INVENTORY = new InputKey(CODE_KEY_INVENTORY);
        inputKeys.add(KEY_INVENTORY);

        KEY_MENU = new InputKey(CODE_KEY_MENU);
        inputKeys.add(KEY_MENU);

        KEY_INTERACT = new InputKey(CODE_KEY_INTERACT);
        inputKeys.add(KEY_INTERACT);

        KEY_SELECT = addNewKey(CODE_KEY_SELECT);


    }

    public void refresh() {
        this.map = OverworldScreen.mapManager.getCurrentMap();
        this.player = OverworldScreen.manager.getPlayer();
    }

    public void update(float delta) {
        for (InputKey key : inputKeys) {
            key.update(delta);
        }

        if (!OverworldScreen.inventoryWindow.showWindow) {
            handlePlayerInput();
        } else {
            if (KEY_LEFT.justPressed()) {
                OverworldScreen.inventoryWindow.cursorPosX--;
            }
            if (KEY_RIGHT.justPressed()) {
                OverworldScreen.inventoryWindow.cursorPosX++;
            }
            if (KEY_UP.justPressed()) {
                OverworldScreen.inventoryWindow.cursorPosY--;
            }
            if (KEY_DOWN.justPressed()) {
                OverworldScreen.inventoryWindow.cursorPosY++;
            }
        }


        if (KEY_MENU.justPressed()) {
            hasPressedMenu = true;
            if (!OverworldScreen.inventoryWindow.showWindow) {
                OverworldScreen.overworldMenuWindow.visible = !OverworldScreen.overworldMenuWindow.visible;
            }

        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (!LOLTEST) {
                OverworldScreen.timeManager.addEvent(new CropGrowthEvent(5, 14, 17, 0));
                LOLTEST = true;
            }
        }
        if (KEY_INVENTORY.justPressed()) {
            inventoryPressed = true;
            OverworldScreen.inventoryWindow.show();
        }

        if (KEY_SELECT.justPressed()) {
            if (OverworldScreen.inventoryWindow.showWindow) {
                OverworldScreen.inventoryWindow.hide();
            }

        }
    }

    private void handlePlayerInput() {
        if (KEY_LEFT.isPressing()) {
            player.move(-1, 0, map);
        }
        if (KEY_RIGHT.isPressing()) {
            player.move(1, 0, map);
        }
        if (KEY_UP.isPressing()) {
            player.move(0, 1, map);
        }
        if (KEY_DOWN.isPressing()) {
            player.move(0, -1, map);
        }
        if (KEY_INTERACT.timedPress(0.5f)) {
            player.interactWithFace();
        }
    }

    private InputKey addNewKey(int keyCode) {
        InputKey tempKey = new InputKey(keyCode);
        inputKeys.add(tempKey);

        return tempKey;
    }
}
