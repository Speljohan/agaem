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

    private final int SIZE = 32;

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
        paddingX = 10;
        paddingY = -10; //Skall vara 10 om SIZE == 16, -10 om SIZE == 32. Måste räknas ut automatiskt i framtiden.
        windowWidth = (int) (camera.viewportWidth * camera.zoom) - marginX * 2;
        windowHeigth = (int) (camera.viewportHeight * camera.zoom) - marginY * 2;

        cursorPosX = 0;
        cursorPosY = 0;

        cursorOriginX = marginX + paddingX;
        cursorOriginY = windowHeigth + marginX - SIZE /*sprite heigth*/ - paddingY;
        cursorX = cursorOriginX;
        cursorY = cursorOriginY;


        showWindow = false;

        maxItemX = (int)MathUtils.floor((17 / (SIZE / 16)));
        maxItemY = (int)MathUtils.floor((12 / (SIZE / 16)));
    }


    @Override
    public void doRender(float delta) {
       // System.out.println(camera.viewportWidth + " CAMeRA WIDTH");
       // System.out.println("" + camera.zoom);
        if(showWindow) {
            drawTexture(background.getTexture(), marginX, marginY, windowWidth, windowHeigth);
            batch.draw(cursor, origin.x + cursorOriginX + SIZE * cursorPosX, origin.y + cursorOriginY - SIZE * cursorPosY);


            int iX = 0;
            int iY = 0;
            for (Item item : OverworldScreen.manager.getPlayer().itemList) {
                item.render(cursorOriginX + iX * SIZE, cursorOriginY - iY*SIZE, origin, batch);
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
        if(cursorPosX > maxItemX)
        {
            cursorPosX = 0;
        }
        if(cursorPosX < 0)
        {
            cursorPosX = maxItemX;
        }
        if(cursorPosY > maxItemY)
        {
            cursorPosY = 0;
        }
        if(cursorPosY < 0)
        {
            cursorPosY = maxItemY;
        }
        cursorPosX = MathUtils.clamp(cursorPosX,0,maxItemX);
      //  System.out.println("CURSOR X POST: " + cursorPosX);
       cursorPosY =  MathUtils.clamp(cursorPosY,0,maxItemY);
        int tempCursorPos = cursorPosY*maxItemX + cursorPosX + cursorPosY*1; //En etta läggs till om pekaren är på annan rad än första, annars kommer sista item:en i en rad vara samma som första.
      // System.out.println("Cursorpos: " + tempCursorPos + " X: " + cursorPosX + " Y: " + cursorPosY);
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
