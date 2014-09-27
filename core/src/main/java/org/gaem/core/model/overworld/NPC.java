package org.gaem.core.model.overworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import org.gaem.core.AGame;
import org.gaem.core.engine.NPCJsonData;
import org.gaem.core.engine.NPCList;
import org.gaem.core.screen.OverworldScreen;
import org.gaem.core.util.JsonUtil;

/**
 * Created by Joar on 2014-09-27.
 */
public class NPC extends Mobile {

   public Sprite sprite;
   public String id;
   public String name;
   public String text;

    public NPC(float x, float y, String id) {
        super(AGame.ASSETS.get("sprites/player.pack", TextureAtlas.class));
        tileX = MathUtils.floor(x / 16);
        tileY = MathUtils.floor(y / 16);

        this.id = id;

        realX = x;
        realY = y;


        this.sprite = new Sprite(AGame.ASSETS.get("sprites/player.png", Texture.class));
        this.sprite.setCenter(16, 16);
        NPCList npcList = JsonUtil.readJson();
        System.out.println("LEEEEEEEEEEEEEEEEEEEEEEEEEENG " + npcList.npcs.size());
        for(NPCJsonData npc:npcList.npcs){

            System.out.println("id;;;;;;;;;;;;;;;;;; " + id + " " + npc.id);
            if(npc.id.equals(id) )
            {
                System.out.println("ID IS SAME JAJAJA " + id + " " + npc.id);
                this.name = npc.name;
                this.text = npc.text;
            }
        }
    }

    public void interact() {
        OverworldScreen.DIALOGUEMANAGER.createDialogue(text);
        System.out.println("You dud, mah ID is " + id);
        System.out.println(name + ": " + text);
   //   JsonUtil.readJson();
    }

    public void update(float delta) {

    }
}
