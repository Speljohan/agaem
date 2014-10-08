package org.gaem.core.engine;

import com.badlogic.gdx.utils.JsonValue;
import org.gaem.core.engine.json.JsonObject;
import org.gaem.core.ui.dialogue.DialogueChoiceItem;

import java.util.ArrayList;

/**
 * Created by Joar on 2014-10-08.
 */
public class NPCDialogueJsonData implements JsonObject {


    private String id;
    private String linkedID;
    private String text;
    private ArrayList<DialogueChoiceItem> choices;

    @Override
    public void deserialize(JsonValue source) {
        this.id = source.getString("id");
        this.linkedID = source.getString("linkedID");
        this.text = source.getString("text");

        choices = new ArrayList<DialogueChoiceItem>();

        JsonValue chos = source.get("choices");

        System.out.println("WILL THIS BRAKE: " + text);


        if(chos == null) {
            System.out.println("CHOCHO NULL SAD");
        }

        if(chos != null) {
            for (JsonValue d : chos) {
                DialogueChoiceItem choice = new DialogueChoiceItem();
                choice.deserialize(d);
                choices.add(choice);
            }
        }

        System.out.println(text);

    }


    public String getId() {
        return id;
    }

    public String getLinkedID() {
        return linkedID;
    }

    public String getText() {
        return text;
    }

    public ArrayList<DialogueChoiceItem> getChoices() {
        return choices;
    }
}
