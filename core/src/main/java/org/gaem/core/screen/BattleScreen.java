package org.gaem.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import org.gaem.core.AGame;
import org.gaem.core.engine.PlayerData;
import org.gaem.core.input.BattleInputManager;
import org.gaem.core.model.battle.Encounter;
import org.gaem.core.ui.impl.BattleWindow;

/**
 * Created by Johan on 2014-09-27.
 */
public class BattleScreen implements Screen {

    private static boolean stopAnim = true;
    private AGame game;
    private OrthographicCamera camera;
    private BattleWindow battleWindow;
    private BattleInputManager inputManager;
    private Encounter encounter;

    public BattleScreen(AGame game, Encounter encounter) {
        this.game = game;
        this.encounter = encounter;
    }

    public static void startOverworldTransition() {
        stopAnim = false;
        PlayerData.PLAYER.reset();
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        battleWindow.render(delta);
    }

    public void update(float delta) {
        camera.update();
        inputManager.update(delta);
        battleWindow.update(delta);

        if (!stopAnim) {
            stopAnim = true;
            game.setScreen(new OverworldScreen(game));
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //camera.zoom = 0.5f;
        battleWindow = new BattleWindow(camera, encounter);
        inputManager = new BattleInputManager(battleWindow);

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
