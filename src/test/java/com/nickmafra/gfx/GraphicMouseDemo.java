package com.nickmafra.gfx;

import java.awt.*;
import java.awt.event.MouseEvent;

public class GraphicMouseDemo {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final double FPS = 60;

    public static final int OBJ_WIDTH = 100;
    public static final int OBJ_HEIGHT = 50;

    private static volatile int objX = 50;
    private static volatile int objY = 80;

    public static void main(String[] args) {
        SimpleGraphic graphic = new SimpleGraphic("Graph", WIDTH, HEIGHT, FPS);

        graphic.setDrawer(GraphicMouseDemo::draw);
        graphic.addMouseActionListener(new MouseActionListenerImpl());

        graphic.start();
    }

    private static void draw(Graphics2D g) {
        g.setBackground(Color.BLACK);
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.RED);
        g.fillRect(objX, objY, OBJ_WIDTH, OBJ_HEIGHT);
    }


    private static class MouseActionListenerImpl extends MouseActionListenerAdapter {

        private volatile boolean draggingObj;
        private volatile int objRelativeX;
        private volatile int objRelativeY;

        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            System.out.println("Pressed at " + x + ", " + y);

            if (mouseIsOverObj(x, y)) {
                objRelativeX = objX - x;
                objRelativeY = objY - y;
                draggingObj = true;
            }
        }

        private static boolean mouseIsOverObj(int x, int y) {
            return objX < x && x < objX + OBJ_WIDTH
                    && objY < y && y < objY + OBJ_HEIGHT;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            if (draggingObj) {
                objX = limitRange(x + objRelativeX, 0, WIDTH - OBJ_WIDTH);
                objY = limitRange(y + objRelativeY, 0, HEIGHT - OBJ_HEIGHT);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            draggingObj = false;
        }
    }

    private static int limitRange(int value, int min, int max) {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }
}
