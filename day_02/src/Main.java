import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static List<List<Integer>> allRows = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        firstPart();
    }

    private static void firstPart() throws Exception {
        readAllRows();
        var sum = allRows.stream()
                .mapToInt(Main::checkRow)
                .sum();

        System.out.println("sum: " + sum);
    }

    private static void readAllRows() throws Exception {
        Path filePath = Path.of("input.txt");

        List<List<Integer>> rows = new ArrayList<>();

        Files.lines(filePath)
                .map(line -> line.trim().split("\\s+"))
                .map(array -> Stream.of(array)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .forEach(rows::add);

        allRows = rows;
    }

    private static int checkRow(List<Integer> row) {
        var isIncreasing = false;

        for (var i = 0; i < row.size() - 1; i++) {
            var diff = row.get(i) - row.get(i + 1);

            if ((Math.abs(diff) > 3 || diff == 0) ||
                    (i > 0 && (isIncreasing != (diff > 0)))) {
                return 0;
            }

            isIncreasing = diff > 0;
        }

        return 1;
    }
}