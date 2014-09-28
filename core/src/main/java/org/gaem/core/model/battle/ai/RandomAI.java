package org.gaem.core.model.battle.ai;

import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Johan on 2014-09-28.
 */
public class RandomAI extends AI {

    public RandomAI() {
        super();
    }


    @Override
    public void executeTurn() {
        encounter.useAttack(properties.skills.get(MathUtils.random(0, 3)));

    }
}
