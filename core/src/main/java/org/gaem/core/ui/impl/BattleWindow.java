package org.gaem.core.ui.impl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.AGame;
import org.gaem.core.model.battle.BattleProperties;
import org.gaem.core.ui.UIWindow;

/**
 * Created by Johan on 2014-09-27.
 */
public class BattleWindow extends UIWindow {

    Sprite background, pointer;
    private BattleProperties player, enemy;

    private Vector2 pointerLocation;
    private int currentSelection = 0;

    public BattleWindow(OrthographicCamera camera) {
        super(camera);
        player = new BattleProperties(47, 142, 0, 53, null, null);
        background = new Sprite(AGame.ASSETS.get("sprites/chatbox.png", Texture.class));
        pointer = new Sprite(AGame.ASSETS.get("sprites/pointer.png", Texture.class));
        font.setScale(0.6f);
        font.setColor(Color.BLACK);
        this.pointerLocation = new Vector2(-135, -45);
    }

    @Override
    public void doUpdate(float delta) {

    }

    @Override
    public void doRender(float delta) {
        drawTexture(background.getTexture(), -150, -110, 380, 100); // Actions Box
        drawTexture(background.getTexture(), 240, -110, 230, 100); // Stats Box
        drawTexture(background.getTexture(), -150, 0, 620, 350); // Main Box
        drawTexture(pointer.getTexture(), (int) pointerLocation.x, (int) pointerLocation.y);
        //System.out.println(pointerLocation);

        drawText("HP: " + player.currentHP + " / " + player.maxHP, 245, -15);
        drawText("MP: " + player.currentMP + " / " + player.maxMP, 245, -45);
        drawText("Fight", -115, -25);
        drawText("Items", -115, -65);
        drawText("Spells", 75, -25);
        drawText("Run Away man!", 75, -65);
        drawText("Crazed Capitalist Scumbag", 65, 340);
    }

    public void changeSelection(int index) {
        switch (index) {
            case 0:
                pointerLocation = new Vector2(-135, -45);
                break;
            case 1:
                pointerLocation = new Vector2(-135, -85);
                break;
            case 2:
                pointerLocation = new Vector2(95, -45);
                break;
            case 3:
                pointerLocation = new Vector2(95, -85);
                break;
        }
        System.out.println("CALLED");
    }

    public void nextSelection() {
        changeSelection(++currentSelection);
    }
}
