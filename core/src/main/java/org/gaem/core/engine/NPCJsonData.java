package org.gaem.core.engine;

import com.badlogic.gdx.utils.JsonValue;
import org.gaem.core.engine.json.JsonObject;

/**
 * Created by Joar on 2014-09-27.
 */
public class NPCJsonData implements JsonObject {
    public String id;
    public String name;
    public String text;

    @Override
    public void deserialize(JsonValue source) {
        this.id = source.getString("id");
        this.name = source.getString("name");
        this.text = source.getString("text");
    }
}
