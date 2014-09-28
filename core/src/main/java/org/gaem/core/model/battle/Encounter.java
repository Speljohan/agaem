package org.gaem.core.model.battle;

import com.badlogic.gdx.math.MathUtils;
import org.gaem.core.model.battle.skill.Skill;

/**
 * Created by Johan on 2014-09-27.
 */
public class Encounter {

    private boolean enemyTurn;
    private boolean turnFinished = false;
    private BattleProperties player, enemy;
    private float pause, currentTime;

    private BattleListener listener;

    private Skill currentSkill;

    public Encounter(BattleProperties player, BattleProperties enemy, BattleListener listener) {
        this.player = player;
        this.enemy = enemy;
        this.enemyTurn = false;
        this.pause = 1f;
        this.listener = listener;
    }

    public void update(float delta) {
        currentTime += delta;
        if (currentTime >= pause) {
            currentTime = 0;
            if (enemyTurn && currentSkill == null) {
                useAttack(enemy.skills.get(MathUtils.random(0, 3)));
                return;
            }

            if (turnFinished) {
                if (currentSkill != null) {
                    if (isEnemyTurn()) {
                        listener.attackFinished(currentSkill.inflict(player));
                    } else {
                        listener.attackFinished(currentSkill.inflict(enemy));
                    }
                    currentSkill = null;
                    nextTurn();
                }
            }
        }

    }

    public BattleProperties getCurrentTarget() {
        return (enemyTurn ? player : enemy);
    }

    public BattleProperties getCurrentTurn() {
        return (!enemyTurn ? player : enemy);
    }

    public boolean isEnemyTurn() {
        return enemyTurn;
    }

    public BattleProperties getEnemy() {
        return enemy;
    }

    public BattleProperties getPlayer() {
        return player;
    }

    public void useAttack(Skill skill) {
        listener.attackStarted(skill.getDescription());
        currentSkill = skill;
        turnFinished = true;
        currentTime = 0;
    }

    public void nextTurn() {
        enemyTurn = !enemyTurn;
        turnFinished = false;
    }


}
