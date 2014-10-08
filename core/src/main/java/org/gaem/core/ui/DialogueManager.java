package org.gaem.core.ui;


import com.badlogic.gdx.graphics.OrthographicCamera;
import org.gaem.core.model.TextManager;
import org.gaem.core.model.battle.Encounter;
import org.gaem.core.model.overworld.NPC;
import org.gaem.core.screen.OverworldScreen;
import org.gaem.core.ui.dialogue.DialogueChoiceItem;
import org.gaem.core.ui.dialogue.DialogueItem;

import java.util.ArrayList;

/**
 * Created by Johan on 2014-09-27.
 */
public class DialogueManager {

    public Dialogue currentDialogue;
    private OrthographicCamera cam;
    public boolean isInDialogue;
    public boolean canGoNext;
    public float timer;
    private NPC curNPC;

    public DialogueManager(OrthographicCamera cam) {
        this.cam = cam;
        isInDialogue = false;
    }

    public void createDialogue(NPC npc) {
        canGoNext = false;
        isInDialogue = true;

        /* TEST AV CHOISES */
      //  System.out.println("Creating dialogue!");


        ArrayList<DialogueChoiceItem> choices = new ArrayList<DialogueChoiceItem>();
        choices.add(new DialogueChoiceItem("1","I do like watermelons",true,false));
        choices.add(new DialogueChoiceItem("1","Would you like a cup of tea?",true,false));
        choices.add(new DialogueChoiceItem("1","No, just no",true,false));
        choices.add(new DialogueChoiceItem("1","Prepare to die, fiend!",true,true));

        /*  SLUT PÅ TEST */
        curNPC = npc;

       DialogueItem text = new DialogueItem("WRING","I AM ERROR","50kok",choices);
        for(DialogueItem dialogueItem : npc.dialogues)
        {
            if(dialogueItem.getID().equals(npc.curDialogueID))
            {
                text = dialogueItem;
                break;

            }
        }

        if(text.getChoices().size() == 0){
            text.setChoices(choices);
        }
        currentDialogue = new Dialogue(cam, npc.name,text.getText(),text.getChoices());
     //   currentDialogue = new Dialogue(cam, "LOLWUT","THIS IS SUM TEXT",choices);
       // npc.setCurDialogueID(text.);

        System.out.println("Creating dialogue!");
    }

    public void hideDialogue() {
        currentDialogue = null;
        isInDialogue = false;
        curNPC = null;
     //   System.out.println("HIDING DIALOGUE");
    }

    public void render(float delta) {
        if (currentDialogue != null) {
            currentDialogue.render(delta);
        }
    }

    public void update(float delta) {
        timer += delta;
        if(timer > 1)
        {
            timer-=1;
            canGoNext = true;
        }
        if (currentDialogue != null) {
            currentDialogue.update(delta);
        }
    }

    public boolean next() { //Skall returnera
        System.out.println("NEXT DIALOGUE");
        if(canGoNext) {
            DialogueChoiceItem tempChoise = currentDialogue.getChoice();
            curNPC.setCurDialogueID(tempChoise.getLinkedID());
            if(tempChoise.isWillExitDialogue()){
                hideDialogue();
            }
            else{
                createDialogue(curNPC);

            }

            if(tempChoise.isWillInitBattle()){
                OverworldScreen.startBattleTransition(Encounter.generateRandomEncounter());
            }

        }
        return false;
    }
}
