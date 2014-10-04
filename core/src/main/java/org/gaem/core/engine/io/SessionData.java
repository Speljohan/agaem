package org.gaem.core.engine.io;

import com.badlogic.gdx.files.FileHandle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Johan on 2014-10-04.
 */
public class SessionData {

    public static final FileHandle SAVEPATH = FileHandle.tempDirectory("test"); // TODO: Temporärt för att SaveLoadTest skall fungera.
    private ArrayList<Tag> saveObjects;

    public SessionData() {
        this.saveObjects = new ArrayList<Tag>();
    }

    public void add(Tag object) {
        this.saveObjects.add(object);
    }

    public Tag get(String name) {
        for (Tag t : saveObjects) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    public void save(String file) {
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(SAVEPATH.path() + file));

            dos.writeInt(saveObjects.size());

            for (Tag saveObject : saveObjects) {
                dos.writeShort(saveObject.getId());
                dos.writeUTF(saveObject.getName());
                saveObject.write(dos);
            }
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load(String file) {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(SAVEPATH.path() + file));

            int size = dis.readInt();

            for (int i = 0; i < size; i++) {
                Tag t = TagUtils.readTag(dis);
                t.read(dis);
                saveObjects.add(t);
            }
            dis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
