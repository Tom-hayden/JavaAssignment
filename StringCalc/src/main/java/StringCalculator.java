import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private static final int MAX_ALLOWED_NUMBER = 1000;

    public static int add(String numbers) {
        int sum = 0;
        List<String> delimiters = new ArrayList<>(Arrays.asList(",","\n"));
        List<Integer> negativeNumbers = new ArrayList<>();

        int dataStartIndex = getDataStartIndex(numbers);

        if (dataStartIndex != 0){
            addCustomDelimiters(numbers.substring(0, dataStartIndex - 1), delimiters);
        }

        String[] splitNumbers = numbers.substring(dataStartIndex).split(generateRegex(delimiters));

        for (String num : splitNumbers) {
            if (num.isEmpty()) {
                continue;
            }
            try {
                int intNum = Integer.parseInt(num);
                if (intNum < 0){
                    negativeNumbers.add(intNum);
                } else if (intNum <= MAX_ALLOWED_NUMBER) {
                    sum += intNum;
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number format: " + num, e);
            }
        }

        if (!negativeNumbers.isEmpty()) {
            throw new RuntimeException("Negatives not allowed: " + negativeNumbers);
        }
        return sum;
    }

    private static int getDataStartIndex(String numbers) {
        if (!numbers.startsWith("//")){
            return 0;
        } else {
            return numbers.indexOf("\n") + 1;
        }
    }

    private static void addCustomDelimiters(String customDelimiters, List<String> delimiters) {
        //Regex matching blah in "[blah]" where blah does not contain ']'
        Pattern pattern = Pattern.compile("\\[([^\\]]+)]");
        Matcher matcher = pattern.matcher(customDelimiters);

        while (matcher.find()) {
            String match = matcher.group(1);
            delimiters.add(match);
        }
    }

    private static String generateRegex(List<String> delimiters) {
        String[] escapedDelimiters = delimiters
                .stream()
                .map(Pattern::quote)
                .toArray(String[]::new);

        return String.join("|", escapedDelimiters);
    }
}
