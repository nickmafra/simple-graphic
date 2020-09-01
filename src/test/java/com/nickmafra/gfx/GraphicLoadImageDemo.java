package com.nickmafra.gfx;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GraphicLoadImageDemo {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;
    public static final double FPS = 10;

    private static Image img;
    public static final String IMAGE_PATH = "./img/img_demo.png";

    public static void main(String[] args) throws IOException {
        SimpleGraphic graphic = new SimpleGraphic("Graph", WIDTH, HEIGHT, FPS);

        graphic.setDrawer(GraphicLoadImageDemo::draw);

        img = ImageIO.read(new File(IMAGE_PATH));

        graphic.start();
    }

    private static void draw(Graphics2D g) {
        g.setBackground(Color.YELLOW);
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.drawImage(img, 100, 50, null);
    }

}