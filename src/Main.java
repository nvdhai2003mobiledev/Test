import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static class VietnameseResult {
        int count;
        List<String> characters;

        public VietnameseResult(int count, List<String> characters) {
            this.count = count;
            this.characters = characters;
        }
    }
    public static void main(String[] args) {
        runTests();

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter a string of Latin characters:");
        String input = scanner.nextLine();

        input = input.replaceAll("[^a-zA-Z]", "");

        VietnameseResult result = countVietnameseCharacters(input);
        System.out.println("Output: " + result.count + " (" + String.join(", ", result.characters) + ")");

        scanner.close();
    }

    public static void runTests() {
        testCase("hwfdawhwhcoomddfgwdc", 6, "Example from problem statement");

        testCase("w", 1, "Single w character");
        testCase("aw", 1, "Single aw combination");
        testCase("aa", 1, "Single aa combination");
        testCase("dd", 1, "Single dd combination");
        testCase("ee", 1, "Single ee combination");
        testCase("oo", 1, "Single oo combination");
        testCase("ow", 1, "Single ow combination");

        testCase("AW", 1, "Uppercase AW");
        testCase("Aw", 1, "Mixed case Aw");

        testCase("aaw", 2, "Overlapping aa+w");
        testCase("waw", 2, "Overlapping w+aw");

        testCase("awaaowddweeoo", 6, "Multiple combinations");
        testCase("abcdefghijklmnopqrstuvxyz", 0, "No Vietnamese characters");
    }


    public static void testCase(String input, int expectedOutput, String description) {
        VietnameseResult result = countVietnameseCharacters(input);
        int actualOutput = result.count;

        System.out.println("\nTest: " + description);
        System.out.println("Input: " + input);
        System.out.println("Expected output: " + expectedOutput);
        System.out.println("Actual output: " + actualOutput + " (" + String.join(", ", result.characters) + ")");

        if (actualOutput == expectedOutput) {
            System.out.println("Test PASSED!");
        } else {
            System.out.println("Test FAILED!");
        }
    }


    public static VietnameseResult countVietnameseCharacters(String input) {
        input = input.toLowerCase();

        List<String> vietnameseChars = new ArrayList<>();
        int i = 0;

        while (i < input.length()) {
            if (i + 1 < input.length()) {
                String twoChars = input.substring(i, i + 2);

                if (twoChars.equals("aw") || // ă
                        twoChars.equals("aa") || // â
                        twoChars.equals("dd") || // đ
                        twoChars.equals("ee") || // ê
                        twoChars.equals("oo") || // ô
                        twoChars.equals("ow")) { // ơ
                    vietnameseChars.add(twoChars);
                    i += 2;
                    continue;
                }
            }

            if (input.charAt(i) == 'w') {
                vietnameseChars.add("w");
            }

            i++;
        }

        return new VietnameseResult(vietnameseChars.size(), vietnameseChars);
    }
}
