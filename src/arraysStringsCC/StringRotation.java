package arraysStringsCC;

/*1.9 String Rotation: Assumeyou have a method i5Sub 5tring which checks if one word is a substring
        of another. Given two strings, 51 and 52, write code to check if 52 is a rotation of 51 using only one
        call to i5Sub5tring (e.g.,"waterbottle" is a rotation of"erbottlewat")

        So, we need to check if there's a way to split s1 into x andy such that xy = s1 andyx = s2.
        Regardless of where the division between x andy is, we can see that yx will always be a
        substring of xyxy.That is, s2 will always be a substring of s1s1.
        And this is precisely how we solve the problem: simply do isSubstring(slsl, s2)

pp 217
*/

public class StringRotation {

    boolean isRotation(String sl, String s2) {
        int len = sl.length();
        /* Check that sl and s2 are equal length and not empty*/
        if (len == s2.length() && len > 0) {
             /* Concatenate sl and sl within new buffer */
            String slsl = sl + sl;
            return isSubstring(slsl, s2);
        }
        return false;
    }

    public static boolean isSubstring(String slsl, String s2){
        // Someone mast implement the method checking is substring!
        return false;
    }


    public static void main(String[] args) {

    }
}
