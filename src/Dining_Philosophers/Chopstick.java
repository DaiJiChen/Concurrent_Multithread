package Dining_Philosophers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {

    private int id;
    private Lock lock;

    public Chopstick(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public boolean pickUp(Philosopher p, State s) {
        // This is where well wll simulate deadlock
        try {
            if(lock.tryLock(10, TimeUnit.MILLISECONDS)) {
                System.out.println(p + " picked up " + s.toString() + " " + this);
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void putDown(Philosopher p, State s) {
        lock.unlock();
        System.out.println(p + " puts down " + s.toString() + " " + this);
    }

    @Override
    public String toString() {
        return "Chopstick " + id;
    }
}
