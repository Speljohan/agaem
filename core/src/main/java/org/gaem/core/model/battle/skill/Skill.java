package org.gaem.core.model.battle.skill;

import org.gaem.core.model.battle.BattleProperties;
import org.gaem.core.model.battle.SkillResult;

/**
 * Created by Johan on 2014-09-27.
 */
public abstract class Skill {


    public abstract String getName();

    public abstract int getCost();

    public abstract SkillResult inflict(BattleProperties target);

}
