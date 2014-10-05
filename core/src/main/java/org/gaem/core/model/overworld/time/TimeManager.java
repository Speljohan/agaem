package org.gaem.core.model.overworld.time;

import java.util.ArrayList;

/**
 * Created by Johan on 2014-10-04.
 */
public class TimeManager {

    private ArrayList<TimedEvent> events, toAdd, toRemove;

    public TimeManager() {
        this.events = new ArrayList<TimedEvent>();
        this.toAdd = new ArrayList<TimedEvent>();
        this.toRemove = new ArrayList<TimedEvent>();
    }

    public void addEvent(TimedEvent event) {
        event.setOwner(this);
        this.toAdd.add(event);
    }

    public void removeEvent(TimedEvent event) {
        this.toRemove.add(event);
    }

    public void update(float delta) {
        events.removeAll(toRemove);
        events.addAll(toAdd);

        toRemove.clear();
        toAdd.clear();

        for (TimedEvent e : events) {
            if (!e.isInitialized()) {
                e.init();
                e.setInitialized(true);
            }
            e.update(delta);
        }
    }

}
