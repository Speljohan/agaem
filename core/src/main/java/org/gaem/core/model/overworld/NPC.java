package org.gaem.core.model.overworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.JsonValue;
import org.gaem.core.AGame;
import org.gaem.core.engine.NPCDialogueJsonData;
import org.gaem.core.engine.NPCJsonData;
import org.gaem.core.engine.NPCList;
import org.gaem.core.engine.SpriteSheet;
import org.gaem.core.model.battle.Encounter;
import org.gaem.core.screen.OverworldScreen;
import org.gaem.core.ui.dialogue.DialogueChoiceItem;
import org.gaem.core.ui.dialogue.DialogueItem;
import org.gaem.core.util.JsonUtil;

import java.util.ArrayList;

/**
 * Created by Joar on 2014-09-27.
 */
public class NPC extends Mobile {

    public String id;
    public String curDialogueID;
    public String name;
    public String text;
    public ArrayList<DialogueItem> dialogues;

    public NPC(float x, float y, String id) {
        super(new SpriteSheet(AGame.ASSETS.get("sprites/player_newnew.png", Texture.class), 16, 16));
        solid = true;
        tileX = MathUtils.floor(x / 16);
        tileY = MathUtils.floor(y / 16);

        dialogues = new ArrayList<DialogueItem>();

        curDialogueID = "1";

        this.id = id;

        realX = x;
        realY = y;


        NPCList npcList = new NPCList();
        JsonValue val = AGame.ASSETS.get("data/dialogue.json");
        npcList.deserialize(val);
        for (NPCJsonData npc : npcList.npcs) {
            if (npc.id.equals(id)) {
                this.name = npc.name;
                this.text = npc.text;

                for(NPCDialogueJsonData jDia : npc.dialogues){
                    dialogues.add(new DialogueItem(jDia.getId(),jDia.getText(),jDia.getLinkedID(),jDia.getChoices()));
                }
            }
        }
    }

    public void interact(Player player) {
        if(!OverworldScreen.DIALOGUEMANAGER.isInDialogue) {
            OverworldScreen.DIALOGUEMANAGER.createDialogue(this);

        }
        else {

        }
        if(!OverworldScreen.DIALOGUEMANAGER.isInDialogue)
        OverworldScreen.startBattleTransition(Encounter.generateRandomEncounter());
        //   JsonUtil.readJson();
    }

    public void setCurDialogueID(String curDialogueID) {
        this.curDialogueID = curDialogueID;
    }

    public void update(float delta) {
        super.update(delta);
    }
}
