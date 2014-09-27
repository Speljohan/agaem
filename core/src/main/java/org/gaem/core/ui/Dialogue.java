package org.gaem.core.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.gaem.core.AGame;
import org.gaem.core.util.TextScroller;


/**
 * Created by Johan on 2014-09-27.
 */
public class Dialogue extends UIWindow {

    private TextScroller textScroller;
    private Sprite background;

    public Dialogue(OrthographicCamera camera, String text) {
        super(camera);
        this.textScroller = new TextScroller(text, 5);
        background = new Sprite(AGame.ASSETS.get("sprites/chatbox.png", Texture.class));
        textScroller.start();
    }

    public void doRender(float delta) {
        drawTexture(background.getTexture(), 0, 0, 480, 100);
        drawText(textScroller.getTextChunk(), 10, 90);
    }

    public void doUpdate(float delta) {
        textScroller.update(delta);
    }

}
