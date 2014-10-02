package org.gaem.core.ui.inventory;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.gaem.core.AGame;
import org.gaem.core.screen.OverworldScreen;
import org.gaem.core.ui.UIWindow;
import org.gaem.core.ui.inventory.items.Item;

/**
 * Created by Joar on 2014-10-02.
 */
public class ActiveItemWindow extends UIWindow {

    private Sprite background;

    public ActiveItemWindow(OrthographicCamera camera) {
        super(camera);
        background = new Sprite(AGame.ASSETS.get("sprites/chatbox.png", Texture.class));
    }

    @Override
    public void doRender(float delta) {
        drawTexture(background.getTexture(), 5, 5, 16, 16);

        Item tempItem = OverworldScreen.manager.getPlayer().activeItem;

        if(tempItem != null){
            tempItem.render(5,5,origin,batch);
        }
    }

    @Override
    public void doUpdate(float delta) {

    }
}
