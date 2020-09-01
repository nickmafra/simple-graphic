package com.nickmafra.gfx;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GraphicLayeredDemo {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final double FPS = 10;

    private static long startTime;

    public static void main(String[] args) {
        SimpleGraphic graphic = new SimpleGraphic("Graph", WIDTH, HEIGHT, FPS);

        graphic.setDrawer(GraphicLayeredDemo::drawAll);

        startTime = System.currentTimeMillis();
        graphic.start();
    }

    private static void drawAll(Graphics2D g) {
        long deltaTime = System.currentTimeMillis() - startTime;
        drawRainbow(deltaTime, g);
        drawCircle(deltaTime, g);
        drawText(deltaTime, g);
    }

    //
    // LAYER 1
    //

    public static final float TIME_PERIOD = 2000;
    public static final float PERIODIC_X_LENGTH = 400;
    public static final float PERIODIC_Y_LENGTH = 200;

    private static Color getRainbowColor(long deltaTime, int x, int y) {
        float hue = (x / PERIODIC_X_LENGTH + y / PERIODIC_Y_LENGTH + deltaTime / TIME_PERIOD) % 1;
        return Color.getHSBColor(hue, 1, 0.9F);
    }

    private static void drawRainbow(long deltaTime, Graphics2D g) {
        PixelDrawer.draw(g, WIDTH, HEIGHT, 1, (x, y) -> getRainbowColor(deltaTime, x, y));
    }

    //
    // LAYER 2
    //

    public static final long ROTATION_PERIOD = 2000;
    public static final double CENTER_X = 500;
    public static final double CENTER_Y = 400;
    public static final double ROTATION_RADIUS = 100;
    public static final double CIRCLE_RADIUS = 10;

    private static void drawCircle(long deltaTime, Graphics2D g) {
        double angle = (2 * Math.PI * deltaTime) / ROTATION_PERIOD;
        Shape shape = new Ellipse2D.Double(
                CENTER_X + ROTATION_RADIUS * Math.cos(angle),
                CENTER_Y + ROTATION_RADIUS * Math.sin(angle),
                CIRCLE_RADIUS, CIRCLE_RADIUS);

        g.setColor(Color.BLACK);
        g.fill(shape);
    }

    //
    // LAYER 3
    //

    public static final int FONT_SIZE = 20;
    private static final Font font = new Font(null, Font.PLAIN, FONT_SIZE);

    private static void drawText(long deltaTime, Graphics2D g) {
        g.setFont(font);
        String texto = "Tempo: " + (deltaTime / 1000);
        g.drawString(texto, 50, 50);
    }
}
