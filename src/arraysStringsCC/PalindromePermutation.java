package arraysStringsCC;

/*1.4 Palindrome Permutation: Given a string, write a function to check if it is a permutation of
        a palindrome. A palindrome is a word or phrase that is the same forwards and backwards. A
        permutation is a rearrangement of letters. The palindrome does not need to be limited to just
        dictionary words.
        EXAMPLE
        Input: Tact Coa
        Output: True (permutations: "taco cat'; "atco eta·; etc.)
        pp 207 */

public class PalindromePermutation {

    public static boolean isPermutationOfPalindrome(String phrase) {
        int[] table = buildCharFrequencyTable(phrase);
//        printTable(table);
        return checkMaxOneOdd(table);

    }

    /* Check that no more than one character has an odd count. */
    public static boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd= false;
        for (int count : table) {
            if (count% 2 == 1) {
                if (foundOdd) {
                    return false;
                }
                foundOdd= true;
            }
        }
        return true;
    }

    /* Map each character to a number. a -> 0, b -> 1, c -> 2, etc.
* This is case insensitive. Non-letter characters map to -1. */
    public static int getCharNumber(Character c) {
        int a= Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val= Character.getNumericValue(c);
        if (a<= val && val<= z) {
            return val - a;
        }
        return -1;
    }

    /* Count how many times each character appears. */
    public static int[] buildCharFrequencyTable(String phrase) {
        int[] table = new int[Character.getNumericValue('z') -
        Character.getNumericValue('a') + 1];
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if(x != -1) {
                table[x]++;
            }
        }
        return table;
    }
    //Input: Tact Coa
    //Output: True
    public static void main(String[] args) {
        String str = "Tact Coa";
        boolean answ = isPermutationOfPalindrome(str);
        System.out.println("answ: "+answ);

    }

    public static void printTable(int [] tab){
        System.out.println("Table len:"+tab.length);
//        for (int i:tab) {
//            if (i != 0)
//                System.out.println(""+i);
//        }
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != 0)
                System.out.println(""+i+", cnt: "+ tab[i]);
        }
    }


}
