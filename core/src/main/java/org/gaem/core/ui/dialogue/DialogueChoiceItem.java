package org.gaem.core.ui.dialogue;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.JsonValue;
import org.gaem.core.AGame;
import org.gaem.core.engine.json.JsonObject;

/**
 * Created by Joar on 2014-10-07.
 */
public class DialogueChoiceItem  implements JsonObject {
    private String linkedID;
    private String text;
    private boolean willInitBattle;
    private boolean willExitDialogue;
    private int x,y;

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    private BitmapFont font;

    public DialogueChoiceItem() {
        init();
    }

    public DialogueChoiceItem(String linkedID, String text, boolean willExitDialogue, boolean willInitBattle) {
        this.linkedID = linkedID;
        this.text = text;
        this.willExitDialogue = willExitDialogue;
        this.font = AGame.ASSETS.get("fonts/SILKWONDER.fnt", BitmapFont.class);
        this.willInitBattle = willInitBattle;
        font.setScale(0.3f);
        x = 0;
        y = 0;
    }

    public  void init(){
        this.font = AGame.ASSETS.get("fonts/SILKWONDER.fnt", BitmapFont.class);
        font.setScale(0.3f);
    }

    public boolean isWillExitDialogue() {
        return willExitDialogue;
    }

    public String getLinkedID() {
        return linkedID;
    }



    public String getText() {
        return text;
    }

    public void render(SpriteBatch batch,float delta){
        font.draw(batch,text,x,y);

    }

    public  void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean isWillInitBattle() {
        return willInitBattle;
    }

    @Override
    public void deserialize(JsonValue source) {

        this.linkedID = source.getString("linkedID");
        this.text = source.getString("text");
        this.willInitBattle = source.getBoolean("willInitBattle");
        this.willExitDialogue = source.getBoolean("willExitDialogue");
    }
}
