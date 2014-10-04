package org.gaem.core.model.overworld.time;

/**
 * Created by Johan on 2014-10-04.
 */
public abstract class TimedEvent {

    private float elapsed, interval;

    public TimeManager owner;

    public TimedEvent(float interval) {
        this.interval = interval;
        this.elapsed = 0;
    }

    public void setOwner(TimeManager owner) {
        this.owner = owner;
    }

    public void update(float delta) {
        elapsed += delta;

        if (elapsed >= interval) {
            elapsed = 0;
            onTrigger();
        }
    }

    public abstract void onTrigger();
}
