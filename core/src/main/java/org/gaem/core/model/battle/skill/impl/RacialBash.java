package org.gaem.core.model.battle.skill.impl;

import org.gaem.core.model.battle.BattleProperties;
import org.gaem.core.model.battle.SkillResult;
import org.gaem.core.model.battle.skill.Skill;

/**
 * Created by Johan on 2014-09-27.
 */
public class RacialBash extends Skill {

    @Override
    public String getName() {
        return "Racial Bash";
    }

    @Override
    public int getCost() {
        return 4;
    }

    @Override
    public SkillResult inflict(BattleProperties target) {
        return new SkillResult("The contempt for humanity disgusts {target}", false);
    }
}
