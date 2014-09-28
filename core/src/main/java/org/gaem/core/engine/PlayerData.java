package org.gaem.core.engine;

import org.gaem.core.model.battle.BattleProperties;
import org.gaem.core.model.battle.skill.Skill;

/**
 * Created by Johan on 2014-09-28.
 */
public class PlayerData {

    public static BattleProperties PLAYER;
    public static float posX = -1, posY = -1;
    public static int facing;

    static {
        PLAYER = new BattleProperties("you", 10, 10, 10, 10, Skill.ALL, null, null);
    }
}
