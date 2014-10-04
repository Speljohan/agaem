package org.gaem.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import org.gaem.core.AGame;
import org.gaem.core.engine.PlayerData;
import org.gaem.core.input.InputManager;
import org.gaem.core.model.battle.Encounter;
import org.gaem.core.model.overworld.EntityManager;
import org.gaem.core.model.overworld.MapManager;
import org.gaem.core.model.overworld.time.TimeManager;
import org.gaem.core.ui.DialogueManager;
import org.gaem.core.ui.inventory.ActiveItemWindow;
import org.gaem.core.ui.inventory.InventoryWindow;
import org.gaem.core.ui.menu.OverworldMenuWindow;

/**
 * Created by Johan on 2014-09-27.
 */
public class OverworldScreen implements Screen {

    public static DialogueManager DIALOGUEMANAGER;
    public static MapManager mapManager;
    public static EntityManager manager;
    public static InputManager inputManager;
    public static TimeManager timeManager;
    public static InventoryWindow inventoryWindow;
    public static OverworldMenuWindow overworldMenuWindow;
    private ActiveItemWindow activeItemWindow;
    static boolean stopAnim = true;
    private static Encounter encounter;
    public static OrthographicCamera camera;
    private AGame game;

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

            mapManager.render(camera);

            game.batch.setProjectionMatrix(camera.combined);
            game.batch.enableBlending();
            game.batch.begin();
            manager.render(delta, game.batch);
            game.batch.end();

            DIALOGUEMANAGER.render(delta);
            inventoryWindow.render(delta);
            activeItemWindow.render(delta);
            overworldMenuWindow.render(delta);
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
        if(!(mapManager.width < camera.viewportWidth || mapManager.height < camera.viewportHeight)){
            camera.position.set(MathUtils.clamp(manager.getPlayer().realX + 16, 0 + camera.viewportWidth / 4, mapManager.width - camera.viewportWidth / 4), MathUtils.clamp(manager.getPlayer().realY + 16, 0 + camera.viewportHeight / 4, mapManager.height - camera.viewportHeight / 4), 0);
        }
        else{
           // camera.position.set(manager.getPlayer().realX + 16, manager.getPlayer().realY + 16, 0);
        }

        camera.update();
    }

    private void doUpdate(float delta) {
        manager.update(delta);
        inputManager.update(delta);
        updateCamera();
        timeManager.update(delta);
        DIALOGUEMANAGER.update(delta);
        inventoryWindow.update(delta);
        activeItemWindow.update(delta);
        overworldMenuWindow.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        inputManager = new InputManager(null, null);
        manager = new EntityManager();
        mapManager = new MapManager(this);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 0.5f;
        mapManager.loadMap("testmap");

        timeManager = new TimeManager();

        DIALOGUEMANAGER = new DialogueManager(camera);

        inventoryWindow = new InventoryWindow(camera);

        activeItemWindow = new ActiveItemWindow(camera);

        overworldMenuWindow = new OverworldMenuWindow(camera);


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
