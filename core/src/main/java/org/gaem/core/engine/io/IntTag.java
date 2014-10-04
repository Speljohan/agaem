package org.gaem.core.engine.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Johan on 2014-10-04.
 */
public class IntTag extends Tag<Integer> {

    public IntTag(String name, int value) {
        super(name, value);
    }

    public IntTag(String name) {
        super(name);
    }

    @Override
    public short getId() {
        return 0;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(value);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        value = dis.readInt();
    }
}
