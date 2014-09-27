package org.gaem.core.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.gaem.core.model.Player;

/**
 * Created by Johan on 2014-09-27.
 */
public class InputManager {

    private Player player;
    private TiledMap map;

    public InputManager(Player player, TiledMap map) {
        this.player = player;
        this.map = map;
    }

    public void update(float delta) {
        if ((Gdx.input.isKeyPressed(Input.Keys.LEFT)))
        {
            player.move(-1, 0, (TiledMapTileLayer)map.getLayers().get("MainLayer"));
        }
        if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT)))
        {
            player.move(1, 0, (TiledMapTileLayer)map.getLayers().get("MainLayer"));
        }
        if ((Gdx.input.isKeyPressed(Input.Keys.UP)))
        {
            player.move(0, 1, (TiledMapTileLayer)map.getLayers().get("MainLayer"));
        }
        if ((Gdx.input.isKeyPressed(Input.Keys.DOWN)))
        {

            player.move(0, -1, (TiledMapTileLayer)map.getLayers().get("MainLayer"));
        }
    }
}
