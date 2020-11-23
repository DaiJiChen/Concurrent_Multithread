package Student_Library_Simulation;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {
    private int id;
    private Lock lock;

    Book(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public void read(Student student) throws InterruptedException {
        //lock.lock();
        if(lock.tryLock(1, TimeUnit.MINUTES)) {
            System.out.println(student + " starts reading " + this);
            Thread.sleep(1000);
            lock.unlock();
            System.out.println(student + " has finished reading " + this);
        }
    }

    @Override
    public String toString() {
        return "Book " + id;
    }
}
