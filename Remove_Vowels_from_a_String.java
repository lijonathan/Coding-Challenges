import java.lang.StringBuffer;

public class Remove_Vowels_from_a_String {

/*
Rating: Easy

Given a string S, remove the vowels
'a', 'e', 'i', 'o', and 'u' from it,
and return the new string.


Example 1:

Input: "leetcodeisacommunityforcoders"
Output: "ltcdscmmntyfrcdrs"

Example 2:

Input: "aeiou"
Output: "" 

Note:

    S consists of lowercase English letters only.
    1 <= S.length <= 1000

*/

    public String removeVowels(String S) {
    	// O(n) space, O(n) time
    	// n = # of characters in S
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < S.length(); ++i)
        {
            if(S.charAt(i) != 'a' && S.charAt(i) != 'e' &&
               S.charAt(i) != 'i' && S.charAt(i) != 'o' &&
               S.charAt(i) != 'u')
            {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }
}
