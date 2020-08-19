package com.nickmafra.gfx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

public class Graphic {

    private final JFrame jframe;
    private final Component component;

    private Dimension dimension;

    private Consumer<Graphics2D> drawer;

    public Graphic(String title, int width, int height) {
        jframe = new JFrame(title);
        jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        component = new GraphicComponent();
        jframe.getContentPane().add(component);

        dimension = new Dimension(width, height);
    }

    public void setPanelSize(int width, int height) {
        this.dimension = new Dimension(width, height);
    }

    public void setDrawer(Consumer<Graphics2D> drawer) {
        this.drawer = drawer;
    }

    public void addOnClosingListener(Consumer<WindowEvent> listener) {
        jframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                listener.accept(e);
            }
        });
    }

    public void start() {
        component.setPreferredSize(dimension);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    public boolean isVisible() {
        return jframe.isVisible();
    }

    public void repaint() {
        jframe.repaint();
    }

    public class GraphicComponent extends JPanel {
        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (ui != null && g instanceof Graphics2D && drawer != null) {
                drawer.accept((Graphics2D) g);
            }
        }

    }

}
