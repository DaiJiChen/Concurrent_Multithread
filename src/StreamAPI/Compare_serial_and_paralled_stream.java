package StreamAPI;

import java.util.stream.IntStream;

public class Compare_serial_and_paralled_stream {
    public static void main(String[] args) {
        //time 1773ms
        long start = System.currentTimeMillis();
        long numPrime = IntStream.rangeClosed(2, Integer.MAX_VALUE/1000).filter(x -> isPrime(x)).count();
        long end = System.currentTimeMillis();
        System.out.println("Num primes: " + numPrime);
        System.out.println("Sequential time" + (end-start));

        //time 290ms
        start = System.currentTimeMillis();
        numPrime = IntStream.rangeClosed(2, Integer.MAX_VALUE/1000).parallel().filter(x -> isPrime(x)).count();
        end = System.currentTimeMillis();
        System.out.println("Num primes: " + numPrime);
        System.out.println("Parallel time" + (end-start));
    }


    public static boolean isPrime(long num) {
        if(num <= 1) return false;
        if(num == 2) return true;
        if(num%2 == 0) return false;

        // we can check the numbers in the range [N, surt(N)]
        long maxDivisior = (long)Math.sqrt(num);
        for(int i = 3; i < maxDivisior; i+=2)
            if(num%i == 0) return false;

        return true;
    }

}


