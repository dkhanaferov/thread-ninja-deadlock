package com.threadninja;

/**
 * @author dkhanaferov
 * @since 3/27/18
 */
public class ThreadNinja {

    private class Sword {
        public synchronized int getRank() {
            return 1;
        }
    }

    private int rank = 1;
    private Sword sword = new Sword();

    public int getRank() {
        return rank + sword.getRank();
    }

    public synchronized void fight(ThreadNinja opponent) {

        if(!opponent.canDefendAgainst(this))rank++;
    }

    public synchronized boolean canDefendAgainst(ThreadNinja opponent) {
        if(getRank() > opponent.getRank()) {
            rank++;
            return true;
        }
        return false;
    }
}
