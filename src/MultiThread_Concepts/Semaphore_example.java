package MultiThread_Concepts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader{
    INSTANCE;
    private Semaphore semaphore = new Semaphore(3, true);

    public void downloadData() {
        try {
            semaphore.acquire();
            download();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private void download() throws InterruptedException {
        System.out.println("Downloading data from internet...");
        Thread.sleep(1000);
    }
}


public class Semaphore_example {
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();

        for(int i = 0; i < 20; i++) {
            es.execute( new Runnable() {
                @Override
                public void run() {
                    System.out.println("A new thread is created");
                    Downloader.INSTANCE.downloadData();
                }
            });
        }
        es.shutdown();
    }
}
