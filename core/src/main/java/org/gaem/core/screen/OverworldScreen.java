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
import org.gaem.core.engine.PlayerData;
import org.gaem.core.input.InputManager;
import org.gaem.core.model.battle.Encounter;
import org.gaem.core.model.overworld.EntityManager;
import org.gaem.core.model.overworld.NPC;
import org.gaem.core.model.overworld.Player;
import org.gaem.core.model.overworld.interactables.Rock;
import org.gaem.core.ui.DialogueManager;

/**
 * Created by Johan on 2014-09-27.
 */
public class OverworldScreen implements Screen {

    public static DialogueManager DIALOGUEMANAGER;
    public static int mapWidth;
    public static int mapHeigth;
    static boolean stopAnim = true;
    private static Encounter encounter;
    public OrthographicCamera camera;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    private AGame game;
    private InputManager inputManager;

    private EntityManager manager;
  //  private Interactable inter;

    public OverworldScreen(AGame game) {
        this.game = game;

    }

    public static void startBattleTransition(Encounter encounter) {
        stopAnim = false;
        OverworldScreen.encounter = encounter;
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
            manager.render(delta, game.batch);
            game.batch.end();

            DIALOGUEMANAGER.render(delta);
            battleAnimationLoop();
        }

    }

    private void battleAnimationLoop() {
        if (!stopAnim) {
            camera.rotate(3);
            camera.zoom -= 0.005f;
            if (camera.zoom <= 0.01) {
                stopAnim = true;
                PlayerData.posX = manager.getPlayer().realX;
                PlayerData.posY = manager.getPlayer().realY;
                PlayerData.facing = manager.getPlayer().facing;
                game.setScreen(new BattleScreen(game, encounter));
            }
        }
    }

    private void updateCamera() {
        //  System.out.println("Camera update: Width: " + mapWidth + " heigth: " + mapHeigth + " player pos: " + player.realX + " " + player.realY + " clamp " + MathUtils.clamp(player.realX + 16,0,mapWidth) + " " + MathUtils.clamp(player.realY + 16, 0, mapHeigth));
        camera.position.set(MathUtils.clamp(manager.getPlayer().realX + 16, 0 + camera.viewportWidth / 4, mapWidth - camera.viewportWidth / 4), MathUtils.clamp(manager.getPlayer().realY + 16, 0 + camera.viewportHeight / 4, mapHeigth - camera.viewportHeight / 4), 0);
        // camera.position.set(player.realX + 16,player.realY + 16, 0);

        camera.update();
    }

    private void doUpdate(float delta) {
        manager.update(delta);
        inputManager.update(delta);
        updateCamera();
        DIALOGUEMANAGER.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        manager = new EntityManager();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 0.5f;

        tiledMap = new TmxMapLoader().load("map/testmap.tmx");
        mapWidth = (Integer) tiledMap.getProperties().get("width") * 16;
        mapHeigth = (Integer) tiledMap.getProperties().get("height") * 16;
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        MapLayer mapLayer = tiledMap.getLayers().get("obte");

        MapObject mapObject = mapLayer.getObjects().get("WAT");
        System.out.println(mapLayer.getObjects().getCount());
        MapObjects mo = mapLayer.getObjects();

        for (MapObject mot : mo) {
            if (mot.getProperties().containsKey("gid") && (Integer) mot.getProperties().get("gid") == 101) {
                if (PlayerData.posX == -1 && PlayerData.posY == -1) {
                    manager.setPlayer(new Player((Float) mot.getProperties().get("x"), (Float) mot.getProperties().get("y")));
                    System.out.println("Added player! " + mot.getProperties().get("x") + " " + mot.getProperties().get("y"));
                    PlayerData.posX = (Float) mot.getProperties().get("x");
                    PlayerData.posY = (Float) mot.getProperties().get("y");
                } else {
                    manager.setPlayer(new Player(PlayerData.posX, PlayerData.posY));
                }
            }
            if (mot.getProperties().containsKey("gid") && (Integer) mot.getProperties().get("gid") == 102) {
                String npcID = (String) mot.getProperties().get("id");
                System.out.println("NPC ID " + npcID);
                manager.add(new NPC((Float) mot.getProperties().get("x"), (Float) mot.getProperties().get("y"), npcID));
                System.out.println("Added NPC! " + mot.getProperties().get("x") + " " + mot.getProperties().get("y"));
            }
        }
        // player = new Player((Float) mapObject.getProperties().get("x"), (Float) mapObject.getProperties().get("y"), npcList);
        inputManager = new InputManager(manager.getPlayer(), tiledMap);
        DIALOGUEMANAGER = new DialogueManager(camera);

        manager.add(new Rock(manager.getPlayer().realX + 64, manager.getPlayer().realY));
        manager.add(new Rock(manager.getPlayer().realX + 128, manager.getPlayer().realY));
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
