package org.gaem.core.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.gaem.core.AGame;
import org.gaem.core.ui.dialogue.DialogueChoiceItem;
import org.gaem.core.ui.dialogue.DialogueCursor;
import org.gaem.core.util.TextScroller;

import java.util.ArrayList;


/**
 * Created by Johan on 2014-09-27.
 */
public class Dialogue extends UIWindow { //TODO Proper disposal.

    private TextScroller textScroller;
    private Sprite background;
    private String name;
    private ArrayList<DialogueChoiceItem> choices;
    private DialogueCursor dialogueCursor;
    private int selectedIndex;

    public Dialogue(OrthographicCamera camera,String name, String text, ArrayList<DialogueChoiceItem> choices) {
        super(camera);
        this.name = name;
        this.choices = choices;
        this.textScroller = new TextScroller(text, 0.1f);
        background = new Sprite(AGame.ASSETS.get("sprites/chatbox.png", Texture.class));
        dialogueCursor = new DialogueCursor();
        textScroller.start();

        int tempX = (int) origin.x + 50;
        int tempY = (int) origin.y + 50;

        if(choices != null){
            for(DialogueChoiceItem choice : choices){
                choice.setPosition(tempX,tempY);
                tempY -= 10;
               // dialogueCursor.select(choice);
            }

            if(choices.size() > 0){
                dialogueCursor.select(choices.get(selectedIndex));
            }
        }
    }

    public void doRender(float delta) {
        drawTexture(background.getTexture(), 0, 0, 480, 100);
        drawText(name, 10, 95);
        drawText(textScroller.getTextChunk(), 10, 80);

        dialogueCursor.render(batch);



        if(choices != null){
            for(DialogueChoiceItem choice : choices){
                choice.render(batch,delta);
            }
        }
    }

    public void doUpdate(float delta) {
        textScroller.update(delta);

    }

    public void selectNext(){
        selectedIndex += 1;
        if(selectedIndex == choices.size()){
            selectedIndex = 0;
        }
        dialogueCursor.select(choices.get(selectedIndex));
    }

    public void selectPrev(){
        selectedIndex -= 1;
        if(selectedIndex == -1){
            selectedIndex = choices.size()-1;
        }
        dialogueCursor.select(choices.get(selectedIndex));
    }


    public ArrayList<DialogueChoiceItem> getChoices() {
        return choices;
    }

    public DialogueChoiceItem getChoice() {
        return choices.get(selectedIndex);
    }
}
