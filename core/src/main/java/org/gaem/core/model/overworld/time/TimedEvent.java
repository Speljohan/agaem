package org.gaem.core.model.overworld.time;

/**
 * Created by Johan on 2014-10-04.
 */
public abstract class TimedEvent {

    public TimeManager owner;
    private float elapsed, interval;
    private boolean isInitialized;
    public TimedEvent(float interval) {
        this.interval = interval;
        this.elapsed = 0;
        this.isInitialized = false;
    }

    public void setOwner(TimeManager owner) {
        this.owner = owner;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        this.isInitialized = initialized;
    }

    public void update(float delta) {
        elapsed += delta;

        if (elapsed >= interval) {
            elapsed = 0;
            onTrigger();
        }
    }

    public abstract void onTrigger();

    public abstract void init();
}
