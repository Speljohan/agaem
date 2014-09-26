package org.gaem.core.util;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Johan on 2014-09-27.
 */
public class TileUtils {

    public static Vector2 tileForPos(float x, float y) {
        return new Vector2(MathUtils.floor(x) / 16, MathUtils.floor(y) / 16);
    }

}
