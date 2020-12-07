package StreamAPI;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StreamwithNumber {
    public static void main(String[] args) {
        int[] nums = {1, 5, 3, -2, 9};

        //lambda expression
        // :: operator () is referenced to certain method
        Arrays.stream(nums).forEach(System.out::println);
        System.out.println("XXXXXXXXXXXXXX");

        int sum = Arrays.stream(nums).sum();
        System.out.println("SUM is: " + sum);
        System.out.println("XXXXXXXXXXXXXX");

        IntStream.range(0, 5).forEach(System.out::println);
        System.out.println("XXXXXXXXXXXXXX");
        IntStream.range(6, 12).forEach(x -> System.out.println(x));
        System.out.println("XXXXXXXXXXXXXX");

        IntStream.range(1, 10).filter(x -> (x%2 == 0)).forEach(x -> System.out.println(x));
        System.out.println("XXXXXXXXXXXXXX");
    }
}
