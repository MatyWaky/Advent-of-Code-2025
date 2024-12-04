import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        firstPart();

        secondPart();
    }

    private static void firstPart() throws Exception {
        String text = readFile();
        String regex = "mul\\((-?\\d+),(-?\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        long sum = 0;

        while (matcher.find()) {
            long x = Integer.parseInt(matcher.group(1));
            long y = Integer.parseInt(matcher.group(2));
            sum += x * y;
        }

        System.out.println("Part 1: " + sum);
    }

    private static void secondPart() throws Exception {
        String text = readFile();
        String regex = "(do\\(\\))|(don't\\(\\))|mul\\((-?\\d+),(-?\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        boolean isEnabled = true;
        long sum = 0;

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                isEnabled = true;
            } else if (matcher.group(2) != null) {
                isEnabled = false;
            } else if (matcher.group(3) != null && matcher.group(4) != null) {
                if (isEnabled) {
                    long x = Integer.parseInt(matcher.group(3));
                    long y = Integer.parseInt(matcher.group(4));
                    sum += x * y;
                }
            }
        }
        System.out.println("Part 2: " + sum);
    }

    private static String readFile() throws Exception {
        Path filePath = Path.of("input.txt");
        return Files.readString(filePath);
    }
}