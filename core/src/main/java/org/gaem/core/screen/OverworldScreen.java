package org.gaem.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import org.gaem.core.AGame;
import org.gaem.core.input.InputManager;
import org.gaem.core.model.overworld.NPC;
import org.gaem.core.model.overworld.Player;
import org.gaem.core.ui.DialogueManager;

import java.util.ArrayList;

/**
 * Created by Johan on 2014-09-27.
 */
public class OverworldScreen implements Screen {

    public static DialogueManager DIALOGUEMANAGER;
    public OrthographicCamera camera;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    private AGame game;
    private ArrayList<NPC> npcList;
    private Player player;
    private InputManager inputManager;


    public OverworldScreen(AGame game) {
        this.game = game;

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);


        if (game.ASSETS.update()) {

            doUpdate(delta);

            tiledMapRenderer.render();
            tiledMapRenderer.setView(camera);

            game.batch.setProjectionMatrix(camera.combined);
            game.batch.enableBlending();
            game.batch.begin();
            for (NPC npc : npcList) {
                npc.render(delta, game.batch);
            }
            player.render(delta, game.batch);
            game.batch.end();

            DIALOGUEMANAGER.render(delta);
        }

    }

    private void updateCamera() {
      //  camera.position.set(MathUtils.clamp(player.realX + 16, 100, 1000), MathUtils.clamp(player.realY + 16, 100, 1000), 0);
        camera.position.set(player.realX + 16,player.realY + 16, 0);
        camera.update();
    }

    private void doUpdate(float delta) {
        for (NPC npc : npcList) {
            npc.update(delta);
        }
        inputManager.update(delta);
        player.update(delta);
        updateCamera();
        DIALOGUEMANAGER.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        npcList = new ArrayList<NPC>();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 0.5f;

        tiledMap = new TmxMapLoader().load("map/testmap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        MapLayer mapLayer = tiledMap.getLayers().get("obte");

        MapObject mapObject = mapLayer.getObjects().get("WAT");
        System.out.println(mapLayer.getObjects().getCount());
        MapObjects mo = mapLayer.getObjects();

        for (MapObject mot : mo) {
            if (mot.getProperties().containsKey("gid") && (Integer) mot.getProperties().get("gid") == 101) {
                player = new Player((Float) mot.getProperties().get("x"), (Float) mot.getProperties().get("y"), npcList);
                System.out.println("Added player! " + mot.getProperties().get("x") + " " + mot.getProperties().get("y"));
            }
            if (mot.getProperties().containsKey("gid") && (Integer) mot.getProperties().get("gid") == 102) {
                String npcID = (String)mot.getProperties().get("id");
                System.out.println("NPC ID " + npcID);
                npcList.add(new NPC((Float) mot.getProperties().get("x"), (Float) mot.getProperties().get("y"), npcID));
                System.out.println("Added NPC! " + mot.getProperties().get("x") + " " + mot.getProperties().get("y"));
            }
        }
        // player = new Player((Float) mapObject.getProperties().get("x"), (Float) mapObject.getProperties().get("y"), npcList);
        inputManager = new InputManager(player, tiledMap);
        DIALOGUEMANAGER = new DialogueManager(camera);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
