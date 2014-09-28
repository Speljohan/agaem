package org.gaem.core.ui.menu;

/**
 * Created by Johan on 2014-09-28.
 */
public class MenuItem {

    public boolean disabled;
    public String text;
    public int id;

    public MenuItem(boolean disabled, String text, int id) {
        this.disabled = disabled;
        this.text = text;
        this.id = id;
    }

}
