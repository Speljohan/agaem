package org.gaem.core.engine;

import com.badlogic.gdx.utils.JsonValue;
import org.gaem.core.engine.json.JsonObject;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Created by Joar on 2014-09-27.
 */
public class NPCList implements JsonObject {
    public ArrayList<NPCJsonData> npcs;

    @Override
    public void deserialize(JsonValue source) {
        npcs = new ArrayList<NPCJsonData>();
        JsonValue npcs = source.get("npcs");

        for (JsonValue d : npcs) {
            NPCJsonData npc = new NPCJsonData();
            npc.deserialize(d);
            this.npcs.add(npc);
        }
    }
}
