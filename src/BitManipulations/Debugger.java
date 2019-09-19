package BitManipulations;

/*5.5 Debugger: Explain what the following code does: ((n & (n-1)) == 0).
SOLUTION
We can work backwards to solve this question.
What does it mean if A & B ==== 0?
pg 716
It means that A and B never have a 1 bit in the same place. So if n & ( n-1) -- 0, then n and n-1 never
share a 1.
When you subtract 1 from a number, you lookat the least significant bit. If it's a 1 you change it to 0, and you
are done. If it's a zero, you must"borrow" from a larger bit. So, you go to increasingly larger bits, changing
each bit from a O to a 1, until you find a 1. You flip that 1 to a O and you are done.
1101011000 - 1 = 1101010111 [base 2]
So what does n & (n-1} === 0 indicate?
n and n-1 must have no ls in common. Given that they look like this:
if n = abcde1000
then n-1 = abcde0111
abcde must be all 0s, which means that n must look like this: 00001000.The value n is therefore a power
of two. So, we have our answer:
    ( ( n & ( n-1)) == 0) checks if n is a power of 2 (or if n is 0).
        pp 296 */

public class Debugger {
}
