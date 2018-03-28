package com.threadninja;

import java.lang.management.ThreadInfo;

/**
 * @author dkhanaferov
 * @since 3/27/18
 */
@FunctionalInterface
public interface DeadlockHandler {
    void handleDeadlock(final ThreadInfo[] deadlockedThreads);
}