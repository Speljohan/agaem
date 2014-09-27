package org.gaem.core.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.gaem.core.ui.impl.BattleWindow;

/**
 * Created by Johan on 2014-09-27.
 */
public class BattleInputManager {

    public BattleWindow battleWindow;

    public BattleInputManager(BattleWindow window) {
        this.battleWindow = window;
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            battleWindow.changeSelection(2);
        }
    }
}
