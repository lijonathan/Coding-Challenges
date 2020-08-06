import java.util.ArrayList;
import java.util.List;

public class Text_Justification {

/*
Rating: Hard

Given an array of words and a width maxWidth, format the text such that each line has
exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you
can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces
on a line do not divide evenly between words, the empty slots on the left will be assigned more
spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

    A word is defined as a character sequence consisting of non-space characters only.
    Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
    The input array words contains at least one word.

Example 1:

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

Example 2:

Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.

Example 3:

Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
*/

/*
    Approach: Iterate through the words array. Count the number of strings that can be
              put into a single line, factoring in the at minimum 1 space in between each character.
              (Also factoring in the last word does not need a space after it.)

              Special case: If only 1 word can go on the line, pad up until maxWidth after the word
                            with spaces and continue to the next iteration.

              Otherwise - # of separations = # words - 1. The number of spaces between each word is
                          maxWitdh - #characters of words in line / # of seperations. Leftover = 
                          #characters of words in line % # of separations. For each separation at the
                          start, add 1 space on top of number of spaces if there is still leftover. After adding
                          the extra space, decrement leftover count.

              Remove the last line and separate each word with 1 space and pad the end with extra spaces until
              the line equals maxWidth.
*/


    // O(n) time where n = # of characters in the words array
    // O(n) space for the stringbuffer
    // Runtime: 1 ms, faster than 64.40% of Java online submissions
    // Memory Usage: 38.2 MB, less than 13.21% of Java online submissions
    public int count_characters(String[] words, int maxWidth, int i)
    {
        int char_count = 0;
        while(i < words.length)
        {
            char_count += words[i].length() + 1;
            if (char_count - 1 == maxWidth) // last word does not need space after so -1
            {
                ++i;
                break;
            }
            else if (char_count - 1 > maxWidth) // last word does not need space after so -1
            {
                char_count -= words[i].length();
                char_count -= 1;
                break;
            }
            ++i;
        }
        return i;
    }
    public List<String> fullJustify(String[] words, int maxWidth) {
        ArrayList<String> lines = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < words.length; ++i)
        {
            int next = count_characters(words, maxWidth, i);
            int w_c = next - i;
            int letters = 0;
            for (int k = i; k < next; ++k)
            {
                letters += words[k].length();
            }
            int spaces = maxWidth - letters;

            if (w_c == 1)
            {
                sb.append(words[i]);
                for (int s = 0; s < spaces; ++s)
                {
                    sb.append(" ");
                }
                lines.add(sb.toString());
                sb.delete(0, sb.length());
                continue;
            }
            int separations = w_c - 1;
            int spaces_per = spaces / separations; // divide by zero handled in w_c == 1
            int extra = spaces % separations;
            for (int make = i; make < next; ++make)
            {
                sb.append(words[make]);
                if (make != next - 1)
                {
                    for (int s = 0; s < spaces_per; ++s)
                    {
                        sb.append(" ");
                    }
                    if (extra > 0)
                    {
                        sb.append(" ");
                        --extra;
                    }
                }
            }
            lines.add(sb.toString());
            sb.delete(0, sb.length());
            i = next - 1;
        }


        String last_line = lines.get(lines.size() - 1);
        lines.remove(lines.size() - 1);
        int length = last_line.length();
        for (int i = 0; i < length; ++i)
        {
            Character c = last_line.charAt(i);
            if (c != ' ')
            {
                sb.append(c);
            }
            else if (i != 0 && last_line.charAt(i - 1) != ' ')
            {
                sb.append(" ");
            }
        }
        int spaces = maxWidth - sb.length();
        for (int s = 0; s < spaces; ++s)
        {
            sb.append(" ");
        }
        lines.add(sb.toString());
        return lines;
    }
}
