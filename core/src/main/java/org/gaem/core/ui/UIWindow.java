package org.gaem.core.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.AGame;
import org.gaem.core.util.CameraUtils;

/**
 * Created by Johan on 2014-09-27.
 */
public abstract class UIWindow {

    protected BitmapFont font;
    protected SpriteBatch batch;
    protected OrthographicCamera camera;
    protected Vector2 origin;

    public UIWindow(OrthographicCamera camera) {
        this.camera = camera;
        origin = CameraUtils.getBottomLeftCorner(camera);
        this.batch = new SpriteBatch();
        this.font = AGame.ASSETS.get("fonts/SILKWONDER.fnt", BitmapFont.class);
        font.setScale(0.3f);
    }

    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        doRender(delta);
        batch.end();
    }

    public abstract void doRender(float delta);

    public void update(float delta) {
        origin = CameraUtils.getBottomLeftCorner(camera);
        doUpdate(delta);
    }

    public abstract void doUpdate(float delta);

    public void drawText(String s, int x, int y) {
        font.draw(batch, s, origin.x + x, origin.y + y);
    }

    public void drawTextWrapped(String s, int x, int y, int wrap) {
        font.drawWrapped(batch, s, x, y, wrap, BitmapFont.HAlignment.LEFT);
    }

    public void setFontColor(Color color) {
        font.setColor(color);
    }

    public void resetFontColor() {
        font.setColor(Color.BLACK);
    }

    public void drawTexture(Texture t, int x, int y) {
        batch.draw(t, origin.x + x, origin.y + y);
    }

    public void drawTexture(Texture t, int x, int y, int scaleX, int scaleY) {
        batch.draw(t, origin.x + x, origin.y + y, scaleX, scaleY);
    }

}
