package org.agaem.toolkit.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Johan on 2014-10-03.
 */
public class Layer {

    private Tile[][] tiles;
    private Map parent;
    private String tag;

    public Layer(Tile[][] tiles, Map parent) {
        this.tiles = tiles;
        this.parent = parent;
    }

    public Layer(Map parent) {
        this.parent = parent;
        generateEmptyLayer();
    }

    public String getTag() {
        return tag;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    private void generateEmptyLayer() {
        for (int x = 0; x < parent.getWidth(); x++) {
            for (int y = 0; y < parent.getHeight(); y++) {
                tiles[x][y] = new Tile(-1);
            }
        }
    }

    public void render(float delta, SpriteBatch batch) {
        for (int x = 0; x < parent.getWidth(); x++) {
            for (int y = 0; y < parent.getHeight(); y++) {
                tiles[x][y].render(delta, x, y, 16, 16, batch);
            }
        }
    }

}
