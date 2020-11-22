package Concurrent_Collections;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker implements Runnable {
    private int id;
    CountDownLatch latch;

    Worker(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Worker " + id + " is working");
        latch.countDown();
        System.out.println("Worker " + id + " finished");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class CountDownLatch_Demo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(5);

        for(int i = 0; i < 5; i++) {
            executor.execute( new Worker(i +1, latch) );
        }

        try {
            latch.await();
            System.out.println("All work has been done...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
