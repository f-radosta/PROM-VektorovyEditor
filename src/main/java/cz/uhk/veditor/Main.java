package cz.uhk.veditor;

import cz.uhk.veditor.gui.MainWindow;

import javax.swing.*;

/**
 * Startovaci trida aplikace vEditor
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new MainWindow().setVisible(true);
        });
    }
}
