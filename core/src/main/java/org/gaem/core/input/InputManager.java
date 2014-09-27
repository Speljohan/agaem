package org.gaem.core.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.gaem.core.model.Player;

/**
 * Created by Johan on 2014-09-27.
 */
public class InputManager implements InputProcessor {

    private Player player;
    private TiledMap map;

    public InputManager(Player player, TiledMap map) {
        this.player = player;
        this.map = map;
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            player.move(-1, 0, (TiledMapTileLayer)map.getLayers().get(0));
        }
        if (keycode == Input.Keys.RIGHT) {
            player.move(1, 0, (TiledMapTileLayer)map.getLayers().get(0));
        }
        if (keycode == Input.Keys.DOWN) {
            player.move(0, 1, (TiledMapTileLayer)map.getLayers().get(0));
        }
        if (keycode == Input.Keys.UP) {
            player.move(0, -1, (TiledMapTileLayer)map.getLayers().get(0));
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void update(float delta) {

    }
}
