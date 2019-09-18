package BitManipulations;

/*5.4 Next Number: Given a positive integer, print the next smallest and the next largest number that
have the same number of 1 bits in their binary representation.
pg 116
SOLUTION
There are a number of ways to approach this problem, including using brute force, using bit manipulation,
and using clever arithmetic. Note that the arithmetic approach builds on the bit manipulation approach.
You'll want to understand the bit manipulation approach before going on to the arithmetic one.
The Brute Force Approach
An easy approach is simply brute force: count the number of ls in n, and then increment (or decrement)
until you find a number with the same number of ls. Easy-but not terribly interesting. Can we do something
a bit more optimal? Yes!

With this change, we have increased the size of n. But, we also have one too many ones, and one too few
zeros. We'll need to shrink the size of our number as much as possible while keeping that in mind.
We can shrink the number by rearranging all the bits to the right of bit p such that the 0s are on the left and
the ls are on the right. As we do this, we want to replace one of the 1 s with a 0.
A relatively easy way of doing this is to count how many ones are to the right of p, clear all the bits from
0 until p, and then add back in cl-1 ones. Let cl be the number of ones to the right of p and c0 be the
number of zeros to the right of p.

        pp 291  */

public class NextNumber {

    public static void test() {
        int p, a, b, mask, n, c1, c0;

        p = 7;
        c1 = 5;
        c0 = 2;
        n = 13948;
        a = 1 << p;     // all zeros except for a 1 at position p.
        b = a - 1;      // all zeros, followed by p ones.
        mask = ~b;      // all ones, followed by p zeros.
        n = n & mask;   // clears rightmost p bits.
//        n &= ~((1 << p) - 1);

        //To insert cl - 1 ones on the right, we do the following:
        a = 1 << (c1 - 1); // 0s with a 1 at position c1 - 1
        b = a - 1; // 0s with ls at positions 0 through
        n = n | b;  // inserts ls at positions 0 through
        //Or, more concisely:    n |= (1 << (c1 - 1)) - 1;
        int c = 7;
        c >>= 1;
        System.out.println("BInt: " + Integer.toBinaryString(c));

    }

    public static void main(String[] args) {
        test();
    }

    int getNext(int n) {
        /* Compute c0 and cl */
        int c = n;
        int c0 = 0;
        int cl = 0;
        while (((c & 1) == 0) && (c != 0)) {
            c0++;
            c >>= 1;
        }

        while ((c & 1) == 1) {
            cl++;
            c >>= 1;
        }

        /* Error: if n == 11..1100...00, then there is no bigger number with the same
         * number of ls. */
        if (c0 + cl == 31 || c0 + cl == 0) {
            return -1;
        }

        int p = c0 + cl; // position of rightmost non-trailing zero

        n |= (1 << p); // Flip rightmost non-trailing zero
        n &= ~((1 << p) - 1); // Clear all bits to the right of p
        n |= (1 << (cl - 1)) - 1; // Insert (cl-1) ones on the right.

        return n;
    }


}
