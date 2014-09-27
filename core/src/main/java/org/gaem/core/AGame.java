package org.gaem.core;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import org.gaem.core.model.Player;

public class AGame implements ApplicationListener {
	float elapsed;
    public static AssetManager ASSETS;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Player player;

    Texture img;
    TiledMap tiledMap;
   // OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;

	@Override
	public void create () {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 0.5f;
        camera.setToOrtho(true);
        ASSETS = new AssetManager();
        loadAssets();
        batch = new SpriteBatch();

        tiledMap = new TmxMapLoader().load("map/testmap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        while(!ASSETS.update());
        player = new Player();
	}

    private void loadAssets() {
        ASSETS.load("sprites/player.png", Texture.class);
        ASSETS.load("sprites/libgdx-logo.png", Texture.class);
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

            player.update(elapsed);
            camera.position.set(player.realX + 16, player.realY + 16, 0);
            camera.update();

            tiledMapRenderer.setView(camera);
            tiledMapRenderer.render();

            batch.setProjectionMatrix(camera.combined);
            batch.enableBlending();
            batch.begin();
          //  batch.draw(ASSETS.get("sprites/libgdx-logo.png", Texture.class), 0, 0);
            batch.draw(player.sprite, player.realX, player.realY);
            batch.end();
        }

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
