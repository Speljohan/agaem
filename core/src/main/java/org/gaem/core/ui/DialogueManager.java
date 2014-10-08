package org.gaem.core.ui;


import com.badlogic.gdx.graphics.OrthographicCamera;
import org.gaem.core.model.TextManager;
import org.gaem.core.model.battle.Encounter;
import org.gaem.core.screen.OverworldScreen;
import org.gaem.core.ui.dialogue.DialogueChoiceItem;

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

    public DialogueManager(OrthographicCamera cam) {
        this.cam = cam;
        isInDialogue = false;
    }

    public void createDialogue(String name, String text) {
        canGoNext = false;
        isInDialogue = true;

        /* TEST AV CHOISES */

        ArrayList<DialogueChoiceItem> choices = new ArrayList<DialogueChoiceItem>();
        choices.add(new DialogueChoiceItem("1","I do like watermelons",true,false));
        choices.add(new DialogueChoiceItem("1","Would you like a cup of tea?",true,false));
        choices.add(new DialogueChoiceItem("1","No, just no",true,false));
        choices.add(new DialogueChoiceItem("1","Prepare to die, fiend!",true,true));

        /*  SLUT PÅ TEST */

        currentDialogue = new Dialogue(cam, name,text,choices);
    }

    public void hideDialogue() {
        currentDialogue = null;
        isInDialogue = false;
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
        if(canGoNext) {
            DialogueChoiceItem tempChoise = currentDialogue.getChoice();
            hideDialogue();
            if(tempChoise.isWillInitBattle()){
                OverworldScreen.startBattleTransition(Encounter.generateRandomEncounter());
            }

        }
        return false;
    }
}
