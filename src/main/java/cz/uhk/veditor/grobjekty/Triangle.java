package cz.uhk.veditor.grobjekty;

import java.awt.*;

public class Triangle extends AbstractGeomObject {
    protected int size;

    public Triangle(Point position, int size, Color color) {
        super(position, color);
        this.size = size;
    }

    public Triangle(int size) {
        this.size = size;
    }

    @Override
    public boolean contains(int x, int y) {
        int x1 = position.x;
        int y1 = position.y + (int)(size * Math.sqrt(3)/2);
        int x2 = position.x + size;
        int y2 = position.y + (int)(size * Math.sqrt(3)/2);
        int x3 = position.x + size/2;
        int y3 = position.y;
        
        if (x < x1 || x > x2 || y < y3 || y > y1) {
            return false;
        }
        
        // Line 1: spodni hrana (x1,y1) to (x2,y2)
        if ((y1 - y) * (x2 - x1) - (x1 - x) * (y2 - y1) < 0) {
            return false;
        }
        
        // Line 2: prava hrana (x2,y2) to (x3,y3)
        if ((y2 - y) * (x3 - x2) - (x2 - x) * (y3 - y2) < 0) {
            return false;
        }
        
        // Line 3: leva hrana (x3,y3) to (x1,y1)
        if ((y3 - y) * (x1 - x3) - (x3 - x) * (y1 - y3) < 0) {
            return false;
        }
        
        return true;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        
        int[] xPoints = {position.x, position.x + size, position.x + size/2};
        int[] yPoints = {position.y + (int)(size * Math.sqrt(3)/2), position.y + (int)(size * Math.sqrt(3)/2), position.y};
        
        g.drawPolygon(xPoints, yPoints, 3);
    }
}
