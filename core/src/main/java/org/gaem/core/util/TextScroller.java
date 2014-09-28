package org.gaem.core.util;

/**
 * Created by Johan on 2014-09-27.
 */
public class TextScroller {

    private String text;

    private String textChunk;
    private int index;
    private float interval, current;

    private boolean isRunning;

    public TextScroller(String text, float interval) {
        this.text = text;
        this.textChunk = "";
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
            current += delta;
            if (current >= interval) {
                current = 0;
                if (index >= text.length()) {
                    isRunning = false;
                    stop();
                }

                textChunk = text.substring(0, index);
                index++;
            }
        }
    }

    public String getTextChunk() {
        return textChunk;
    }
}
