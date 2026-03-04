import java.util.*;

public class Euphonious {

    // Use a Set for O(1) vowel lookup
    private static final Set<Character> VOWELS = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'y'));

    private static boolean isVowel(char c) {
        return VOWELS.contains(c);
    }

    /**
     * Counts the minimum number of letters to insert so that the word has no
     * three or more consecutive vowels or consonants.
     *
     * Single-pass algorithm: track how many consecutive letters of the same
     * type (vowel/consonant) have been seen. Each time that count reaches 3,
     * one insertion is needed and the counter resets to 1.
     */
    public static int countInsertions(String word) {
        if (word == null || word.isEmpty()) {
            return 0;
        }
        word = word.toLowerCase();

        int insertions = 0;
        int consecutive = 0;
        Boolean prevWasVowel = null;

        for (char c : word.toCharArray()) {
            // Skip non-letter characters
            if (!Character.isLetter(c)) {
                consecutive = 0;
                prevWasVowel = null;
                continue;
            }

            boolean currIsVowel = isVowel(c);

            if (prevWasVowel == null || currIsVowel != prevWasVowel) {
                // First letter or type changed – start a new streak
                consecutive = 1;
                prevWasVowel = currIsVowel;
            } else {
                // Same type as previous letter
                consecutive++;
                if (consecutive == 3) {
                    insertions++;
                    consecutive = 1;
                }
            }
        }

        return insertions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String wordEntered = scanner.nextLine();
        System.out.println(countInsertions(wordEntered));
    }
}
