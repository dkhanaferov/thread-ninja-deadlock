package com.threadninja;

import java.lang.management.ThreadInfo;
import java.util.concurrent.TimeUnit;

/**
 * @author dkhanaferov
 * @since 3/27/18
 */
public class Driver {

    public static void main(String[] args) {

        DeadlockDetector deadlockDetector = new DeadlockDetector((deadlockedThreads)->{
            if (deadlockedThreads != null) {
                System.err.println("Deadlock detected!");

                for (ThreadInfo threadInfo : deadlockedThreads) {

                    if (threadInfo != null) {

                        for (Thread thread : Thread.getAllStackTraces().keySet()) {

                            if (thread.getId() == threadInfo.getThreadId()) {
                                System.err.println(threadInfo.toString().trim());

                                for (StackTraceElement ste : thread.getStackTrace()) {
                                    System.err.println("\t" + ste.toString().trim());
                                }
                            }
                        }
                    }
                }
            }

        }, 5, TimeUnit.SECONDS);
        deadlockDetector.start();

        ThreadNinja n1 = new ThreadNinja();
        ThreadNinja n2 = new ThreadNinja();

        Thread t1 = new Thread(() -> {
            while(true) {
                try {
                    n1.fight(n2);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while(true) {
                try {
                    n2.fight(n1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        t1.setName("T1");
        t2.setName("T2");

        t1.start();
        t2.start();
    }
}
