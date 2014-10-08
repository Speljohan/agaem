package org.gaem.core.engine.script;

import org.gaem.core.model.overworld.NPC;
import org.gaem.core.screen.OverworldScreen;

/**
 * Created by Johan on 2014-10-07.
 */
public class ScriptAPI {

    public static void teleport(String mapName, int x, int y) {
        OverworldScreen.mapManager.loadMap(mapName);
        OverworldScreen.manager.getPlayer().setLocation(x * 16, (OverworldScreen.mapManager.height - (y * 16)));
    }

    public static void spawn(String name, int x, int y) {
        // TODO: Göra om delar av NPC-systemet.
    }

    public static boolean busy() {
        return OverworldScreen.DIALOGUEMANAGER.isInDialogue;
    }

    public static void dialogue(NPC caller) {
        OverworldScreen.DIALOGUEMANAGER.createDialogue(caller);
    }

}
