package Inter_Thread_Communication;

import java.util.ArrayList;
import java.util.List;

class Process {
    public void produce() throws InterruptedException {
        synchronized(this) {
            System.out.println("Producing");
            wait();
            System.out.println("Came back to producing");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);

        synchronized(this) {
            System.out.println("Consumed...");
            notify();
            System.out.println("After consume...");
        }
    }

}

public class Wait_and_Notify {
    public static void main(String[] args) {
        Process process = new Process();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }

}
