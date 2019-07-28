package arraysStringsCC;

// Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
//cannot use additional data structures? pp 203

/*If we can't use additional data structures, we can do the following:
        1. Compare every character of the string to every other character of the string. This will take 0(n2) time
        and 0(1) space.
        2. If we are allowed to modify the input string, we could sort the string in O(n log(n)) time and then
        linearly check the string for neighboring characters that are identical. Careful, though: many sorting
        algorithms take up extra space.
        These solutions are not as optimal in some respects, but might be better depending on the constraints of
        the problem.*/

public class stringUniqueCharacters {

    public static boolean isUniqueChars(String str) {
//        The time complexity for this code isO(n) -----------
        if (str.length() > 128) return false;

        boolean[] char_set= new boolean[128];
        for (int i= 0; i < str.length(); i++) {
            int val= str.charAt(i);
            if (char_set[val]) {//Already found this char in string
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    public static boolean isUniqueCharsBO(String str) {
//        We can reduce our space usage bya factor of eight by using a bit vector. We will assume, in the below code,
//                that the string only uses the lowercase letters a through z. This will allow us to use just a single int.
        int checker= 0;
        for (int i= 0; i < str.length(); i++) {
            int val= str.charAt(i) - '0';
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }

    public static void main(String[] args) {
//        String str = "abcdefghijklmnopqrstuvxyz1234567890";
        String str = "abcdefghijklmnopqrstuvxyz";
//        System.out.println("Is unique: " + isUniqueChars(str));
        System.out.println("Is unique: " + isUniqueCharsBO(str));

    }


}
