package fork_join_invoke.fork_join_task;

import java.util.concurrent.RecursiveTask;

public class SimpleRecursiveTask extends RecursiveTask<Integer> {
    private int count;

    public SimpleRecursiveTask(int x) {count = x;}

    @Override
    protected Integer compute() {
        if(count > 100) {
            System.out.println("Prallel needed " + count);

            SimpleRecursiveTask t1 = new SimpleRecursiveTask(count/2);
            SimpleRecursiveTask t2 = new SimpleRecursiveTask(count/2);

            t1.fork();
            t2.fork();

            int solution = 0;
            solution += t1.join();
            solution += t2.join();

            return solution;
        }
        else {
            System.out.println("No paralled needed... " + count);
            return 2*count;
        }
    }
}
