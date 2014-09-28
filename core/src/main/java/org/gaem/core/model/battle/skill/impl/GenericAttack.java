package org.gaem.core.model.battle.skill.impl;

import org.gaem.core.model.battle.BattleProperties;
import org.gaem.core.model.battle.SkillResult;
import org.gaem.core.model.battle.skill.Skill;

/**
 * Created by Johan on 2014-09-28.
 */
public class GenericAttack extends Skill {

    @Override
    public String getName() {
        return "Generic_Attack";
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "{source} launch at {target}";
    }

    @Override
    public SkillResult inflict(BattleProperties target) {
        target.currentHP -= 1;
        return new SkillResult("{source} strike {target} hard to the head.", false);
    }
}
