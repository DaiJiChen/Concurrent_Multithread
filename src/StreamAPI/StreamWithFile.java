package StreamAPI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamWithFile {
    public static void main(String[] args) throws IOException {
        String path= "C:\\Users\\daiji\\OneDrive\\Desktop\\Concurrent_Mutlithread\\src\\StreamAPI\\file.txt";

        Stream<String> namesStream = Files.lines(Paths.get(path));

        List<String> names = namesStream.filter(x -> x.startsWith("K")).collect(Collectors.toList());

        names.forEach(System.out::println);
    }
}
