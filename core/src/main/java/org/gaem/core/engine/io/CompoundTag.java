package org.gaem.core.engine.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Johan on 2014-10-04.
 */
public class CompoundTag extends Tag<ArrayList<Tag>> {

    public CompoundTag(String name, ArrayList<Tag> value) {
        super(name, value);
    }

    public CompoundTag(String name) {
        super(name);
    }

    public void add(Tag t) {
        if (this.value == null)
            this.value = new ArrayList<Tag>();

        this.value.add(t);
    }

    public Tag get(String name) {
        for (Tag t : value) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public short getId() {
        return 3;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(value.size());
        for (Tag t : value) {
            dos.writeShort(t.getId());
            dos.writeUTF(t.getName());
            t.write(dos);
        }
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.value = new ArrayList<Tag>();
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            Tag t = TagUtils.readTag(dis);
            t.read(dis);
            this.value.add(t);
        }
    }
}
