package org.gaem.core.model.battle.skill;

import org.gaem.core.model.battle.BattleProperties;
import org.gaem.core.model.battle.SkillResult;
import org.gaem.core.model.battle.skill.impl.BongStrike;
import org.gaem.core.model.battle.skill.impl.Kumbaja;
import org.gaem.core.model.battle.skill.impl.ProbationNotice;
import org.gaem.core.model.battle.skill.impl.RacialBash;

import java.util.ArrayList;

/**
 * Created by Johan on 2014-09-27.
 */
public abstract class Skill {


    public static ArrayList<Skill> ALL;

    static {
        ALL = new ArrayList<Skill>();
        ALL.add(new BongStrike());
        ALL.add(new Kumbaja());
        ALL.add(new ProbationNotice());
        ALL.add(new RacialBash());
    }

    public abstract String getName();

    public abstract int getCost();

    public abstract String getDescription();

    public abstract SkillResult inflict(BattleProperties target);

}
