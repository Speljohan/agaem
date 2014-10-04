package org.gaem.core.engine.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Johan on 2014-10-04.
 */
public abstract class Tag<T> {

    protected T value;
    private String name;

    public Tag(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public Tag(String name) {
        this.name = name;
    }

    public abstract short getId();

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public abstract void write(DataOutputStream dos) throws IOException;

    public abstract void read(DataInputStream dis) throws IOException;

}
