package com.nickmafra.gfx;

import java.awt.*;
import java.util.function.BiFunction;

public class PixelDrawer {

    private final int width;
    private final int height;
    private final int scale;
    private final BiFunction<Integer, Integer, Color> pixelColor;

    public PixelDrawer(int width, int height, int scale, BiFunction<Integer, Integer, Color> pixelColor) {
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.pixelColor = pixelColor;
    }

    public PixelDrawer(int width, int height, BiFunction<Integer, Integer, Color> pixelColor) {
        this(width, height, 1, pixelColor);
    }

    public void draw(Graphics2D g) {
        for (int y = 0; y < height; y ++) {
            for (int x = 0; x < width; x ++) {
                g.setColor(pixelColor.apply(x, y));
                g.fillRect(x * scale, y * scale, scale, scale);
            }
        }
    }
}
