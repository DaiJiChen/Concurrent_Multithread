package Create_thread_With_Executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class FixedThreadPools {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for(int i = 0; i < 10; i++) {
            Work work = new Work(i);
            executor.execute(work);
        }

        executor.shutdown();

        try {
            if(!executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

    }
}
