package com.nickmafra.gfx;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimpleGraphic extends Graphic {

    private final ScheduledExecutorService executor;
    private final double fps;

    public SimpleGraphic(String title, int width, int height, double fps) {
        super(title, width, height);
        this.fps = fps;
        executor = Executors.newSingleThreadScheduledExecutor();
        addOnClosingListener(e -> executor.shutdownNow());
    }

    @Override
    public void close() {
        executor.shutdownNow();
        super.close();
    }

    @Override
    public void start() {
        super.start();
        executor.scheduleAtFixedRate(super::repaint, 0, (int) (1000 / fps), TimeUnit.MILLISECONDS);
    }
}
