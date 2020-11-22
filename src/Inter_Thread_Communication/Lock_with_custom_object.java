package Inter_Thread_Communication;

public class Lock_with_custom_object {
    public static int count1 = 0;
    public static int count2 = 0;

    // create two custom objects, use their intrinsic locks ato synchronize count1 and count2
    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void increment1() {
        synchronized (lock1) {
            count1++;
        }
    }

    public static void increment2() {
        synchronized (lock2) {
            count2++;
        }
    }

    public static boolean process() {
        Thread t1 =  new Thread( new Runnable(){
            @Override
            public void run() {
                for(int i = 0; i < 100; i++)
                    increment1();
            }
        });

        Thread t2 =  new Thread( new Runnable(){
            @Override
            public void run() {
                for(int i = 0; i < 100; i++)
                    increment2();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter1 is:" + count1);
        System.out.println("Counter2 is:" + count2);

        return (count1 == 100 && count2 == 100);
    }

    public static void main(String[] args) {
        process();
    }
}
