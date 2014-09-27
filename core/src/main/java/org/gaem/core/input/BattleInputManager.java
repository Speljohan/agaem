package org.gaem.core.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.gaem.core.ui.impl.BattleWindow;

/**
 * Created by Johan on 2014-09-27.
 */
public class BattleInputManager {

    public BattleWindow battleWindow;
    private float current, interval;
    public BattleInputManager(BattleWindow window) {
        this.battleWindow = window;
        this.current = 0;
        this.interval = 0.2f;
    }

    public void update(float delta) {
        current += delta;

        if (current <= interval) {
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            battleWindow.nextSelection();
            current = 0;
        }
    }
}
