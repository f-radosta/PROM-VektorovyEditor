package cz.uhk.veditor.grobjekty;

import java.awt.*;

public class Rectangle extends AbstractGeomObject {
    protected int width;
    protected int height;

    public Rectangle(Point position, int width, int height, Color color) {
        super(position, color);
        this.width = width;
        this.height = height;
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean contains(int x, int y) {
        return x >= position.x && x <= position.x + width
                && y >= position.y && y <= position.y + height;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.drawRect(position.x, position.y, width, height);
    }
}
