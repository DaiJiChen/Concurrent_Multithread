package Concurrent_Collections;

import java.io.FileReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class firstWorker implements Runnable{
     private BlockingQueue<Integer> blockingQueue;

     firstWorker(BlockingQueue<Integer> blockingQueue) {
         this.blockingQueue = blockingQueue;
     }

     @Override
     public void run() {
         int counter = 0;
         while(true) {
             try {
                 blockingQueue.put(counter++);
                 System.out.println("put " + counter);
                 Thread.sleep(10);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     }
 }

class secondWorker implements Runnable{
    private BlockingQueue<Integer> blockingQueue;

    secondWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                int number = blockingQueue.take();
                System.out.println("take " + number);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class BlockingQueue_Demo {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

        firstWorker w1 = new firstWorker(blockingQueue);
        secondWorker w2 = new secondWorker(blockingQueue);

        new Thread(w1).start();
        new Thread(w2).start();
    }
}