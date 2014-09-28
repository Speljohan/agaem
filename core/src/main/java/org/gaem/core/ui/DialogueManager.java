package org.gaem.core.ui;


import com.badlogic.gdx.graphics.OrthographicCamera;
import org.gaem.core.model.TextManager;

/**
 * Created by Johan on 2014-09-27.
 */
public class DialogueManager {

    private Dialogue currentDialogue;
    private OrthographicCamera cam;

    public DialogueManager(OrthographicCamera cam) {
        this.cam = cam;
    }

    public void createDialogue(String name, String text) {
       System.out.println("The NPC says: " + TextManager.getInstance().getTextByNPC(text));
        currentDialogue = new Dialogue(cam, name,text);

    }

    public void hideDialogue() {
        currentDialogue = null;
    }

    public void render(float delta) {
        if (currentDialogue != null) {
            currentDialogue.render(delta);
        }
    }

    public void update(float delta) {
        if (currentDialogue != null) {
            currentDialogue.update(delta);
        }
    }
}
