package org.gaem.core.model.battle;

/**
 * Created by Johan on 2014-09-27.
 */
public interface BattleListener {

    void attackStarted(String description);

    void attackFinished(SkillResult result);

    void playerWin();

    void playerLose();
}
