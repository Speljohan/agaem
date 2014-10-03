package org.agaem.toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import com.badlogic.gdx.backends.lwjgl.LwjglFrame;
import com.l2fprod.common.propertysheet.DefaultProperty;
import com.l2fprod.common.propertysheet.PropertySheetTable;
import com.l2fprod.common.propertysheet.PropertySheetTableModel;
import org.agaem.toolkit.game.MapEditor;
import org.agaem.toolkit.ui.MainUI;
import org.agaem.toolkit.ui.components.TileListRenderer;

import javax.swing.*;

/**
 * Created by Johan on 2014-10-03.
 */
public class Toolkit {

    private MainUI ui;
    private MapEditor editor;
    private JMenuBar menuBar;
    private PropertySheetTableModel model;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        new Toolkit();
    }

    public MainUI getUI() {
        return ui;
    }

    public void tileSelected() {
        DefaultProperty p = new DefaultProperty();
        p.setName("Tile");
        p.setValue("0");
        model.addProperty(p);
    }

    private void createMenu() {
        this.menuBar = new JMenuBar();
        menuBar.add(new JMenu("File"));
        menuBar.add(new JMenu("Options"));
        menuBar.add(new JMenu("Help"));
    }

    public Toolkit() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ui = new MainUI();
                editor = new MapEditor(Toolkit.this);
                model = new PropertySheetTableModel();
                createMenu();
                JFrame frame = new JFrame("AGaem Toolkit");
                frame.setContentPane(ui.getRootPanel());
                frame.setJMenuBar(menuBar);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ui.getPaletteList().setCellRenderer(new TileListRenderer());
                ui.getGamePanel().add(new LwjglCanvas(editor, new LwjglApplicationConfiguration()).getCanvas());
                ui.getPropertiesPanel().add(new PropertySheetTable(model));
            }
        });
    }
}
