package Concurrent_Collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedWorker implements Delayed {
    private long duration;
    private String name;

    DelayedWorker(long duration, String name) {
        this.duration = System.currentTimeMillis() + duration;
        this.name = name;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed otherWorker) {
        if(this.duration < ((DelayedWorker)otherWorker).getDuration())
            return -1;
        else if(this.duration < ((DelayedWorker)otherWorker).getDuration())
            return 1;
        return 0;
    }

    public long getDuration() {
        return duration;
    }

    public String toString() {
        return name;
    }
}

public class DelayQueue_Demo {
    public static void main(String[] args) {
        BlockingQueue<DelayedWorker> queue = new DelayQueue<>();

        queue.add(new DelayedWorker(1000, "First..."));
        queue.add(new DelayedWorker(10000, "Second..."));
        queue.add(new DelayedWorker(4000, "Third..."));

        while(!queue.isEmpty()) {
            try {
                DelayedWorker w = queue.take();
                System.out.println(w.toString() + w.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
