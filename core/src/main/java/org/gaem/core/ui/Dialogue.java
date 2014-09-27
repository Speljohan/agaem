package org.gaem.core.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.gaem.core.AGame;
import org.gaem.core.util.TextScroller;


/**
 * Created by Johan on 2014-09-27.
 */
public class Dialogue {

    private TextScroller textScroller;
    private SpriteBatch batch;

    public Dialogue() {
        this.textScroller = new TextScroller("Hai, World!", 30);
        this.batch = new SpriteBatch();
    }

    public void update(float delta) {
        textScroller.update(delta);
    }

    public void render(float delta) {
        batch.begin();
        batch.end();
    }

}
