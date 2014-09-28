package org.gaem.core.model.battle;

import com.badlogic.gdx.math.MathUtils;
import org.gaem.core.engine.PlayerData;
import org.gaem.core.model.battle.ai.RandomAI;
import org.gaem.core.model.battle.skill.Skill;
import org.gaem.core.model.battle.skill.impl.BongStrike;
import org.gaem.core.model.battle.skill.impl.Kumbaja;
import org.gaem.core.model.battle.skill.impl.ProbationNotice;
import org.gaem.core.model.battle.skill.impl.RacialBash;
import org.gaem.core.screen.BattleScreen;

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

    private boolean won, lost;

    public Encounter(BattleProperties player, BattleProperties enemy, BattleListener listener) {
        this.player = player;
        this.enemy = enemy;
        this.enemyTurn = false;
        this.pause = 1f;
        this.listener = listener;
        this.won = false;
        this.lost = false;
    }

    public static Encounter generateRandomEncounter() {
        ArrayList<Skill> skills = new ArrayList<Skill>();
        skills.add(new BongStrike());
        skills.add(new Kumbaja());
        skills.add(new ProbationNotice());
        skills.add(new RacialBash());
        return new Encounter(PlayerData.PLAYER, new BattleProperties("GenericFiend", 10, 10, 10, 10, skills, null, new RandomAI()), null);
    }

    public void win() {
        this.won = true;
    }

    public void lose() {
        this.lost = true;
    }

    public void setListener(BattleListener listener) {
        this.listener = listener;
    }

    public void update(float delta) {
        clampVals(player);
        clampVals(enemy);
        currentTime += delta;
        if (currentTime >= pause) {
            currentTime = 0;
            if (won) {
                BattleScreen.startOverworldTransition();
                return;
            } else if (lost) {
                BattleScreen.startOverworldTransition();
                return;
            }
            if (enemy.currentHP <= 0) {
                listener.playerWin();
                return;
            } else if (player.currentHP <= 0) {
                listener.playerLose();
                return;
            }
            if (enemyTurn && currentSkill == null) {
                enemy.getAI(this).executeTurn();
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

    private void clampVals(BattleProperties target) {
        target.currentMP = MathUtils.clamp(target.currentMP, 0, 1000);
        target.currentHP = MathUtils.clamp(target.currentHP, 0, 1000);
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
