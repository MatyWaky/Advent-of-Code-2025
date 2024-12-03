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
        secondPart();
    }

    private static void firstPart() throws Exception {
        readAllRows();
        var sum = allRows.stream()
                .mapToInt(Main::checkRow)
                .sum();

        System.out.println("Part 1: " + sum);
    }

    private static void secondPart() throws Exception {
        readAllRows();
        var sum = 0;

        for (var row : allRows) {
            if (checkRow(row) == 0) {
                sum += checkRowAndDeleteWrongElement(row);
            } else {
                sum++;
            }
        }

        System.out.println("Part 2: " + sum);
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
        var isDecreasing = false;

        for (var i = 0; i < row.size() - 1; i++) {
            var diff = row.get(i) - row.get(i + 1);

            if ((Math.abs(diff) > 3 || diff == 0) ||
                    (i > 0 && (isDecreasing != (diff > 0)))) {
                return 0;
            }

            isDecreasing = diff > 0;
        }

        return 1;
    }

    private static int checkRowAndDeleteWrongElement(List<Integer> row) {
        for (var i = 0; i < row.size(); i++) {
            List<Integer> tempRow = new ArrayList<>(row);
            tempRow.remove(i);
            if (checkRow(tempRow) == 1) {
                return 1;
            }
        }
        return 0;
    }
}