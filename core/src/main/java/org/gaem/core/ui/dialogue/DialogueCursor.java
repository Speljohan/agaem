package org.gaem.core.ui.dialogue;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.gaem.core.AGame;
import org.gaem.core.screen.OverworldScreen;

/**
 * Created by Joar on 2014-10-08.
 */
public class DialogueCursor {
    private int x,y;
    private Sprite sprite;
    private DialogueChoiceItem selected;

    public DialogueCursor(){
        x = 0;
        y = 0;
        sprite  = new Sprite(AGame.ASSETS.get("sprites/dialogue_cursorr.png", Texture.class));
    }

    public void select(DialogueChoiceItem item){
        x = item.getX() - 20;
        y = item.getY() - 16 + 2;
        selected = item;
        System.out.println("SELECTED: " + item.getText());
    }

    public void render(SpriteBatch batch) {
        batch.draw(sprite.getTexture(),x,y);
       // batch.draw(sprite.getTexture(), OverworldScreen.manager.getPlayer().realX+32,OverworldScreen.manager.getPlayer().realY-32);
      //  System.out.println("DO I RENDER?");
    }
}
