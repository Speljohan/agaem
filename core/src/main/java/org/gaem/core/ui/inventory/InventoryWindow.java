package org.gaem.core.ui.inventory;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import org.gaem.core.AGame;
import org.gaem.core.screen.OverworldScreen;
import org.gaem.core.ui.UIWindow;
import org.gaem.core.ui.inventory.items.Item;

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

    public int maxItemX = 17;
    public int maxItemY = 12;

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
            batch.draw(cursor, origin.x + cursorOriginX + 16 * cursorPosX, origin.y + cursorOriginY - 16 * cursorPosY);


            int iX = 0;
            int iY = 0;
            for (Item item : OverworldScreen.manager.getPlayer().itemList) {
                item.render(cursorOriginX + iX * 16, cursorOriginY - iY*16, origin, batch);
                iX++;
                if(iX > maxItemX){
                   iX = 0;
                   iY++;
                }
            }
        }


    }


    @Override
    public void doUpdate(float delta) {
       // System.out.println("INV UPDATING");
      //  System.out.println("CURSOR X PRE: " + cursorPosX);
        cursorPosX = MathUtils.clamp(cursorPosX,0,maxItemX);
      //  System.out.println("CURSOR X POST: " + cursorPosX);
       cursorPosY =  MathUtils.clamp(cursorPosY,0,maxItemY);
        int tempCursorPos = cursorPosY*maxItemX + cursorPosX + ((cursorPosY > 0) ? 1 : 0); //En etta läggs till om pekaren är på annan rad än första, annars kommer sista item:en i en rad vara samma som första.
       // System.out.println("");
        if(tempCursorPos < OverworldScreen.manager.getPlayer().itemList.size()){
            OverworldScreen.manager.getPlayer().activeItem = OverworldScreen.manager.getPlayer().itemList.get(tempCursorPos);
        }
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
