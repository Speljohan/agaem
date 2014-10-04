package org.gaem.core.engine.io;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by Johan on 2014-10-04.
 */
public class TagUtils {

    public static Tag readTag(DataInputStream dis) throws IOException {
        int id = dis.readShort();
        String name = dis.readUTF();
        Tag t = null;

        switch (id) {
            case 0: // Integer
                t = new IntTag(name);
                break;
            case 1: // String
                t = new StringTag(name);
                break;
            case 2: // Long
                t = new LongTag(name);
                break;
            case 3: // Compound
                t = new CompoundTag(name);
                break;
            default:
                System.out.println("Unrecognised Tag ID: " + id);
                break;
        }
        return t;
    }
}
