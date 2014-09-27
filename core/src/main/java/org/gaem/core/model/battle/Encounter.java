package org.gaem.core.model.battle;

import org.gaem.core.model.battle.skill.Skill;

/**
 * Created by Johan on 2014-09-27.
 */
public class Encounter {

    private int turn;
    private boolean turnFinished = false;
    private BattleProperties player, enemy;

    public Encounter(BattleProperties player, BattleProperties enemy) {
        this.player = player;
        this.enemy = enemy;
        this.turn = 0;
    }

    public void update(float delta) {

    }

    public BattleProperties getEnemy() {
        return enemy;
    }

    public int getEnemyId() {
        return turn == 1 ? 0 : 1;
    }

    public BattleProperties getPlayer() {
        return player;
    }

    public SkillResult useAttack(Skill skill, boolean enemy) {
        return skill.inflict(enemy ? this.enemy : player);
    }

    private void nextTurn() {
        turn = (turn == 1 ? 0 : 1);
    }


}
