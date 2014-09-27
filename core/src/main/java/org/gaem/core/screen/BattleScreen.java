package org.gaem.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import org.gaem.core.AGame;
import org.gaem.core.input.BattleInputManager;
import org.gaem.core.ui.impl.BattleWindow;

/**
 * Created by Johan on 2014-09-27.
 */
public class BattleScreen implements Screen {

    private AGame game;
    private OrthographicCamera camera;
    private BattleWindow battleWindow;
    private BattleInputManager inputManager;

    public BattleScreen(AGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        inputManager.update(delta);
        battleWindow.update(delta);
        battleWindow.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 0.5f;
        battleWindow = new BattleWindow(camera);
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
