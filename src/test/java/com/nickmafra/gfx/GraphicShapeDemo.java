package com.nickmafra.gfx;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GraphicShapeDemo {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final double FPS = 10;

    public static final long ROTATION_PERIOD = 2000;
    public static final double CENTER_X = 500;
    public static final double CENTER_Y = 400;
    public static final double ROTATION_RADIUS = 100;
    public static final double CIRCLE_RADIUS = 10;

    private static long startTime;

    public static void main(String[] args) {
        SimpleGraphic graphic = new SimpleGraphic("Graph", WIDTH, HEIGHT, FPS);

        graphic.setDrawer(GraphicShapeDemo::draw);

        startTime = System.currentTimeMillis();
        graphic.start();
    }

    private static void draw(Graphics2D g) {
        g.setBackground(Color.BLUE);
        g.clearRect(0, 0, WIDTH, HEIGHT);

        double deltaTime = System.currentTimeMillis() - startTime;
        double angle = (2 * Math.PI * deltaTime) / ROTATION_PERIOD;
        Shape shape = new Ellipse2D.Double(
                CENTER_X + ROTATION_RADIUS * Math.cos(angle),
                CENTER_Y + ROTATION_RADIUS * Math.sin(angle),
                CIRCLE_RADIUS, CIRCLE_RADIUS);

        g.setColor(Color.YELLOW);
        g.draw(shape);
    }

}