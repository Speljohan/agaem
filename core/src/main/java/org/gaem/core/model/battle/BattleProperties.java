package org.gaem.core.model.battle;

import org.gaem.core.model.battle.ai.AI;
import org.gaem.core.model.battle.skill.Skill;

import java.util.ArrayList;

/**
 * Created by Johan on 2014-09-27.
 */
public class BattleProperties {

    public int currentHP, maxHP;
    public int currentMP, maxMP;
    public ArrayList<Skill> skills;
    public ArrayList<Item> items;
    public String name;
    public AI ai;

    public BattleProperties(String name, int currentHP, int maxHP, int currentMP, int maxMP, ArrayList<Skill> skills, ArrayList<Item> items, AI ai) {
        this.name = name;
        this.currentHP = currentHP;
        this.maxHP = maxHP;
        this.currentMP = currentMP;
        this.maxMP = maxMP;
        this.skills = skills;
        this.items = items;
        this.ai = ai;
    }

    public AI getAI(Encounter encounter) {
        ai.setEncounter(encounter);
        ai.setProperties(this);
        ai.setEnemyProperties(encounter.getPlayer());
        return ai;
    }

    public void reset() {
        currentHP = maxHP;
        currentMP = maxMP;
    }

}
