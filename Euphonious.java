import java.util.*;

public class Euphonious {

    public static int countNumberOfLettersToInsert(int checkSum, int threeConsecutiveLettersGroup) {

        if (checkSum == 3) {
            return 1;
        } else if (checkSum % 2 == 1 && threeConsecutiveLettersGroup > 0) {
            return (checkSum - 1) / 2;
        } else if (checkSum % 2 == 0 && threeConsecutiveLettersGroup > 0) {
            return (checkSum - 2) / 2;
        } else {
            return 0;
        }
    }

    public static boolean isConsonantPresentInTheStringOfVowels(char wordToArray) {
        boolean isTrue = false;
        char[] consonants = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm',
                'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'};

        for (char consonant : consonants)
            if (wordToArray == consonant) {
                isTrue = true;
                break;
            }
        return isTrue;
    }

    public static boolean isVowelPresentInTheStringOfConsonants(char wordToArray) {
        boolean isTrue = false;
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};

        for (char vowel : vowels)
            if (wordToArray == vowel) {
                isTrue = true;
                break;
            }
        return isTrue;
    }

    public static int sumNumberOfLettersToInsert(List<Integer> numberOfCharsToInsert) {
        int sum = 0;
        for (int number : numberOfCharsToInsert) {
            sum = sum + number;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String wordEntered = scanner.nextLine();

        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};
        char[] consonants = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v',
                'w', 'x', 'z'};
        char[] charsIntoArray = wordEntered.toCharArray();
        int checkSumForVowels = 0;
        int checkSumForConsonants = 0;
        int threeConsecutiveVowelsGroup = 0;
        int threeConsecutiveConsonantsGroup = 0;
        int consToInsertInBetween;
        int vowelsToInsertInBetween;
        ArrayList<Integer> numberOfCharsToInsert = new ArrayList<>();
        numberOfCharsToInsert.add(0);
        var counter = 0;

        if (charsIntoArray.length < 3) {
            System.out.println('0');
        } else { // ---- checking VOWELS ----
            for (char c : charsIntoArray) {
                for (char vowel : vowels) {
                    if (c == vowel) {
                        checkSumForVowels += 1;
                        if (checkSumForVowels % 3 == 0) {
                            threeConsecutiveVowelsGroup += 1;
                        }
                        consToInsertInBetween = countNumberOfLettersToInsert(checkSumForVowels,
                                threeConsecutiveVowelsGroup);
                        numberOfCharsToInsert.set(counter, consToInsertInBetween);

                    } else if (isConsonantPresentInTheStringOfVowels(c)) {
                        checkSumForVowels = 0;
                        threeConsecutiveVowelsGroup = 0;
                        counter++;
                        numberOfCharsToInsert.add(counter, 0);
                        break;
                    }
                }
            }
// ---- checking CONSONANTS ----
            for (char c : charsIntoArray) {
                for (char consonant : consonants) {
                    if (c == consonant) {
                        checkSumForConsonants += 1;
                        if (checkSumForConsonants % 3 == 0) {
                            threeConsecutiveConsonantsGroup += 1;
                        }
                        vowelsToInsertInBetween = countNumberOfLettersToInsert(checkSumForConsonants,
                                threeConsecutiveConsonantsGroup);
                        numberOfCharsToInsert.set(counter, vowelsToInsertInBetween);
                    } else if (isVowelPresentInTheStringOfConsonants(c)) {
                        checkSumForConsonants = 0;
                        threeConsecutiveConsonantsGroup = 0;
                        counter++;
                        numberOfCharsToInsert.add(counter, 0);
                        break;
                    }
                }
            }
        }
        System.out.println(sumNumberOfLettersToInsert(numberOfCharsToInsert));
        numberOfCharsToInsert.clear();
    }
}
