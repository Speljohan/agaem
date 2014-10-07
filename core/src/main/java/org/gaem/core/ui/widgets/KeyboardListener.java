package org.gaem.core.ui.widgets;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by Johan on 2014-10-06.
 */
public class KeyboardListener extends InputListener {

    @Override
    public boolean keyDown(InputEvent event, int keyCode) {
        System.out.println("TYPED");

        return true;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        System.out.println("CLICKED");
        return true;
    }
}
