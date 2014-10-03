package org.agaem.toolkit.io;

import org.agaem.toolkit.model.Layer;
import org.agaem.toolkit.model.Map;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Johan on 2014-10-03.
 */
public class MapWriter {

    public MapWriter() {

    }

    public void write(Map map) {
        File f = new File(map.getName() + ".agm");

        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
            dos.writeUTF(map.getName());
            dos.writeInt(map.getWidth());
            dos.writeInt(map.getHeight());
            dos.writeInt(map.getLayers().size());
            for (Layer l : map.getLayers()) {
                dos.writeUTF(l.getTag());
                for (int x = 0; x < map.getWidth(); x++) {
                    for (int y = 0; y < map.getHeight(); y++) {
                        dos.writeInt(l.getTiles()[x][y].getTileIdx());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
