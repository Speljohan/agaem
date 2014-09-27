package org.gaem.core.util;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Johan on 2014-09-27.
 */
public class CameraUtils {

    public static Vector2 getBottomLeftCorner(Camera camera) {
        return new Vector2(camera.position.x - camera.viewportWidth / 4, camera.position.y - camera.viewportHeight / 4);
    }
}
