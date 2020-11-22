package Concurrent_Collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class produce implements Runnable {
    ConcurrentMap<String, Integer> map;

    produce(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        map.put("A", 5);
        map.put("p", 7);
        map.put("l", 4);
        map.put("e", 2);
    }
}

class consume implements Runnable {
    ConcurrentMap<String, Integer> map;

    consume(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        System.out.println(map.get("A"));
        System.out.println(map.get("e"));
        System.out.println(map.get("l"));
    }
}

public class Concurrent_HashMap_Demo {
    public static void main(String[] args) {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

        new Thread( new produce(map)).start();
        new Thread( new consume(map)).start();
    }
}
