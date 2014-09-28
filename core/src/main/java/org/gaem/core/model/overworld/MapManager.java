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

    public void loadMap(String mapName) {
        screen.manager.clear();
        currentMap = new TmxMapLoader().load("map/" + mapName + ".tmx");
        width = (Integer) currentMap.getProperties().get("width") * 16;
        height = (Integer) currentMap.getProperties().get("height") * 16;
        tiledMapRenderer = new OrthogonalTiledMapRenderer(currentMap);

        MapLayer mapLayer = currentMap.getLayers().get("obte");

        MapObjects mo = mapLayer.getObjects();

        for (MapObject mot : mo) {
            MapProperties props = mot.getProperties();
            if (props.containsKey("gid") && (Integer) props.get("gid") == 101) {
                if (PlayerData.posX == -1 && PlayerData.posY == -1) {
                    screen.manager.setPlayer(new Player((Float) props.get("x"), (Float) props.get("y")));
                    PlayerData.posX = (Float) props.get("x");
                    PlayerData.posY = (Float) props.get("y");
                } else {
                    screen.manager.setPlayer(new Player(PlayerData.posX, PlayerData.posY));
                }
            }
            if (props.containsKey("gid") && (Integer) props.get("gid") == 102) {
                String npcID = (String) props.get("id");
                screen.manager.add(new NPC((Float) props.get("x"), (Float) props.get("y"), npcID));
            }
            if (props.containsKey("gid") && (Integer) props.get("gid") == 103) {
                screen.manager.add(new Rock((Float) props.get("x"), (Float) props.get("y")));
            }
        }
        screen.manager.add(new Rock(screen.manager.getPlayer().realX + 64, screen.manager.getPlayer().realY));
        screen.manager.add(new Rock(screen.manager.getPlayer().realX + 128, screen.manager.getPlayer().realY));
    }

    public void render(OrthographicCamera camera) {
        tiledMapRenderer.render();
        tiledMapRenderer.setView(camera);

    }
}