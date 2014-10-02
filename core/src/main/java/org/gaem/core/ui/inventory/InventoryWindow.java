package org.gaem.core.ui.inventory;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import org.gaem.core.AGame;
import org.gaem.core.ui.UIWindow;

/**
 * Created by Joar on 2014-10-02.
 */
public class InventoryWindow extends UIWindow{

    private Sprite background;
    private Sprite cursor;
    private int marginX;
    private int marginY;
    private int paddingX;
    private int paddingY;

    private int cursorOriginX;
    private int cursorOriginY;

    private int windowWidth;
    private int windowHeigth;

    private int cursorX;
    private int cursorY;

    public int cursorPosX;
    public int cursorPosY;

    public boolean showWindow;

    public InventoryWindow(OrthographicCamera camera) {
        super(camera);
        background = new Sprite(AGame.ASSETS.get("sprites/chatbox.png", Texture.class));
        cursor = new Sprite(AGame.ASSETS.get("sprites/cursor.png", Texture.class));
        marginX = 10;
        marginY = 10;
        paddingX = 5;
        paddingY = 5;
        windowWidth = (int) (camera.viewportWidth * camera.zoom) - marginX * 2;
        windowHeigth = (int) (camera.viewportHeight * camera.zoom) - marginY * 2;

        cursorPosX = 0;
        cursorPosY = 0;

        cursorOriginX = marginX + paddingX;
        cursorOriginY = windowHeigth + marginX - 16 /*sprite heigth*/ - paddingY;
        cursorX = cursorOriginX;
        cursorY = cursorOriginY;


        showWindow = false;

    }


    @Override
    public void doRender(float delta) {
       // System.out.println(camera.viewportWidth + " CAMeRA WIDTH");
       // System.out.println("" + camera.zoom);
        if(showWindow) {
            drawTexture(background.getTexture(), marginX, marginY, windowWidth, windowHeigth);
            batch.draw(cursor,origin.x + cursorOriginX + 16*cursorPosX,origin.y + cursorOriginY - 16*cursorPosY);
        }
    }


    @Override
    public void doUpdate(float delta) {
       // System.out.println("INV UPDATING");
      //  System.out.println("CURSOR X PRE: " + cursorPosX);
        cursorPosX = MathUtils.clamp(cursorPosX,0,17);
      //  System.out.println("CURSOR X POST: " + cursorPosX);
       cursorPosY =  MathUtils.clamp(cursorPosY,0,12);
    }

    public void show(){
        showWindow = true;
    }

    public void hide(){
        showWindow = false;
    }

    public void toggle(){
        showWindow = !showWindow;
    }
}
