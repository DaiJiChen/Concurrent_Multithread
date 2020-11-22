package MultiThread_Concepts;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Livelock_example {
    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    // run this code will leads to a deadlock.
    // solution: let two workers get locks in the same order: first get lock1, then lock2
    public static void main(String[] args) {
        Livelock_example livelock = new Livelock_example();

        // after Java 8, it i spossible to create threads like this.
        // the run() method will execute worker1 and worker2
        new Thread(livelock::worker1, "Worker1").start();
        new Thread(livelock::worker2, "Worker2").start();
    }

    public void worker1() {
        while(true) {
            try {
                lock1.tryLock(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker1 acquired lock1");
            System.out.println("worker 1 tries to get lock2");

            if(lock2.tryLock()) {
                System.out.println("worker1 acquired lock2");
                lock1.unlock();
            }
            else {
                System.out.println("worker1 cannot acquired lock2");
                continue;
            }
            break;
        }

        lock1.unlock();
        lock2.unlock();
    }

    public void worker2() {
        while(true) {
            try {
                lock2.tryLock(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker2 acquired lock2");
            System.out.println("worker2 tries to get lock1");

            if(lock1.tryLock()) {
                System.out.println("worker2 acquired lock1");
                lock2.unlock();
            }
            else {
                System.out.println("worker2 cannot acquired lock1");
                continue;
            }
            break;
        }

        lock1.unlock();
        lock2.unlock();
    }
}

