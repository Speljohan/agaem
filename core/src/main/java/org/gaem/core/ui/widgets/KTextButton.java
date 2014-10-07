package org.gaem.core.ui.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Johan on 2014-10-06.
 */
public class KTextButton extends TextButton {

    private boolean selected;

    public KTextButton(String text, Skin skin) {
        super(text, skin);
    }

    public KTextButton(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


}
