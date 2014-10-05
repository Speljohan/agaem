package org.gaem.core.model.overworld.time;

/**
 * Created by Johan on 2014-10-05.
 */
public abstract class SingleExecutionTimedEvent extends TimedEvent {

    public SingleExecutionTimedEvent() {
        super(0);
    }

    @Override
    public void onTrigger() {

    }

    @Override
    public void init() {
        execute();
        owner.removeEvent(this);
    }

    public abstract void execute();
}
