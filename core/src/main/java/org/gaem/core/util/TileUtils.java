package org.gaem.core.util;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.model.overworld.Mobile;

/**
 * Created by Johan on 2014-09-27.
 */
public class TileUtils {

    public static final int[] SOLID = {3, 5, 9, 12};


    public static boolean isBlocked(int id) {
        for (int i = 0; i < SOLID.length; i++) {
            if (id == SOLID[i]) return true;
        }
        return false;
    }

    public static Vector2 tileForPos(float x, float y) {
        return new Vector2(MathUtils.floor(x) / 16, MathUtils.floor(y) / 16);
    }

    public static double distanceTo(Mobile mobile, float targetX, float targetY) {
        return Math.sqrt((mobile.realX - targetX) * (mobile.realX - targetX) + (mobile.realY - targetY) * (mobile.realY - targetY));
    }

}
