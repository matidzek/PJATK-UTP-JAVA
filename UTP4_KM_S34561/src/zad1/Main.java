package zad1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.*;
import static java.util.stream.Collectors.groupingBy;

public class Main {
  public static void main(String[] args) throws IOException {
    Files.lines(Path.of("unixdict.txt"))
            .collect(groupingBy(c -> {
              char[] charArray = c.toCharArray();
              Arrays.sort(charArray);
              return new String(charArray);
            }))
            .values().stream()
            .collect(groupingBy(List::size, TreeMap::new, Collectors.toList()))
            .lastEntry()
            .getValue()
            .forEach(list -> System.out.println(String.join(" ", list)));
  }

}
