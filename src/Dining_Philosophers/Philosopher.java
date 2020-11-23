package Dining_Philosophers;

import org.omg.CORBA.INTERNAL;

import java.util.Random;

public class Philosopher implements Runnable{
    private int id;
    private volatile boolean full;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private Random random;
    private int eatingCounter;

    Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        random = new Random();
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    @Override
    public void run() {
        try {
            while(!full) {
                think();
                if(leftChopstick.pickUp(this, State.LEFT)) {
                    if (rightChopstick.pickUp(this, State.RIGHT)) {
                        eat();
                        rightChopstick.putDown(this, State.RIGHT);
                    }
                    leftChopstick.putDown(this, State.LEFT);
                }
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException {
        System.out.println(this + " is thinking...");
        Thread.sleep(random.nextInt(1000));
    }

    private void eat() throws InterruptedException {
        System.out.println(this + " is thinking...");
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    public void setFull(boolean full){
        this.full = full;
    }

    public int getEatingCounter() {
        return eatingCounter;
    }

    public boolean getFull() {return full;}


    @Override
    public String toString() {
        return "Philosopher " + id;
    }

}
