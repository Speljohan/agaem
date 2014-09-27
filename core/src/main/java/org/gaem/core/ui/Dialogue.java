package org.gaem.core.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.AGame;
import org.gaem.core.util.CameraUtils;
import org.gaem.core.util.TextScroller;


/**
 * Created by Johan on 2014-09-27.
 */
public class Dialogue {

    private TextScroller textScroller;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Sprite background;
    private BitmapFont font;

    public Dialogue(OrthographicCamera camera) {
        this.textScroller = new TextScroller("Hai, World, this message is brought to you by NAZIS!!! OMG", 5);
        this.batch = new SpriteBatch();
        this.camera = camera;
        background = new Sprite(AGame.ASSETS.get("sprites/chatbox.png", Texture.class));
        font = AGame.ASSETS.get("fonts/SILKWONDER.fnt", BitmapFont.class);
        font.setScale(0.3f);
        textScroller.start();
    }

    public void update(float delta) {
        textScroller.update(delta);
    }

    public void render(float delta) {
        Vector2 origin = CameraUtils.getBottomLeftCorner(camera);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, origin.x, origin.y, 480, 100);
        font.draw(batch, textScroller.getTextChunk(), origin.x + 10, origin.y + 90);
        batch.end();
    }

}
