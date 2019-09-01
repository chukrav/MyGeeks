package BitManipulations;

/*5.2 Binary to String: Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double,
print the binary representation. If the number cannot be represented accurately in binary with at
most 32 characters, print"ERROR:

       pp 288  */

public class BinaryToString {

    public static String printBinary(double num) {
        if (num >= 1 || num <= 0) {
            return "ERROR: > 1";
        }

        StringBuilder binary = new StringBuilder();
        binary.append(".");
        while (num > 0) {
            /* Setting a limit on length: 32 characters */
            if (binary.length() >= 32) {
                return "ERROR: > 32 bit";
            }
            double r = num * 2;
            if (r >= 1) {
                binary.append(1);
                num = r - 1;
            } else {
                binary.append(0);
                num = r;
            }
        }
        return binary.toString();
    }

    public static void main(String[] args) {
        System.out.println("ooo: "+(0.5 + 1./8.));
        String res = printBinary(0.635);
        System.out.println(res);
        //System.out.println("Integer.BYTES: "+Integer.BYTES*8);
    }
}









