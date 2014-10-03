package org.agaem.toolkit.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Johan on 2014-10-03.
 */
public class Map {

    private ArrayList<Layer> layers;
    private int width, height;

    public Map() {

    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void render(float delta, SpriteBatch batch) {
        for (Layer l : layers) {
            l.render(delta, batch);
        }
    }
}
