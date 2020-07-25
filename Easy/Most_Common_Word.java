import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Most_Common_Word {
/*
Rating: Easy

Given a paragraph and a list of banned words, return
the most frequent word that is not in the list of banned words.
It is guaranteed there is at least one word that isn't banned,
and that the answer is unique.

Words in the list of banned words are given in lowercase, and free
of punctuation.  Words in the paragraph are not case sensitive.
The answer is in lowercase.



Example:

Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word
in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.

Note:

    1 <= paragraph.length <= 1000.
    0 <= banned.length <= 100.
    1 <= banned[i].length <= 10.
    The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols,
    and even if it is a proper noun.)
    paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
    There are no hyphens or hyphenated words.
    Words only consist of letters, never apostrophes or other punctuation symbols.
*/

/*
    Approach: Replace all punctuation symbols with spaces and split the paragraph on the space. Note that
              after replacement, there may be multiple spaces consecutively which will split into the empty string.

              Iterate through the split sentence and lowercase the word. If the word is not in the banned
              set, store a HashMap of the counts.
*/



    // O(n) runtime, n = length of the string
    // O(k) space where k = # of words
    // Runtime: 12 ms, faster than 63.07% of Java online submissions
    // Memory Usage: 40.0 MB, less than 17.48 of Java online submissions
    public String mostCommonWord(String paragraph, String[] banned) {
        HashSet<String> banned_set = new HashSet<String>(Arrays.asList(banned));
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        paragraph = paragraph.replaceAll("!", " ");
        paragraph = paragraph.replaceAll("\\?", " ");
        paragraph = paragraph.replaceAll("'", " ");
        paragraph = paragraph.replaceAll(",", " ");
        paragraph = paragraph.replaceAll(";", " ");
        paragraph = paragraph.replaceAll("\\.", " ");
        String[] words = paragraph.split(" ");
        for (int i = 0; i < words.length; ++i)
        {
            String w = words[i];
            if (w.equals(""))
            {
                continue;
            }
            w = w.toLowerCase();
            if (!banned_set.contains(w))
            {
                Integer count = counts.get(w);
                if (count == null)
                {
                    count = 0;
                }
                counts.put(w, count + 1);
            }
        }
        String most_freq = null;
        int max = Integer.MIN_VALUE;
        for (String word: counts.keySet())
        {
            if (counts.get(word) > max)
            {
                max = counts.get(word);
                most_freq = word;
            }
        }
        return most_freq;
    }
}