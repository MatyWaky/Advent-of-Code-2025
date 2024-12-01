import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws Exception {
        Path filePath = Path.of("input.txt");

        List<Integer> firstColumn = new ArrayList<>();
        List<Integer> secondColumn = new ArrayList<>();

        var lines = Files.readAllLines(filePath);
        lines.stream()
                .map(line -> line.trim().split("\\s+"))
                .forEach(numbers -> {
                    firstColumn.add(Integer.parseInt(numbers[0]));
                    secondColumn.add(Integer.parseInt(numbers[1]));
                });


        var similarityScore = firstColumn.stream()
                .mapToInt(integer -> integer * (int) secondColumn.stream()
                        .filter(value -> value.equals(integer))
                        .count())
                .sum();

        System.out.println("Similarity score: " + similarityScore);


        firstColumn.sort(Integer::compareTo);
        secondColumn.sort(Integer::compareTo);

        var totalDistance = IntStream.range(0, firstColumn.size())
                .map(i -> Math.abs(firstColumn.get(i) - secondColumn.get(i)))
                .sum();

        System.out.println("Total distance: " + totalDistance);
    }
}