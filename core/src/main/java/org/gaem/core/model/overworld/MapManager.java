package org.gaem.core.model.overworld;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import org.gaem.core.engine.PlayerData;
import org.gaem.core.model.overworld.interactables.Rock;
import org.gaem.core.model.overworld.triggerables.ScriptTrigger;
import org.gaem.core.screen.OverworldScreen;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Johan on 2014-09-28.
 */
public class MapManager {

    public TiledMap currentMap;
    public int width, height;
    public TiledMapRenderer tiledMapRenderer;
    private OverworldScreen screen;

    public MapManager(OverworldScreen screen) {
        this.screen = screen;
    }

    public TiledMap getCurrentMap() {
        return currentMap;
    }

    public void loadMap(String mapName) {
        OverworldScreen.manager.clear();
        currentMap = new TmxMapLoader().load("map/" + mapName + ".tmx");
        TiledMapTileLayer timeLayer = new TiledMapTileLayer((Integer) currentMap.getProperties().get("width"), (Integer) currentMap.getProperties().get("height"), 16, 16);
        timeLayer.setName("timeLayer");
        currentMap.getLayers().add(timeLayer);
        width = (Integer) currentMap.getProperties().get("width") * 16;
        height = (Integer) currentMap.getProperties().get("height") * 16;
        tiledMapRenderer = new OrthogonalTiledMapRenderer(currentMap);

        MapLayer mapLayer = currentMap.getLayers().get("obte");

        MapObjects mo = mapLayer.getObjects();

        for (MapObject mot : mo) {
            MapProperties props = mot.getProperties();

            if (props.containsKey("gid")) {
                int gid = (Integer) props.get("gid");
                switch (gid) {
                    case 101:
                        if (PlayerData.posX == -1 && PlayerData.posY == -1) {
                            screen.manager.setPlayer(new Player((Float) props.get("x"), (Float) props.get("y")));
                            PlayerData.posX = (Float) props.get("x");
                            PlayerData.posY = (Float) props.get("y");
                        } else {
                            screen.manager.setPlayer(new Player(PlayerData.posX, PlayerData.posY));
                        }
                        break;
                    case 102:
                        String npcID = (String) props.get("id");
                        screen.manager.add(new NPC((Float) props.get("x"), (Float) props.get("y"), npcID));
                        break;
                    case 103:
                        /*String map = props.get("teleport_map", String.class);
                        int teleport_x = Integer.parseInt(props.get("teleport_x", String.class));
                        int teleport_y = Integer.parseInt(props.get("teleport_y", String.class));
                        screen.manager.add(new Teleporter(
                                props.get("x", Float.class),
                                props.get("y", Float.class),
                                map,
                                teleport_x,
                                teleport_y));*/

                        screen.manager.add(new ScriptTrigger(props.get("x", Float.class), props.get("y", Float.class), propertiesForTmx(props)));

                        break;
                    case 104:
                        screen.manager.add(new Rock((Float) props.get("x"), (Float) props.get("y")));
                        break;
                }
            }
        }
        OverworldScreen.camera.position.set((Integer) currentMap.getProperties().get("width")+OverworldScreen.camera.viewportWidth/10,(Integer) currentMap.getProperties().get("height")+OverworldScreen.camera.viewportHeight/5,0f);
        OverworldScreen.inputManager.refresh();
    }

    private HashMap<String, Object> propertiesForTmx(MapProperties properties) {
        Iterator<String> keys = properties.getKeys();
        Iterator<Object> values = properties.getValues();
        HashMap<String, Object> out = new HashMap<String, Object>();

        while (keys.hasNext()) {
            out.put(keys.next(), values.next());
        }

        return out;
    }

    public void render(OrthographicCamera camera) {
        tiledMapRenderer.render();
        tiledMapRenderer.setView(camera);

    }
}
