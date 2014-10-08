package org.gaem.core.model.overworld.triggerables;

import org.gaem.core.engine.PlayerData;
import org.gaem.core.model.overworld.Player;
import org.gaem.core.model.overworld.Triggerable;

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
        PlayerData.posX = -1;
        PlayerData.posY = -1;
        //OverworldScreen.mapManager.loadMap(targetMap);
        //manager.getPlayer().setLocation(targetX * 16, (OverworldScreen.mapManager.height - (targetY * 16)));
        //AGame.SCRIPTENGINE.executeScript("teleport", 5, 5);
    }
}
