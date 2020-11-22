package MultiThread_Concepts;

import java.util.Arrays;
import java.util.List;

class Worker implements Runnable {
    private static volatile boolean terminated = false;
    @Override
    public void run() {
        while(!terminated) {
            System.out.println("Worker is working...");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }
}
public class Volatile_keyword {
    public static void main(String[] args) throws InterruptedException {

        Worker worker1 = new Worker();
        Thread t1 = new Thread(worker1);

        Worker worker2 = new Worker();
        Thread t2 = new Thread(worker2);
        
        t1.start();
        t2.start();

        Thread.sleep(1000);
        worker1.setTerminated(true);
        System.out.println("Worker is terminated");
    }
}
