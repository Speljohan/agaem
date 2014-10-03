package org.agaem.toolkit.ui;

import javax.swing.*;

/**
 * Created by Johan on 2014-10-03.
 */
public class MainUI {
    private JPanel rootPanel;
    private JPanel gamePanel;
    private JPanel layerPanel;
    private JList layerList;
    private JPanel layerControlPanel;
    private JButton layerAddButton;
    private JButton layerUpButton;
    private JButton layerDownButton;
    private JButton layerRemoveButton;
    private JPanel palettePanel;
    private JList paletteList;
    private JPanel detailsPanel;
    private JPanel propertiesPanel;

    public MainUI() {
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public JList getPaletteList() {
        return paletteList;
    }

    public JPanel getPropertiesPanel() {
        return propertiesPanel;
    }
}
