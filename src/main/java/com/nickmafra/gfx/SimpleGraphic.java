package com.nickmafra.gfx;

import com.nickmafra.concurrent.LimitedRateThread;

public class SimpleGraphic extends Graphic {

    private final LimitedRateThread thread;

    public SimpleGraphic(String title, int width, int height, double fps) {
        super(title, width, height);
        thread = new LimitedRateThread(title + "Update", (int) (1000 / fps), super::repaint);
        addOnClosingListener(e -> interruptThread());
    }

    private void interruptThread() {
        thread.interrupt();
    }

    @Override
    public void close() {
        interruptThread();
        super.close();
    }

    @Override
    public void start() {
        super.start();
        thread.start();
    }
}
