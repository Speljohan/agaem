package org.gaem.core.model.overworld;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Johan on 2014-09-28.
 */
public class EntityManager {

    private ArrayList<Entity> toAdd, toRemove, entities;
    private Player player;

    public EntityManager() {
        this.toAdd = new ArrayList<Entity>();
        this.toRemove = new ArrayList<Entity>();
        this.entities = new ArrayList<Entity>();
    }

    public void add(Entity e) {
        e.manager = this;
        toAdd.add(e);
    }

    public void remove(Entity e) {
        toRemove.add(e);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        player.manager = this;
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void render(float delta, SpriteBatch batch) {
        player.render(delta, batch);
        for (Entity e : entities) {
            e.render(delta, batch);
        }
    }

    public void update(float delta) {
        entities.removeAll(toRemove);
        entities.addAll(toAdd);

        toRemove.clear();
        toAdd.clear();
        player.update(delta);
        for (Entity e : entities) {
            e.update(delta);
        }
    }
}
