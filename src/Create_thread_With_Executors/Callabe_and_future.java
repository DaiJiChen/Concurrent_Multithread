package Create_thread_With_Executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// instead of implement Runnable, we implement Callable
class task implements Callable<String> {
    private int id;

    task(int id) {
        this.id = id;
    }


    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "ID: " + id;
    }

}

public class Callabe_and_future {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<String>> list = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            Future<String> future = executor.submit(new task(i));
            list.add(future);
        }

        for(Future<String> future : list) {
            String id = null;
            try {
                id = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(id);
        }

        executor.shutdown();

    }
}
