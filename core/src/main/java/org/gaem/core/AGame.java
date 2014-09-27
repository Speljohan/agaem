package org.gaem.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import org.gaem.core.input.InputManager;
import org.gaem.core.model.NPC;
import org.gaem.core.model.Player;
import org.gaem.core.ui.DialogueManager;

import java.util.ArrayList;

public class AGame implements ApplicationListener {
    public static AssetManager ASSETS;
    public static DialogueManager DIALOGUEMANAGER;
    float elapsed;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Player player;
    private InputManager inputManager;
    private int mapWidth;
    private int mapHeigth;
    private ArrayList<NPC> npcList;

    @Override
    public void create () {
        npcList = new ArrayList<NPC>();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 0.5f;
        ASSETS = new AssetManager();
        loadAssets();
        batch = new SpriteBatch();

        tiledMap = new TmxMapLoader().load("map/testmap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        MapLayer mapLayer = tiledMap.getLayers().get("obte");

        MapObject mapObject = mapLayer.getObjects().get("WAT");
        System.out.println( mapLayer.getObjects().getCount());
        MapObjects mo = mapLayer.getObjects();

        while(!ASSETS.update());
        for(MapObject mot:mo)
        {
            if(mot.getProperties().containsKey("gid") && (Integer)mot.getProperties().get("gid") == 101)
            {
                player = new Player((Float) mot.getProperties().get("x"), (Float) mot.getProperties().get("y"), npcList);
            }
            if(mot.getProperties().containsKey("gid") && (Integer)mot.getProperties().get("gid") == 102){
                npcList.add(new NPC((Float) mot.getProperties().get("x"), (Float) mot.getProperties().get("y")));
            }
        }
       // player = new Player((Float) mapObject.getProperties().get("x"), (Float) mapObject.getProperties().get("y"), npcList);
        inputManager = new InputManager(player, tiledMap);
        DIALOGUEMANAGER = new DialogueManager(camera);




    }

    private void loadAssets() {
        ASSETS.load("sprites/player.pack", TextureAtlas.class);
        ASSETS.load("fonts/SILKWONDER.fnt", BitmapFont.class);
        ASSETS.load("sprites/chatbox.png", Texture.class);
    }

    @Override
    public void resize (int width, int height) {
    }

    @Override
    public void render () {
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);



        if (ASSETS.update()) {

            doUpdate();

            tiledMapRenderer.render();
            tiledMapRenderer.setView(camera);

            batch.setProjectionMatrix(camera.combined);
            batch.enableBlending();
            batch.begin();
            for (NPC npc : npcList) {
                batch.draw(npc.sprite, npc.realX, npc.realY - 48);
            }
            batch.draw(player.sprite, player.realX, player.realY - 48);
            batch.end();

            DIALOGUEMANAGER.render(elapsed);
        }

    }

    private void updateCamera() {
        camera.position.set(MathUtils.clamp(player.realX + 16, 100, 1000), MathUtils.clamp(player.realY + 16, 100, 1000), 0);
        camera.update();
    }

    private void doUpdate() {
        for (NPC npc : npcList) {
            npc.update(elapsed);
        }
        inputManager.update(elapsed);
        player.update(elapsed);
        updateCamera();
        DIALOGUEMANAGER.update(elapsed);
    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }

    @Override
    public void dispose () {
    }
}