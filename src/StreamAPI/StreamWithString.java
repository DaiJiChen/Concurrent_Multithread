package StreamAPI;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Comparator;
import java.util.stream.Stream;

public class StreamWithString {
    public static void main(String[] args) {
        String[] names = {"B", "A", "C"};

        Stream.of(names).sorted().forEach(System.out::println);
        System.out.println("XXXXX");
        Stream.of(names).sorted(Comparator.reverseOrder()).forEach(System.out::println);
        System.out.println("XXXXX");

        Stream.of(names).filter(x -> x.startsWith("B")).forEach(System.out::println);
    }
}
