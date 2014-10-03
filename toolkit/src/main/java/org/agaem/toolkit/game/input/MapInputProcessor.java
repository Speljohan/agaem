package org.agaem.toolkit.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.MathUtils;
import org.agaem.toolkit.game.MapEditor;

/**
 * Created by Johan on 2014-10-03.
 */
public class MapInputProcessor implements InputProcessor {

    private MapEditor editor;
    private boolean middleDown;

    private int lastX, lastY;

    public MapInputProcessor(MapEditor editor) {
        this.editor = editor;
        this.middleDown = false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        middleDown = button == Input.Buttons.MIDDLE;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        lastX = 0;
        lastY = 0;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (middleDown) {
            if (lastX != 0 && lastY != 0) {
                editor.camera.position.x -= screenX - lastX;
                editor.camera.position.y += screenY - lastY;
            }
            lastX = screenX;
            lastY = screenY;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        editor.camera.zoom += (amount * 0.1);
        editor.camera.zoom = MathUtils.clamp(editor.camera.zoom, 0.1f, 1f);
        return false;
    }

    public void update(float delta) {

    }
}
