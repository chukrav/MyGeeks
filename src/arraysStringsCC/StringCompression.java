package arraysStringsCC;

/*1.6 String Compression: Implement a method to perform basic string compression using the counts
        of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the
        "compressed" string would not become smaller than the original string, your method should return
        the original string.You can assume the string has only uppercase and lowercase letters (a - z).
        pp 212
*/

public class StringCompression {

    //How hard could it be?
    public static String compressBad(String str) {
        String compressedString = "";
        int countConsecutive = 0;
        for (int i= 0; i < str.length(); i++) {
            countConsecutive++;  // At start became 1 !!!
/* If next character is different than current, append this char to result.*/
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressedString += "" + str.charAt(i) + countConsecutive;
                countConsecutive = 0;
            }
        }
        return compressedString.length() < str.length() ? compressedString : str;
    }
/*    This works. ls it efficient, though?Take a look at the runtime of this code.
    The runtime is O(p + k2), where p is the size of the original string and k is thelnumber of character
    sequences. For example, if the string is aabccdeeaa, then there are six characte sequences. It's slow
    because string concatenation operates in O(n2) time    */

    public static void main(String[] args) {
//         string aabcccccaaa would become a2blc5a3
        String str = "aabcccccaaa";
//        System.out.println("out: " + compressBad(str));
//        System.out.println("out: " + compress(str));
        System.out.println("out: " + compress2(str));

    }

    //    ----------------------- Good one ----------------------------------------
    public static String compress(String str) {
        StringBuilder compressed= new StringBuilder();
        int countConsecutive= 0;
        for (int i= 0; i < str.length(); i++) {
            countConsecutive++;

            /* If next character is different than current, append this char to result.*/
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive= 0;
            }
        }
        return compressed.length() < str.length() ? compressed.toString() : str;
    }

    //    ===============  Best One  =======================
    public static String compress2(String str) {
     /* Check final length and return input string if it would be longer. */
        int finallength= countCompression(str);
        if (finallength >= str.length()) return str;

        StringBuilder compressed= new StringBuilder(finallength); // initial capacity
        int countConsecutive = 0;
        for (int i= 0; i < str.length(); i++) {
            countConsecutive++;

         /* If next character is different than current, append this char to result.*/
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive= 0;
            }
        }
        return compressed.toString();
    }

    public static int countCompression(String str) {
        int compressedLength= 0;
        int countConsecutive= 0;
        for (int i= 0; i < str.length(); i++) {
            countConsecutive++;

             /* If next character is different than current, increase the length.*/
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressedLength  += 1 + String.valueOf(countConsecutive).length();
                countConsecutive = 0;
            }
        }
        return compressedLength;
    }


}
