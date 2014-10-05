package org.gaem.core.engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

/**
 * Created by Johan on 2014-09-28.
 */
public class SpriteSheet {

    private Texture source;
    private int width, height;

    private TextureRegion[] slices;

    public SpriteSheet(Texture source, int width, int height) {
        this.source = source;
        this.width = width;
        this.height = height;
        process();
    }

    private void process() {
        ArrayList<TextureRegion> regions = new ArrayList<TextureRegion>();
        int i = 0;
        for (int y = 0; y < source.getHeight() / height; y++) {
            for (int x = 0; x < source.getWidth() / width; x++) {
                regions.add(new TextureRegion(source, x * width, y * height, width, height));
            }
        }
        slices = regions.toArray(new TextureRegion[i]);
    }

    public TextureRegion get(int index) {
        return slices[index];
    }

    public TextureRegion get(int row, int column) {
        return slices[row * getColumnCount() + column];
    }

    public int getColumnCount() {
        return source.getHeight() / height;
    }

    public int getRowCount() {
        return source.getWidth() / width;
    }
}
