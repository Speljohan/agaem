package org.gaem.core.model.battle.ai;

import org.gaem.core.model.battle.BattleProperties;
import org.gaem.core.model.battle.Encounter;

/**
 * Created by Johan on 2014-09-28.
 */
public abstract class AI {

    public Encounter encounter;
    public BattleProperties properties;
    public BattleProperties enemyProperties;

    public AI() {
    }

    public void setProperties(BattleProperties properties) {
        this.properties = properties;
    }

    public void setEnemyProperties(BattleProperties enemy) {
        this.enemyProperties = enemy;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    public abstract void executeTurn();

}
