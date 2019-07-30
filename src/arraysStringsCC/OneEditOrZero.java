package arraysStringsCC;

/*One Away: There are three types of edits that can be performed on strings: insert a character,
        remove a character, or replace a character. Given two strings, write a function to check if they are
        one edit (or zero edits) away.
        EXAMPLE
        pale, ple -> true
        pales, pale -> true
        pale, bale -> true
        pale, bae -> false
        pp 210 */

public class OneEditOrZero {

    public static boolean oneEditAway(String first, String second) {
        if (first.length()== second.length()) {
            return oneEditReplace(first, second);
        } else if (first.length()+ 1== second.length()) {
            return oneEditinsert(first, second);
        } else if (first.length() - 1== second.length()) {
            return oneEditinsert(second, first);
        }
        return false;
    }

    public static boolean oneEditReplace(String sl, String s2) {
        boolean foundDifference = false;
        for (int i= 0; i < sl.length(); i++) {
            if (sl.charAt(i) != s2.charAt(i)) {
                if (foundDifference) {
                    return false;
                }
                foundDifference = true;
            }
        }
        return true;
    }
    /* Check if you can insert a character into sl to make s2. */
    public static boolean oneEditinsert(String sl, String s2) {
        int indexl = 0;
        int index2 = 0;
        while (index2< s2.length() && indexl< sl.length()) {
            if (sl.charAt(indexl) != s2.charAt(index2)) {
                if (indexl !=index2) {
                    return false;
                }
                index2++;
            } else {
                indexl++;
                index2++;
            }
        }
        return true;
    }

/*    This algorithm (and almost any reasonable algorithm) takes O(n) time, where nis the length of the shorter
    string. */

    public static void main(String[] args) {
//        String s1 = "pale", s2 = "ple"; // -> true
//        String s1 = "pales" , s2 = "pale"; // -> true
        String s1 = "pale", s2 = "bale"; // -> true
//        String s1 = "pale", s2 = "bae"; // -> false
//        boolean isOneEdited = oneEditAway(s1, s2);
        boolean isOneEdited = oneEditAway2(s1, s2);
        System.out.println("isOneEdited: "+isOneEdited);

    }

/*    We might notice that the code for oneEditReplace is very similar to that for oneEditinsert. We can
    merge them into one method */

    public static boolean oneEditAway2(String first, String second) {
        /* Length checks. */
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }
        /* Get shorter and longer string.*/
        String sl = first.length()< second.length() ? first : second;  // shorter
        String s2 = first.length()< second.length() ? second : first;  // longer

        int indexl =0;
        int index2 =0;
        boolean foundDifference = false;
        while (index2< s2.length() && indexl< sl.length()) {
            if (sl.charAt(indexl) != s2.charAt(index2)) {
                /* Ensure that this is the first difference found.*/
                if (foundDifference) return false;
                foundDifference = true;
                if (sl.length() == s2.length()) {//On replace, move shorter pointer
                    indexl++;
                }
            } else {
                indexl++; // If matching, move shorter pointer
            }
            index2++; // Always move pointer for longer string
        }
        return true;
    }
}
