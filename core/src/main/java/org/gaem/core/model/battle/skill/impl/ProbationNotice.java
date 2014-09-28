package org.gaem.core.model.battle.skill.impl;

import org.gaem.core.model.battle.BattleProperties;
import org.gaem.core.model.battle.SkillResult;
import org.gaem.core.model.battle.skill.Skill;

/**
 * Created by Johan on 2014-09-27.
 */
public class ProbationNotice extends Skill {

    @Override
    public String getName() {
        return "Probation Notice";
    }

    @Override
    public int getCost() {
        return 5;
    }

    public String getDescription() {
        return "{source} reads a law paper";
    }

    @Override
    public SkillResult inflict(BattleProperties target) {
        target.currentMP -= 5;
        return new SkillResult(this, "Dismayed by the probation, {target} spends a day protesting (wait one turn)", false);
    }
}
