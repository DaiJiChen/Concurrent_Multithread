package fork_join_invoke.fork_join_action;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        SimpleRecursionAction s = new SimpleRecursionAction(120);
        System.out.println(pool.invoke(s));
    }
}
