package Concurrent_Collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class Person implements Comparable<Person> {
    int age;
    String name;

    Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        return (this.name).compareTo(o.name);
    }

    public String toString() {
        return name + "-" + age;
    }
}

class Productor implements Runnable {
    private BlockingQueue<Person> queue;

    Productor(BlockingQueue<Person> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put(new Person(10, "Smith"));
            queue.put(new Person(55, "Jack"));
            queue.put(new Person(12, "Bob"));
            queue.put(new Person(34, "Joe"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {

    private BlockingQueue<Person> queue;

    Consumer(BlockingQueue<Person> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



public class PriorityBlockingQueue_Demo {
    public static void main(String[] args) {
        BlockingQueue<Person> queue = new PriorityBlockingQueue<Person>();

        new Thread(new Productor(queue)).start();
        new Thread(new Consumer(queue)).start();
    }
}
