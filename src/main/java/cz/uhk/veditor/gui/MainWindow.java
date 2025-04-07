package cz.uhk.veditor.gui;

import cz.uhk.veditor.grobjekty.AbstractGeomObject;
import cz.uhk.veditor.grobjekty.Circle;
import cz.uhk.veditor.grobjekty.Square;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hlavni okno aplikace
 */
public class MainWindow extends JFrame {

    private List<AbstractGeomObject> objekty = new ArrayList<>();

    public MainWindow() {
        super("Vektorový editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initTestData();

        add(new GraphPanel(objekty), BorderLayout.CENTER);

        setSize(800, 600);
        setLocationRelativeTo(null);
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
