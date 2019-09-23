package BitManipulations;

/* 5.6 Conversion: Write a function to determine the number of bits you would need to flip to convert
integer A to integer B.
EXAMPLE
Input:  29 (or: 11101), 15 (or: 01111)
Output:  2

SOLUTION:
How you would figure out which bits in two numbers are different. Simple: with an XOR.
Each 1 in the XOR represents a bit that is different between A and B. Therefore, to check the number of bits
that are different between A and B, we simply need to count the number of bits in AAB that are 1.

       pp 297 */

public class Conversion {

    public static int bitSwapRequired(int a, int b) {
        int count = 0;
        for (int c = a ^ b; c != 0; c = c >> 1) {
            count += c & 1;
        }
        return count;
    }

    /* This code is good, but we can make it a bit better. Rather than simply shifting c repeatedly while checking
the least significant bit, we can continuously flip the least significant bit and count how long it takes c to
reach 0.The operation c = c & ( c - 1) will clear the least significant bit in c.
    */
    public static int bitSwapRequired2(int a, int b) {
        int count = 0;
        for (int c = a ^ b; c != 0; c = c & (c - 1)) {
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int c = 7;
        int count = 0;
        //System.out.println("c: "+Integer.toBinaryString(c));
        for (; c != 0; c = (c & (c-1))){
            count++;
            System.out.println("c: "+Integer.toBinaryString(c));
        }

        System.out.println("count: "+count);
        /*  Output:
            c: 111
            c: 110
            c: 100
            count: 3
         */
    }



}

