package org.gaem.core.model.battle;

import org.gaem.core.model.battle.skill.Skill;

/**
 * Created by Johan on 2014-09-27.
 */
public class SkillResult {

    public Skill skill;
    public String log;
    public boolean win;

    public SkillResult(Skill skill, String log, boolean win) {
        this.skill = skill;
        this.log = log;
        this.win = win;
    }


}
