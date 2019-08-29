package BitManipulations;

/*5.1 Insertion: You are given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method
to insert M into N such that M starts at bit j and ends at bit i. You can assume that the bits j through
i have enough space to fit all of M. That is, if M = 10011, you can assume that there are at least 5
bits between j and i. You would not, for example, have j = 3and i= 2, because M could not fully
fit between bit 3 and bit 2.
EXAMPLE
Input: N
Output: N
SOLUTION
10000000000, M
10001001100
        pp 287
 */

public class Insertion {

    public static int updateBits(int n, int m, int i, int j) {
        /* Create a mask to clear bits i through j in n. EXAMPLE: i = 2, j = 4. Result
         * should be 11100011. For simplicity, we'll use just 8 bits for the example. */

        int allOnes = -1; // will equal sequence of all ls Corrected,was: int allOnes = -0
        System.out.println("n: "+Integer.toBinaryString(n)+
                ", m: "+Integer.toBinaryString(m) +
                ", allOnes: " + Integer.toBinaryString(allOnes));

        // ls before position j, then 0s. left = 11100000
        int left = allOnes << (j + 1);
        System.out.println("left << : "+Integer.toBinaryString(left));

        // l's after position i. right = 00000011
        int right = ((1 << i) - 1);
        System.out.println("right << : "+Integer.toBinaryString(right));

        // All ls, except for 0s between i and j. mask 11100011
        int mask = left | right;
        System.out.println("mask: " + Integer.toBinaryString(mask));
        /* Clear bits j through i then put min there */
        int n_cleared = n & mask; // Clear bits j through i.
        int m_shifted = m << i; // Move m into correct position.
        return n_cleared | m_shifted; // OR them, and we're done!
    }


    public static void main(String[] args) {
        int M = Integer.parseInt("10011", 2);
        int N = Integer.parseInt("10000000000", 2);
        int res = updateBits(N, M,2,4);
        System.out.println("result: "+Integer.toBinaryString(res));
//        playground();
    }

    public static void playground(){
        int allOnes = -1, i = 2,  j = 4;
        System.out.println("Binary: "+Integer.toBinaryString(allOnes));
        int left = allOnes << (j + 1);
        System.out.println("Binary << : "+Integer.toBinaryString(left));
        int right = ((1 << i) - 1);
//        int right = (1 << i);
        System.out.println("Right << : "+Integer.toBinaryString(right));
        int mask = left | right;
        System.out.println("Mask << : "+Integer.toBinaryString(mask));
        int m = Integer.parseInt("101",2);
        System.out.println("M << : " + m);
        int M = Integer.parseInt("10011", 2);
        M = M << 2;
        int N = Integer.parseInt("10000000000", 2);
        System.out.println("result: "+Integer.toBinaryString(N | M ));
    }

}
