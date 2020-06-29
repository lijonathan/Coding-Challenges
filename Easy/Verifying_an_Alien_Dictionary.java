import java.util.HashMap;

public class Verifying_an_Alien_Dictionary {
/*
Rating: Easy

In an alien language, surprisingly they also use english
lowercase letters, but possibly in a different order.
The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language,
and the order of the alphabet, return true if and only if
the given words are sorted lexicographicaly in this alien language.


Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is
unsorted.

Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According
to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character
which is less than any other character (More info).



Constraints:

    1 <= words.length <= 100
    1 <= words[i].length <= 20
    order.length == 26
    All characters in words[i] and order are English lowercase letters.

*/

/*
    Approach: Check each word is greater in sequence to the one prior to it.
    Put all characters into HashMap for quick access to their order.
    For each check with its the previous string, check each character, if blank, set
    to minimum integer.
*/


    // O(kn) runtime, n = # words, k = length of longest word
    // O(26) space for HashMap
    // Runtime: 1 ms, faster than 46.57% of Java online submissions
    // Memory Usage: 39.3 MB, less than 40.11% of Java online submissions
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> c_order = new HashMap<Character, Integer>();
        for (int i = 0; i < order.length(); ++i)
        {
            c_order.put(order.charAt(i), i);
        }

        int max_length = 0;
        for (int k = 0; k < words.length; ++k)
        {
            if (words[k].length() > max_length)
            {
                max_length = words[k].length();
            }
        }
        for (int m = 1; m < words.length; ++m)
        {
            for (int j = 0; j < max_length; ++j)
            {
                int order_1;
                int order_2;
                if (j < words[m - 1].length())
                {
                    order_1 = c_order.get(words[m - 1].charAt(j));
                }
                else
                {
                    order_1 = Integer.MIN_VALUE;
                }
                if (j < words[m].length())
                {
                    order_2 = c_order.get(words[m].charAt(j));
                }
                else
                {
                    order_2 = Integer.MIN_VALUE;
                }
                if (order_2 < order_1)
                {
                    return false;
                }
                else if (order_1 < order_2)
                {
                    break;
                }
            }
        }
        return true;
    }
}
