package org.gaem.core.engine.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Johan on 2014-10-04.
 */
public class LongTag extends Tag<Long> {

    public LongTag(String name, long value) {
        super(name, value);
    }

    public LongTag(String name) {
        super(name);
    }

    @Override
    public short getId() {
        return 2;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeLong(value);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        value = dis.readLong();
    }
}
