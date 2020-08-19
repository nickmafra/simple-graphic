package com.nickmafra.gfx;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GraphicPixelDemo {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final double FPS = 60;

    public static final float TIME_PERIOD = 2000;
    public static final float PERIODIC_X_LENGTH = 200;
    public static final float PERIODIC_Y_LENGTH = 100;

    private static long startTime;

    public static void main(String[] args) {
        SimpleGraphic graphic = new SimpleGraphic("Graph", WIDTH, HEIGHT, FPS);

        PixelDrawer pixelDrawer = new PixelDrawer(WIDTH, HEIGHT, GraphicPixelDemo::getColor);
        graphic.setDrawer(pixelDrawer::draw);

        startTime = System.currentTimeMillis();
        graphic.start();
    }

    private static Color getColor(int x, int y) {
        long deltaTime = System.currentTimeMillis() - startTime;
        float hue = (x / PERIODIC_X_LENGTH + y / PERIODIC_Y_LENGTH + deltaTime / TIME_PERIOD) % 1;
        return Color.getHSBColor(hue, 1, 0.9F);
    }

}