package fork_join_invoke.fork_join_action;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursionAction extends RecursiveAction {
    private int count;

    public SimpleRecursionAction(int x) {count = x;}

    @Override
    protected void compute() {
        if(count > 100) {
            System.out.println("Split tast " + count);

            SimpleRecursionAction s1 = new SimpleRecursionAction(count/2);
            SimpleRecursionAction s2 = new SimpleRecursionAction(count/2);

            s1.fork();
            s2.fork();
        }
        else {
            System.out.println("No need for paralled " + count);
        }
    }
}
