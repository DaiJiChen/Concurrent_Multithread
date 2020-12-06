package fork_join_invoke.fork_join_task;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        SimpleRecursiveTask task = new SimpleRecursiveTask(120);
        System.out.println(pool.invoke(task));
    }

}
