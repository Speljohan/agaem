package org.gaem.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.JsonValue;
import org.gaem.core.screen.OverworldScreen;
import org.gaem.core.util.JsonLoader;

public class AGame extends Game {
    public static AssetManager ASSETS;
    public SpriteBatch batch;

    @Override
    public void create() {

        ASSETS = new AssetManager();
        loadAssets();
        batch = new SpriteBatch();
        this.setScreen(new OverworldScreen(this));
    }

    private void loadAssets() {
        ASSETS.setLoader(JsonValue.class, new JsonLoader(new InternalFileHandleResolver()));
        ASSETS.load("sprites/player_new.png", Texture.class);
        ASSETS.load("fonts/SILKWONDER.fnt", BitmapFont.class);
        ASSETS.load("sprites/chatbox.png", Texture.class);
        ASSETS.load("sprites/pointer.png", Texture.class);
        ASSETS.load("data/dialogue.json", JsonValue.class);
        ASSETS.load("sprites/cursor.png", Texture.class);

        while (!ASSETS.update()) ;
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {
        super.render();

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}