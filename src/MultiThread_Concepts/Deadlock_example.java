package MultiThread_Concepts;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock_example {
    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    // run this code will leads to a deadlock.
    // solution: let two workers get locks in the same order: first get lock1, then lock2
    public static void main(String[] args) {
        Deadlock_example deadlock = new Deadlock_example();

        // after Java 8, it i spossible to create threads like this.
        // the run() method will execute worker1 and worker2
        new Thread(deadlock::worker1, "Worker1").start();
        new Thread(deadlock::worker2, "Worker2").start();
    }

    public void worker1() {
        lock1.lock();
        System.out.println("Worker1 acquired the lock1...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock2.lock();
        System.out.println("Worker1 acquired the lock2...");

        lock1.unlock();
        lock2.unlock();
    }

    public void worker2() {
        lock2.lock();
        System.out.println("Worker2 acquired the lock2...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock1.lock();
        System.out.println("Worker2 acquired the lock1...");

        lock1.unlock();
        lock2.unlock();
    }
}

