package com.nickmafra.gfx;

@FunctionalInterface
public interface CheckedRunnable<E extends Exception> {

    void run() throws E;
}
