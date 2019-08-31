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
        double inNum = num;
        while (num > 0) {
            /* Setting a limit on length: 32 characters */
            if (binary.length() >= 32) {
                double diff = restoreDec(binary.toString());
                System.out.println("More then: > 32 bit, restored: "+ diff);
                return binary.toString();
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

    /*Alternatively, rather than multiplying the number by two and comparing it to 1, we can compare the
number to . 5, then .25, and so on. The code below demonstrates this approach.

     */

    public static String printBinary2(double num) {
        if (num >= 1 || num <= 0) {
            return "ERROR";
        }

        StringBuilder binary = new StringBuilder();
        double frac = 0.5;
        binary.append(".");
        while (num > 0) {
            /* Setting a limit on length: 32 characters */
            if (binary.length() > 32) {
//                return "ERROR";
                double diff = restoreDec(binary.toString());
                System.out.println("More then: > 32 bit, restored: "+ diff);
                return binary.toString();
            }
            if (num >= frac) {
                binary.append(1);
                num -= frac;
            } else {
                binary.append(0);
            }
            frac /= 2;
        }
        return binary.toString();
    }

    public static void main(String[] args) {
        //System.out.println("ooo: "+(0.5 + 1./8.));
//        String res = printBinary(0.635);
//        System.out.println(res);
//        double num = restoreDec(res);
//        System.out.println("restored dec: "+num);
        String res = printBinary2(0.635);
        System.out.println(res);

    }

    public static double restoreDec(String binary){
        double num = 0.;
        for (int i = 1; i < binary.length(); ++i){
            char c = binary.charAt(i);
            if (c == '1'){
                num += Math.pow(2,(-1*i));
            }
        }
        return num;
    }
}









