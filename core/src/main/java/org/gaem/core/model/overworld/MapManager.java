package org.gaem.core.model.overworld;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import org.gaem.core.engine.PlayerData;
import org.gaem.core.model.overworld.interactables.Rock;
import org.gaem.core.model.overworld.triggerables.Teleporter;
import org.gaem.core.screen.OverworldScreen;

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

    public void loadNewMap(String mapName) {
        screen.manager.mapUpdate(mapName);
    }

    public void loadMap(String mapName) {
        currentMap = new TmxMapLoader().load("map/" + mapName + ".tmx");
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
                        String map = props.get("teleport_map", String.class);
                        int teleport_x = Integer.parseInt(props.get("teleport_x", String.class));
                        int teleport_y = Integer.parseInt(props.get("teleport_y", String.class));
                        screen.manager.add(new Teleporter(
                                props.get("x", Float.class),
                                props.get("y", Float.class),
                                map,
                                teleport_x,
                                teleport_y));
                        break;
                    case 104:
                        screen.manager.add(new Rock((Float) props.get("x"), (Float) props.get("y")));
                        break;
                }
            }
        }
        OverworldScreen.inputManager.refresh();
    }

    public void render(OrthographicCamera camera) {
        tiledMapRenderer.render();
        tiledMapRenderer.setView(camera);

    }
}
