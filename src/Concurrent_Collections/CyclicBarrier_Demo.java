package Concurrent_Collections;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javafx.scene.effect.Light;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class cbWorker implements Runnable {
    private int id;
    private CyclicBarrier cyclicBarrier;

    cbWorker(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("Worker " + id + " is working");
        try {
            Thread.sleep(1000);
            System.out.println("Worker " + id + " finihsed stage 1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("Worker " + id + " is working");
        try {
            Thread.sleep(1000);
            System.out.println("Worker " + id + " finihsed stage 2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

public class CyclicBarrier_Demo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("ALL task are finished...\n");
            }
        });

        for(int i = 0; i < 5; i++) {
            executor.execute( new cbWorker(i +1, cyclicBarrier) );
        }

        executor.shutdown();
    }
}
