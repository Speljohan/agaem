package org.gaem.core.model;

import org.gaem.core.model.overworld.NPC;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joar on 2014-09-27.
 */
public class TextManager {
    private static TextManager instance = null;

    private HashMap<String, String> list;

    private TextManager() {
        System.out.println("WAT");

        list = new HashMap<String, String>();

        list.put("npc_test","You dude. I'm Hitler!");
    }

    public static TextManager getInstance() {
        if(instance == null) {
            instance = new TextManager();
        }

        return instance;
    }

    public String getTextByNPC(String id){

        if(list.containsKey(id)){
            //WOW SO MUCH ID
            return list.get(id);
        }
        else{
            //Key finns inte, något är fel.
            return "HALP I AM NOT EXIST!";
        }
    }
}