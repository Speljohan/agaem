package org.gaem.core.ui.menu;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.gaem.core.AGame;
import org.gaem.core.ui.UIWindow;

/**
 * Created by Joar on 2014-10-02.
 */
public class OverworldMenuWindow extends UIWindow {


    //Borde flytta över mycket av koden till en gemensam subklass.

    private Sprite background;
    public boolean visible;

    public OverworldMenuWindow(OrthographicCamera camera) {
        super(camera);

        background = new Sprite(AGame.ASSETS.get("sprites/chatbox.png", Texture.class));
        visible = false;
    }

    @Override
    public void doRender(float delta) {
       // drawTexture(background.getTexture(), marginX, marginY, windowWidth, windowHeigth);
        if(visible) {
            drawTexture(background.getTexture(), 10, 10, (int) camera.viewportWidth / 2 - 20, (int) camera.viewportHeight / 2 - 20);
            drawText("Menu", 50, 200);
        }
    }

    @Override
    public void doUpdate(float delta) {

    }
}
