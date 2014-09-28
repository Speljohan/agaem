package org.gaem.core.model.battle;

import com.badlogic.gdx.math.MathUtils;
import org.gaem.core.model.battle.skill.Skill;
import org.gaem.core.model.battle.skill.impl.BongStrike;
import org.gaem.core.model.battle.skill.impl.Kumbaja;
import org.gaem.core.model.battle.skill.impl.ProbationNotice;
import org.gaem.core.model.battle.skill.impl.RacialBash;

import java.util.ArrayList;

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

    public static Encounter generateRandomEncounter() {
        ArrayList<Skill> skills = new ArrayList<Skill>();
        skills.add(new BongStrike());
        skills.add(new Kumbaja());
        skills.add(new ProbationNotice());
        skills.add(new RacialBash());
        return new Encounter(new BattleProperties("you", 10, 10, 10, 10, skills, null), new BattleProperties("GenericFiend", 10, 10, 10, 10, skills, null), null);
    }

    public void setListener(BattleListener listener) {
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
