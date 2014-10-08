package org.gaem.core.engine.script;

import com.badlogic.gdx.Gdx;
import org.jruby.embed.PathType;
import org.jruby.embed.ScriptingContainer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Johan on 2014-10-07.
 */
public class ScriptEngine {

    private ScriptingContainer scriptingContainer;

    public ScriptEngine() {
        this.scriptingContainer = new ScriptingContainer();
        this.scriptingContainer.setLoadPaths(Arrays.asList("ruby"));
        scriptingContainer.runScriptlet(PathType.CLASSPATH, "bootstrap.rb");
    }

    public void executeScript(String name, HashMap<String, Object> args) {
        fixHashMap(args);
        scriptingContainer.put("args", args);
        scriptingContainer.runScriptlet(PathType.CLASSPATH, Gdx.files.internal("scripts/" + name + ".rb").path());
    }

    private void fixHashMap(HashMap<String, Object> args) {
        for (Map.Entry<String, Object> o : args.entrySet()) {
            try {
                int i = Integer.parseInt((String) o.getValue());
                args.put(o.getKey(), i);
            } catch (Exception e) {
            }
        }
    }

}
