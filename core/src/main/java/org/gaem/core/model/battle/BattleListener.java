package org.gaem.core.model.battle;

import org.gaem.core.model.battle.skill.Skill;

/**
 * Created by Johan on 2014-09-27.
 */
public interface BattleListener {

    void attackStarted(Skill skill);

    void attackFinished(SkillResult result);

    void playerWin();

    void playerLose();
}
