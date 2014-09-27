package org.gaem.core.model.battle;

import java.util.ArrayList;

/**
 * Created by Johan on 2014-09-27.
 */
public class BattleProperties {

    public int currentHP, maxHP;
    public int currentMP, maxMP;
    public ArrayList<Skill> skills;
    public ArrayList<Item> items;

    public BattleProperties(int currentHP, int maxHP, int currentMP, int maxMP, ArrayList<Skill> skills, ArrayList<Item> items) {
        this.currentHP = currentHP;
        this.maxHP = maxHP;
        this.currentMP = currentMP;
        this.maxMP = maxMP;
        this.skills = skills;
        this.items = items;
    }

}
