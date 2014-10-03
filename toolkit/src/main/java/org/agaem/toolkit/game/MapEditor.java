package org.agaem.toolkit.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import org.agaem.toolkit.Toolkit;
import org.agaem.toolkit.game.input.MapInputProcessor;
import org.agaem.toolkit.model.Grid;

/**
 * Created by Johan on 2014-10-03.
 */
public class MapEditor extends Game {

    private Toolkit toolkit;
    public OrthographicCamera camera;

    private Grid grid;
    private MapInputProcessor input;

    public MapEditor(Toolkit toolkit) {
        this.toolkit = toolkit;
    }

    @Override
    public void create() {
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.grid = new Grid(10, 10, 16, 16);
        camera.zoom = 0.5f;
        input = new MapInputProcessor(this);
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        camera.viewportWidth = Gdx.graphics.getWidth();
        camera.viewportHeight = Gdx.graphics.getHeight();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        input.update(0);
        camera.update();
        grid.render(0, camera);
    }
}
