package org.gaem.core.util;

/**
 * Created by Johan on 2014-09-27.
 */
public class TextScroller {

    private String text;

    private String textChunk;
    private int index, interval;

    private boolean isRunning;

    public TextScroller(String text, int interval) {
        this.text = text;
        this.interval = interval;
        this.index = 0;
        this.isRunning = false;
    }

    public void start() {
        this.isRunning = true;
    }

    public void stop() {
        this.isRunning = false;
    }

    public void reset() {
        this.index = 0;
    }

    public void update(float delta) {
        if (isRunning) {
            if (delta >= interval) {
                textChunk = text.substring(0, index);
                index++;
            }
        }
    }

    public String getTextChunk() {
        return textChunk;
    }
}
