package org.gaem.core.engine.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Johan on 2014-10-04.
 */
public class StringTag extends Tag<String> {

    public StringTag(String name, String value) {
        super(name, value);
    }

    public StringTag(String name) {
        super(name);
    }

    @Override
    public short getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeUTF(value);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        value = dis.readUTF();
    }
}
