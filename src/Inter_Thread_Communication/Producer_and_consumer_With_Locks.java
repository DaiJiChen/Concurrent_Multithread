package Inter_Thread_Communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {
    private static Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void producer() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("Producer method...");
            condition.await();
            System.out.println("Producer again...");
        } finally {
            lock.unlock();
        }
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("Consumer method...");
            condition.signal();
            System.out.println("Consumer after await()...");
        } finally {
            lock.unlock();
        }
    }
}


public class Producer_and_consumer_With_Locks {
    public static void main(String[] args) throws InterruptedException {

        Worker worker = new Worker();

        Thread t1 = new Thread( new Runnable() {
            public void run() {
                try {
                    worker.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread( new Runnable() {
            public void run() {
                try {
                    worker.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}

