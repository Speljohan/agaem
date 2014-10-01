package org.gaem.core.engine.json;

import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by Johan on 2014-10-01.
 */
public interface JsonObject {

    void deserialize(JsonValue source);
}
