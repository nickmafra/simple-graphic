package com.nickmafra.gfx;

public class LimitedRateThread extends Thread {

    private final long period;
    private final CheckedRunnable<InterruptedException> runnable;

    public LimitedRateThread(long period, CheckedRunnable<InterruptedException> runnable) {
        this.period = period;
        this.runnable = runnable;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {

                long startTime = now();
                runnable.run();
                long duration = now() - startTime;

                long remaining = period - duration;
                if (remaining > 0)
                    sleep(remaining);

            }
        } catch (InterruptedException e) {
            interrupt();
        }
    }

    public long now() {
        return System.currentTimeMillis();
    }

}
