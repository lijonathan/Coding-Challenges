import java.util.HashSet;

class Jewels_and_Stones {
/*

Rating: Easy

You're given strings J representing the types of stones
that are jewels, and S representing the stones you have.
 Each character in S is a type of stone you have.  You want to
know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J
and S are letters. Letters are case sensitive, so "a" is considered
a different type of stone from "A".

Example 1:

Input: J = "aA", S = "aAAbbbb"
Output: 3

Example 2:

Input: J = "z", S = "ZZ"
Output: 0

Note:

    S and J will consist of letters and have length at most 50.
    The characters in J are distinct.
 */
    
    public int numJewelsInStones(String J, String S) {
        // O(n) space, O(n) time where n is the length of J + S
        HashSet<Character> jewels = new HashSet<Character>();
        int total_jewels = 0;
        int J_size = J.length();
        for(int i = 0; i < J_size; ++i)
        {
            jewels.add(J.charAt(i));
        }
        int S_size = S.length();
        for (int k = 0; k < S_size; ++k)
        {
            if (jewels.contains(S.charAt(k)))
            {
                total_jewels += 1;
            }
        }
        return total_jewels;
    }
}
