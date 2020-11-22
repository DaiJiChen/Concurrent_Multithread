package Create_thread_With_Executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class StockUpdator implements Runnable{

    @Override
    public void run() {
        System.out.println("Updating stock data from web...");
    }
}

public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new StockUpdator(), 5000, 1000, TimeUnit.MILLISECONDS);
    }
}
