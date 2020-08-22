public class Reverse_Bits {
/*
Rating: Easy

Reverse bits of a given 32 bits unsigned integer.

Example 1:

Input: 00000010100101000001111010011100
Output: 00111001011110000010100101000000
Explanation: The input binary string 00000010100101000001111010011100 represents
the unsigned integer 43261596, so return 964176192 which its binary representation
is 00111001011110000010100101000000.

Example 2:

Input: 11111111111111111111111111111101
Output: 10111111111111111111111111111111
Explanation: The input binary string 11111111111111111111111111111101
represents the unsigned integer 4294967293, so return 3221225471 which
its binary representation is 10111111111111111111111111111111.

 

Note:

    Note that in some languages such as Java, there is no unsigned integer type.
    In this case, both input and output will be given as signed integer type and
    should not affect your implementation, as the internal binary representation
    of the integer is the same whether it is signed or unsigned.
    In Java, the compiler represents the signed integers using 2's complement notation.
    Therefore, in Example 2 above the input represents the signed integer -3 and the
    output represents the signed integer -1073741825. 

Follow up:

If this function is called many times, how would you optimize it?
*/

/*
    Approach: Similar to mergesort divide and conquer, split the number into two chunks
              of 16 and swap them. Then mask out each half in the 16, so 8 and swap those
              and so on. ....1111111100000000 & n >> 8 | 0000000011111111 & n << 8
*/

    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        /*
        int rev = 0;
        for (int i = 0; i < 32; ++i)
        {
            rev = rev << 1;
            rev = rev | (n & 1);
            n = n >>> 1;
        }
        return rev;
        */
        n = (n >>> 16) | (n << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8); // 1111111100000000...1111111100000000     .....000000001111111000000011111111
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4); // 11110000....11110000                    000011110000......00001111
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2); // 11001100.....1100                       00110011.....110011
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1); // 1010101010101010....10                  010101010101010101.....01
        return n;
    }
}
