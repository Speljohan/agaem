package org.gaem.core.ui.impl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import org.gaem.core.AGame;
import org.gaem.core.model.battle.BattleProperties;
import org.gaem.core.model.battle.Encounter;
import org.gaem.core.model.battle.SkillResult;
import org.gaem.core.model.battle.skill.Skill;
import org.gaem.core.model.battle.skill.impl.BongStrike;
import org.gaem.core.model.battle.skill.impl.Kumbaja;
import org.gaem.core.model.battle.skill.impl.ProbationNotice;
import org.gaem.core.model.battle.skill.impl.RacialBash;
import org.gaem.core.ui.UIWindow;

import java.util.ArrayList;

/**
 * Created by Johan on 2014-09-27.
 */
public class BattleWindow extends UIWindow {

    Sprite background, pointer;
    private Encounter encounter;

    private Vector2 pointerLocation;
    private int currentSelection = 0;
    private int currentMenu = 0;
    private int optionsIndices = 3;
    private String lastLog;

    public BattleWindow(OrthographicCamera camera, Encounter encounter) {
        super(camera);
        ArrayList<Skill> skills = new ArrayList<Skill>();
        skills.add(new RacialBash());
        skills.add(new BongStrike());
        skills.add(new ProbationNotice());
        skills.add(new Kumbaja());
        this.encounter = new Encounter(new BattleProperties("you", 47, 142, 0, 53, skills, null), new BattleProperties("Tea Party Member", 15, 15, 15, 15, null, null));
        this.lastLog = "";

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

        drawText("HP: " + encounter.getPlayer().currentHP + " / " + encounter.getPlayer().maxHP, 245, -15);
        drawText("MP: " + encounter.getPlayer().currentMP + " / " + encounter.getPlayer().maxMP, 245, -45);

        if (currentMenu == 0) {
            drawText("Fight", -115, -25);
            drawText("Items", -115, -65);
            drawText("Spells", 75, -25);
            drawText("Run Away man!", 75, -65);
        } else if (currentMenu == 1) {
            Skill s1 = encounter.getPlayer().skills.get(0);
            drawText(s1.getName(), -115, -25);
            Skill s2 = encounter.getPlayer().skills.get(1);
            if (s2 != null)
                drawText(s2.getName(), -115, -65);
            Skill s3 = encounter.getPlayer().skills.get(2);
            if (s3 != null)
                drawText(s3.getName(), 75, -25);
            Skill s4 = encounter.getPlayer().skills.get(3);
            if (s4 != null)
                drawText(s4.getName(), 75, -65);
        }
        drawText("Tea Party Member", 65, 340);
        drawText("HP: " + encounter.getEnemy().currentHP + " / " + encounter.getEnemy().maxHP, 65, 320);
        drawText("MP: " + encounter.getEnemy().currentMP + " / " + encounter.getEnemy().maxMP, 65, 300);
        drawTextWrapped(lastLog, -280, -60, 550);
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
                pointerLocation = new Vector2(55, -45);
                break;
            case 3:
                pointerLocation = new Vector2(55, -85);
                break;
        }
        System.out.println("CALLED");
    }

    public void select() {
        if (currentMenu == 1) {
            SkillResult r = encounter.useAttack(encounter.getPlayer().skills.get(currentSelection), true);
            lastLog = r.log.replace("{target}", encounter.getEnemy().name);
        } else {
            currentMenu = currentSelection + 1;
        }
    }

    public void rootMenu() {
        currentMenu = 0;
    }

    public void nextSelection() {
        if (currentSelection == optionsIndices) {
            currentSelection = -1;
        }
        changeSelection(++currentSelection);
    }

    public void previousSelection() {
        if (currentSelection == 0) {
            currentSelection = 4;
        }
        changeSelection(--currentSelection);
    }
}
