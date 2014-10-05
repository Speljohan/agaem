package org.gaem.core.model.overworld.time;

import org.gaem.core.model.overworld.Entity;
import org.gaem.core.model.overworld.interactables.Crop;
import org.gaem.core.screen.OverworldScreen;

/**
 * Created by Johan on 2014-10-05.
 */
public class CropGrowthEvent extends TimedEvent {

    private int x, y, currentStage, spriteIdx;

    public CropGrowthEvent(float interval, int x, int y, int spriteIdx) {
        super(interval);
        this.spriteIdx = spriteIdx;
        this.currentStage = 0;
        this.x = x;
        this.y = y;
    }

    @Override
    public void init() {
        OverworldScreen.manager.add(new Crop(x * 16, y * 16, spriteIdx));
    }

    @Override
    public void onTrigger() {
        if (currentStage == 4) {
            owner.removeEvent(this);
            return;
        }

        Entity e = OverworldScreen.manager.getEntityAt(x, y);
        if (e instanceof Crop) {
            ((Crop) e).setStage(currentStage);
        }
        currentStage++;
    }
}
