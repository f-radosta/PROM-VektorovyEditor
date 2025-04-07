package cz.uhk.veditor.gui;

import cz.uhk.veditor.grobjekty.AbstractGeomObject;
import cz.uhk.veditor.grobjekty.Circle;
import cz.uhk.veditor.grobjekty.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Hlavni okno aplikace
 */
public class MainWindow extends JFrame {
    private List<AbstractGeomObject> objekty = new ArrayList<>();
    private JToolBar toolbar;
    private JToggleButton btSquare;
    private JToggleButton btCircle;
    private JToggleButton btSelect;
    private AbstractGeomObject selectedObject = null;
    private Point lastMousePosition = null;

    public MainWindow() {
        super("Vektorový editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initTestData();

        createToolbar();

        GraphPanel graphPanel = new GraphPanel(objekty);
        add(graphPanel, BorderLayout.CENTER);
        graphPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (btSquare.isSelected()) {
                        objekty.add(new Square(e.getPoint(), 10, Color.RED));
                        graphPanel.repaint();
                    } else if (btCircle.isSelected()) {
                        objekty.add(new Circle(e.getPoint(), 50, Color.BLUE));
                        graphPanel.repaint();
                    }
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && btSelect.isSelected()) {
                    selectedObject = null;
                    for (int i = objekty.size() - 1; i >= 0; i--) {
                        AbstractGeomObject obj = objekty.get(i);
                        if (obj.contains(e.getX(), e.getY())) {
                            selectedObject = obj;
                            lastMousePosition = e.getPoint();
                            break;
                        }
                    }
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && btSelect.isSelected()) {
                    selectedObject = null;
                    lastMousePosition = null;
                }
            }
        });
        
        graphPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (btSelect.isSelected() && selectedObject != null && lastMousePosition != null) {
                    int dx = e.getX() - lastMousePosition.x;
                    int dy = e.getY() - lastMousePosition.y;
                    
                    Point currentPos = selectedObject.getPosition();
                    selectedObject.setPosition(currentPos.x + dx, currentPos.y + dy);
                    
                    lastMousePosition = e.getPoint();
                    
                    graphPanel.repaint();
                }
            }
        });

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void createToolbar() {
        toolbar = new JToolBar(JToolBar.HORIZONTAL);
        
        btSelect = new JToggleButton("Výběr", new ImageIcon(getClass().getResource("/sipka.png")));
        btSelect.setToolTipText("Nástroj pro výběr a přesun objektů");
        
        btSquare = new JToggleButton("Ctverec", new ImageIcon(getClass().getResource("/ctverec.png")));
        btCircle = new JToggleButton("Kruznice", new ImageIcon(getClass().getResource("/kolecko.png")));
        
        toolbar.add(btSelect);
        toolbar.add(btSquare);
        toolbar.add(btCircle);
        
        ButtonGroup group = new ButtonGroup();
        group.add(btSelect);
        group.add(btSquare);
        group.add(btCircle);
        
        btSelect.setSelected(true);
        
        add(toolbar, BorderLayout.NORTH);
    }

    private void initTestData() {
        objekty.add(new Circle(new Point(100,100),50,Color.BLUE));
        objekty.add(new Square(new Point(200,100),10,Color.RED));
        objekty.add(new Circle(new Point(100,300),60,Color.GREEN));
        objekty.add(new Circle(new Point(500,100),70,Color.YELLOW));
        objekty.add(new Square(new Point(200,600),40,Color.MAGENTA));
        objekty.add(new Circle(new Point(300,300),70,Color.ORANGE));
        objekty.add(new Square(new Point(600,400),30,Color.BLACK));
    }
}
