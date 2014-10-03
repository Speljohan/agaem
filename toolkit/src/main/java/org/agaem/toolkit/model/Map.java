package org.agaem.toolkit.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Johan on 2014-10-03.
 */
public class Map {

    private ArrayList<Layer> layers;
    private int width, height;
    private String name;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.layers = new ArrayList<Layer>();
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    public String getName() {
        return name;
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
