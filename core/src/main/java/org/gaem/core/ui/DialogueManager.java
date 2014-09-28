package org.gaem.core.ui;


import com.badlogic.gdx.graphics.OrthographicCamera;
import org.gaem.core.model.TextManager;

/**
 * Created by Johan on 2014-09-27.
 */
public class DialogueManager {

    private Dialogue currentDialogue;
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
       System.out.println("The NPC says: " + TextManager.getInstance().getTextByNPC(text));
        currentDialogue = new Dialogue(cam, name,text);

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

    public boolean next() {
        if(canGoNext) {
            hideDialogue();
        }
        return false;
    }
}
