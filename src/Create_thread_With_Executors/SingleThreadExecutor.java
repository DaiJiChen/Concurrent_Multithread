package Create_thread_With_Executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

class Work implements Runnable {
    private int id;

    Work(int id) { this.id = id; }

    @Override
    public void run() {
        System.out.println("Work " + id + " is been doing, thread id is: " + Thread.currentThread().getId());
        long duration = (long) (Math.random()*5);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

public class SingleThreadExecutor {
    public static void main(String[] args) {
        //this is a single thread that will execute the task sequentially.
        // so one after another
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for(int i = 0; i < 5; i++) {
            Work work = new Work(i);
            executor.execute(work);
        }

        executor.shutdown();
    }
}
