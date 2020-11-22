package Concurrent_Collections;

import java.util.concurrent.Exchanger;

class t1 implements Runnable {
    private int counter;
    Exchanger<Integer> exchanger;

    t1(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while(true) {
            counter += 1;
            System.out.println("t1 incremented the counter: "+counter);
            try {
                counter = exchanger.exchange(counter);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class t2 implements Runnable {
    private int counter;
    Exchanger<Integer> exchanger;

    t2(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while(true) {
            counter -= 1;
            System.out.println("t2 decremented the counter: " + counter);
            try {
                counter = exchanger.exchange(counter);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Exchanger_Demo {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread( new t1(exchanger) ).start();
        new Thread( new t2(exchanger) ).start();

    }
}
