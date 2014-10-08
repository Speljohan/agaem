package org.gaem.core.model.overworld.triggerables;

import org.gaem.core.AGame;
import org.gaem.core.model.overworld.Player;
import org.gaem.core.model.overworld.Triggerable;

import java.util.HashMap;

/**
 * Created by Johan on 2014-10-08.
 */
public class ScriptTrigger extends Triggerable {

    private String script;
    private HashMap<String, Object> args;

    public ScriptTrigger(float x, float y, HashMap<String, Object> args) {
        super(x, y);
        this.script = (String) args.get("script");
        this.args = args;
    }

    @Override
    public void trigger(Player player) {
        AGame.SCRIPTENGINE.executeScript(script, args);
    }
}
