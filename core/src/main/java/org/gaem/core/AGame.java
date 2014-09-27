package org.gaem.core;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import org.gaem.core.model.Player;
import org.gaem.core.util.YDownBitmapFontLoader;
import org.gaem.core.util.YDownTextureLoader;

import java.util.ArrayList;
import java.util.Map;

public class AGame implements ApplicationListener {
	float elapsed;
    public static AssetManager ASSETS;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Player player;

    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    ArrayList<TiledMapTileLayer.Cell> waterCellsInScene;

    Map<String,TiledMapTile> waterTiles;

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
        MapLayer mapLayer = tiledMap.getLayers().get("obte");
        MapObject mapObject = mapLayer.getObjects().get("WAT");
        while(!ASSETS.update());
        player = new Player((Float)mapObject.getProperties().get("x"),(Float)mapObject.getProperties().get("y"));
	}

    private void loadAssets() {
        ASSETS.setLoader(Texture.class, new YDownTextureLoader(new InternalFileHandleResolver()));
        ASSETS.setLoader(BitmapFont.class, new YDownBitmapFontLoader(new InternalFileHandleResolver()));
        ASSETS.load("sprites/player.png", Texture.class);
        ASSETS.load("sprites/libgdx-logo.png", Texture.class);
        ASSETS.load("fonts/SILKWONDER.fnt", BitmapFont.class);
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
            updateCamera();
            camera.update();

            tiledMapRenderer.setView(camera);
            tiledMapRenderer.render();

            batch.setProjectionMatrix(camera.combined);
            batch.enableBlending();
            batch.begin();
            batch.draw(player.sprite, player.realX, player.realY);
            batch.end();
        }

	}

    private void updateCamera() {
        camera.position.set(MathUtils.clamp(player.realX + 16, 100, 1000), MathUtils.clamp(player.realY + 16, 100, 1000), 0);
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
