package cz.uhk.veditor.grobjekty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída Group, která je potomkem AbstractGeomObject a zároveň obsahuje seznam grafických objektů
 */
public class Group extends AbstractGeomObject {
    private List<AbstractGeomObject> objects;
    
    public Group(Point position, Color color) {
        super(position, color);
        this.objects = new ArrayList<>();
    }
    
    public Group() {
        super();
        this.objects = new ArrayList<>();
    }
    
    /**
     * Přidá objekt do skupiny
     * @param object Objekt k přidání
     */
    public void addObject(AbstractGeomObject object) {
        objects.add(object);
    }
    
    /**
     * Odebere objekt ze skupiny
     * @param object Objekt k odebrání
     * @return true pokud byl objekt odebrán, jinak false
     */
    public boolean removeObject(AbstractGeomObject object) {
        return objects.get(objects.indexOf(object)) != null && objects.remove(object);
    }
    
    /**
     * Vrátí seznam objektů ve skupině
     * @return Seznam objektů
     */
    public List<AbstractGeomObject> getObjects() {
        return objects;
    }
    
    @Override
    public boolean contains(int x, int y) {
        for (AbstractGeomObject obj : objects) {
            if (obj.contains(x, y)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void draw(Graphics2D g) {
        for (AbstractGeomObject obj : objects) {
            obj.draw(g);
        }
    }
    
    @Override
    public void setPosition(Point position) {
        int dx = position.x - this.position.x;
        int dy = position.y - this.position.y;
        
        super.setPosition(position);
        
        for (AbstractGeomObject obj : objects) {
            Point objPos = obj.getPosition();
            obj.setPosition(new Point(objPos.x + dx, objPos.y + dy));
        }
    }
    
    @Override
    public void setPosition(int x, int y) {
        setPosition(new Point(x, y));
    }
}
