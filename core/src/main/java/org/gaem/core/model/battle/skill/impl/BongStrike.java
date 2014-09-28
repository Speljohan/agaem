package org.gaem.core.model.battle.skill.impl;

import com.badlogic.gdx.math.MathUtils;
import org.gaem.core.model.battle.BattleProperties;
import org.gaem.core.model.battle.SkillResult;
import org.gaem.core.model.battle.skill.Skill;

/**
 * Created by Johan on 2014-09-27.
 */
public class BongStrike extends Skill {

    @Override
    public String getName() {
        return "Bong Strike";
    }

    @Override
    public int getCost() {
        return 10;
    }

    public String getDescription() {
        return "{source} makes an assault with a bong";
    }

    @Override
    public SkillResult inflict(BattleProperties target) {
        target.currentHP -= MathUtils.random(5, 15);
        return new SkillResult("{source} strike {target} to the head", false);
    }
}
