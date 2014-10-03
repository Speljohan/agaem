package org.agaem.toolkit.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Johan on 2014-10-03.
 */
public class TileListRenderer extends DefaultListCellRenderer {
    private static final long serialVersionUID = -6530533873699384730L;
    public ImageIcon icon = new ImageIcon();

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        label.setText("");
        icon.setImage((BufferedImage) value);
        label.setIcon(icon);
        return label;
    }
}
