package org.gaem.core.model.battle.skill.impl;

import org.gaem.core.model.battle.BattleProperties;
import org.gaem.core.model.battle.SkillResult;
import org.gaem.core.model.battle.skill.Skill;

/**
 * Created by Johan on 2014-09-27.
 */
public class Kumbaja extends Skill {

    @Override
    public String getName() {
        return "Kumbaja";
    }

    @Override
    public int getCost() {
        return 5;
    }

    public String getDescription() {
        return "{source} sings a soothing song";
    }

    @Override
    public SkillResult inflict(BattleProperties target) {
        return new SkillResult(this, "The soothing song puts {target} to sleep", false);
    }
}
