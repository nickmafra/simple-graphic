package com.nickmafra.gfx;

public class SimpleGraphic extends Graphic {

    private final LimitedRateThread thread;

    public SimpleGraphic(String title, int width, int height, double fps) {
        super(title, width, height);
        thread = new LimitedRateThread((int) (1000 / fps), super::repaint);
        addOnClosingListener(e -> thread.interrupt());
    }

    @Override
    public void start() {
        super.start();
        thread.start();
    }
}
