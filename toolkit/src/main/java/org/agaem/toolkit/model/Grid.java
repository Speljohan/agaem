package org.agaem.toolkit.model;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Johan on 2014-10-03.
 */
public class Grid {

    private int sizeX, sizeY, width, height;
    private ShapeRenderer renderer;

    public Grid(int sizeX, int sizeY, int width, int height) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.width = width;
        this.height = height;
        this.renderer = new ShapeRenderer();
    }

    public void render(float f, Camera camera) {
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                renderer.rect(x * width, y * height, width, height);
            }
        }
        renderer.end();
    }
}
