package org.gaem.core.model.overworld.interactables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.gaem.core.AGame;
import org.gaem.core.engine.SpriteSheet;
import org.gaem.core.model.overworld.Interactable;
import org.gaem.core.model.overworld.Player;
import org.gaem.core.screen.OverworldScreen;

/**
 * Created by Johan on 2014-10-05.
 */
public class Crop extends Interactable {

    private int spriteIdx, stage;
    private SpriteSheet sheet;

    public Crop(float x, float y, int spriteIdx) {
        super(x, y);
        this.spriteIdx = spriteIdx;
        this.stage = 0;
        this.sheet = new SpriteSheet(AGame.ASSETS.get("sprites/crops.png", Texture.class), 16, 16);
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    @Override
    public void render(float delta, SpriteBatch batch) {
        batch.draw(sheet.get(spriteIdx, stage), realX, realY);
    }

    @Override
    public void interact(Player player) {
        if (stage == 3) {
            OverworldScreen.manager.remove(this);
        }
    }
}
