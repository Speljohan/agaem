package org.gaem.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.gaem.core.model.battle.Encounter;
import org.gaem.core.screen.BattleScreen;

public class AGame extends Game {
    public static AssetManager ASSETS;
    public SpriteBatch batch;

    @Override
    public void create() {

        ASSETS = new AssetManager();
        loadAssets();
        batch = new SpriteBatch();

        this.setScreen(new BattleScreen(this, Encounter.generateRandomEncounter()));
        //this.setScreen(new OverworldScreen(this));

    }

    private void loadAssets() {
        ASSETS.load("sprites/player_new.png", Texture.class);
        ASSETS.load("fonts/SILKWONDER.fnt", BitmapFont.class);
        ASSETS.load("sprites/chatbox.png", Texture.class);
        ASSETS.load("sprites/pointer.png", Texture.class);
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