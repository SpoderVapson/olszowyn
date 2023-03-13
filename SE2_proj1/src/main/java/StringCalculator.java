import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringCalculator {

    private String additional_separator;

    public int calculate(String str) {
        if (str.isBlank()) {
            return 0;
        }

        if (str.contains("-")) {
            throw new IllegalStateException("Cannot use negative numbers!");
        }

        if (!str.contains(",") && !str.contains("\n") && !str.contains("//")) {
            return Integer.valueOf(str);
        }

        if (str.startsWith("//")) {
            this.additional_separator = String.valueOf(str.charAt(2));
            str = str.substring(3, str.length());
        }

        ArrayList<String> numbers;
        if (additional_separator == null){
            numbers = new ArrayList<>(List.of(str.split("[,\n]")));
        }
        else {
            numbers = new ArrayList<>(List.of(str.split("[,\n" + additional_separator + "]")));
        }

        numbers.removeIf(string -> Integer.valueOf(string) > 1000);

        return addNumbers(numbers);
    }

    private int addNumbers(List<String> nums) {
        return nums.stream().map(Integer::valueOf).reduce(0, Integer::sum);
    }
}
