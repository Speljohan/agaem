package org.gaem.core.model.overworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import org.gaem.core.AGame;
import org.gaem.core.engine.NPCJsonData;
import org.gaem.core.engine.NPCList;
import org.gaem.core.engine.SpriteSheet;
import org.gaem.core.model.battle.Encounter;
import org.gaem.core.screen.OverworldScreen;
import org.gaem.core.util.JsonUtil;

/**
 * Created by Joar on 2014-09-27.
 */
public class NPC extends Mobile {

    public String id;
    public String name;
    public String text;

    public NPC(float x, float y, String id) {
        super(new SpriteSheet(AGame.ASSETS.get("sprites/player_new.png", Texture.class), 16, 16));
        tileX = MathUtils.floor(x / 16);
        tileY = MathUtils.floor(y / 16);

        this.id = id;

        realX = x;
        realY = y;


        NPCList npcList = JsonUtil.readJson();
        System.out.println("LEEEEEEEEEEEEEEEEEEEEEEEEEENG " + npcList.npcs.size());
        for (NPCJsonData npc : npcList.npcs) {

            System.out.println("id;;;;;;;;;;;;;;;;;; " + id + " " + npc.id);
            if (npc.id.equals(id)) {
                System.out.println("ID IS SAME JAJAJA " + id + " " + npc.id);
                this.name = npc.name;
                this.text = npc.text;
            }
        }
    }

    public void interact(Player player) {
        if(!OverworldScreen.DIALOGUEMANAGER.isInDialogue) {
            OverworldScreen.DIALOGUEMANAGER.createDialogue(name, text);
            System.out.println("You dud, mah ID is " + id);
            System.out.println(name + ": " + text);
        }
        else {
            OverworldScreen.DIALOGUEMANAGER.next();
        }
        if(!OverworldScreen.DIALOGUEMANAGER.isInDialogue)
        OverworldScreen.startBattleTransition(Encounter.generateRandomEncounter());
        //   JsonUtil.readJson();
    }

    public void update(float delta) {
        super.update(delta);
    }
}
