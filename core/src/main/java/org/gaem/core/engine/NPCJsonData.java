package org.gaem.core.engine;

import com.badlogic.gdx.utils.JsonValue;
import org.gaem.core.engine.json.JsonObject;

import java.util.ArrayList;

/**
 * Created by Joar on 2014-09-27.
 */
public class NPCJsonData implements JsonObject { //TODO Incorporate the JsonData-files to the real classes.
    public String id;
    public String name;
    public String text;
    public ArrayList<NPCDialogueJsonData> dialogues;

    @Override
    public void deserialize(JsonValue source) {
        this.id = source.getString("id");
        this.name = source.getString("name");
        this.text = source.getString("text");

        dialogues = new ArrayList<NPCDialogueJsonData>();

        JsonValue dias = source.get("dialogues");

        for (JsonValue d : dias) {
            NPCDialogueJsonData dia = new NPCDialogueJsonData();
            dia.deserialize(d);
            dialogues.add(dia);
        }
    }
}
