package Inter_Thread_Communication;

import java.util.ArrayList;
import java.util.List;

class Processor {
    private List<Integer> list = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;
    private final Object lock = new Object();
    private int value = 0;

    public void producer() throws InterruptedException {
        synchronized(lock) {
            while(true) {
                if(list.size() == UPPER_LIMIT) {
                    System.out.println("Waiting for the consumer to consume...");
                    Thread.sleep(500);
                    lock.wait();
                }
                else {
                    System.out.println("Adding: " + value);
                    list.add(value++);
                    lock.notify();
                }
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized(lock) {
            while(true) {
                if(list.size() == LOWER_LIMIT) {
                    System.out.println("Waiting for the producer to produce...");
                    Thread.sleep(500);
                    value = 0;
                    lock.wait();
                }
                else {
                    System.out.println("consuming: " + list.remove(list.size() - 1));
                    lock.notify();
                }
            }
        }
    }

}

public class Producer_and_Consumer {
    public static void main(String[] args) {
        Processor process = new Processor();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }

}

