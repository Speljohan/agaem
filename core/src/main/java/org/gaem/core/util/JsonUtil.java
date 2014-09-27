package org.gaem.core.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import org.gaem.core.engine.NPCJsonData;
import org.gaem.core.engine.NPCList;

import java.io.FileReader;
import java.io.Reader;

/**
 * Created by Joar on 2014-09-27.
 */
public class JsonUtil {
    /*String path = "assets/data/dialogue.json";
    FileReader reader = new FileReader(path);*/
    public static NPCList readJson(){
        Json json = new Json();
       // json.setElementType(MonsterFactory.class, "prototypes", MonsterPrototype.class);

        NPCList jsonData = json.fromJson(NPCList.class, Gdx.files.internal("assets/data/dialogue.json"));
        System.out.println(jsonData.npcs.get(1).text);

        return jsonData;

    }

}
