package arraysStringsCC;


//.2 Check Permutation: Given two strings, write a method to decide if one is a permutation of the
//        other. pp 204

/*Like in many questions, we should confirm some details with our interviewer. We should understand if the
        permutation comparison is case sensitive. That is: is God a permutation of dog? Additionally, we should
        ask if whitespace is significant. We will assume for this problem that the comparison is case sensitive and
        whitespace is significant. So, "god " is different from "dog".
        Observe first that strings of different lengths cannot be permutations of each other. There are two easy
        ways to solve this problem, both of which use this optimization.*/

import java.util.Arrays;

public class CheckPermutation {

    /*Solution #1: Sort the strings.
    If two strings are permutations, then we know they have the same characters, but in different orders.Therefore, sorting the strings will put the characters from two permutations in the same order. We just need to
    compare the sorted versions of the strings.*/
    public static String sort(String s) {
        char[] content= s.toCharArray();
//        java.util.Arrays.sort(content);
        Arrays.sort(content);
        return new String(content);
    }

    public static boolean permutation(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        return sort(s).equals(sort(t));
    }
   /* Solution #2: Check if the two strings have identical character counts.
    We can also use the definition of a permutation-two words with the same character counts-to implement this
     algorithm. We simply iterate through this code, counting how many times each character appears.
            Then, afterwards, we compare the two arrays.*/

    public static boolean permutation2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] letters = new int[128]; // Assumption
        char[] s_array = s.toCharArray();
        for (char c : s_array) {
            letters[c]++;
        }
        for (int i= 0; i < t.length(); i++) {
            int c = (int) t.charAt(i);
            letters[c]--;
            if (letters[c] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "dogg";
        String t = "godd";

//        System.out.println("Permutated: " + permutation(s,t));
        System.out.println("Permutated: " + permutation2(s,t));

    }


}
