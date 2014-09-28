package org.gaem.core.ui.menu;

import java.util.ArrayList;

/**
 * Created by Johan on 2014-09-28.
 */
public class SelectionMenu {

    private ArrayList<MenuItem> options;

    private int selectedIndex;

    public SelectionMenu() {
        this.options = new ArrayList<MenuItem>();
    }

    public void add(MenuItem item) {
        this.options.add(item);
    }

    public int current() {
        return selectedIndex;
    }

    public void next() {
        if (selectedIndex >= options.size() - 1) {
            selectedIndex = 0;
        }
    }

    public void previous() {
        if (selectedIndex <= 0) {
            selectedIndex = options.size() - 1;
        }
    }

}
