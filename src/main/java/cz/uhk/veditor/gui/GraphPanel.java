package cz.uhk.veditor.gui;

import cz.uhk.veditor.grobjekty.AbstractGeomObject;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphPanel extends JPanel {
    List<AbstractGeomObject> objectList;

    public GraphPanel(List<AbstractGeomObject> objectList) {
        this.objectList = objectList;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2f));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        for (AbstractGeomObject obj : objectList) {
            obj.draw(g2);
        }
    }
}
