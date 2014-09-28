package org.gaem.core.model.overworld.triggerables;

import org.gaem.core.model.overworld.Player;
import org.gaem.core.model.overworld.Triggerable;
import org.gaem.core.screen.OverworldScreen;

/**
 * Created by Johan on 2014-09-28.
 */
public class Teleporter extends Triggerable {

    private String targetMap;
    private int targetX, targetY;

    public Teleporter(float x, float y, String targetMap, int targetX, int targetY) {
        super(x, y);
        this.targetMap = targetMap;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    @Override
    public void trigger(Player player) {
        OverworldScreen.mapManager.loadMap(targetMap);
    }
}
