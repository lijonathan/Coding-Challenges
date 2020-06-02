import java.awt.List;
import java.util.ArrayList;

class Letter_Combinations_of_a_Phone_Number {
/*

Rating: Medium

Given a string containing digits from 2-9 inclusive,
return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone
buttons) is given below. Note that 1 does not map to any letters.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note:

Although the above answer is in lexicographical order, your answer
could be in any order you want.
*/

    private void generate(ArrayList<String> combos, String digits, String curr, int index)
    {
        if (index < digits.length())
        {
            Character c = digits.charAt(index);
            if (c == '2')
            {
                if (index + 1 == digits.length())
                {
                    combos.add(curr + 'a');
                    combos.add(curr + 'b');
                    combos.add(curr + 'c');
                }
                else
                {
                    generate(combos, digits, curr + 'a', index + 1);
                    generate(combos, digits, curr + 'b', index + 1);
                    generate(combos, digits, curr + 'c', index + 1);
                }
            }
            else if (c == '3')
            {
                if (index + 1 == digits.length())
                {
                    combos.add(curr + 'd');
                    combos.add(curr + 'e');
                    combos.add(curr + 'f');
                }
                else
                {
                    generate(combos, digits, curr + 'd', index + 1);
                    generate(combos, digits, curr + 'e', index + 1);
                    generate(combos, digits, curr + 'f', index + 1);
                }
            }
            else if (c == '4')
            {
                if (index + 1 == digits.length())
                {
                    combos.add(curr + 'g');
                    combos.add(curr + 'h');
                    combos.add(curr + 'i');
                }
                else
                {
                    generate(combos, digits, curr + 'g', index + 1);
                    generate(combos, digits, curr + 'h', index + 1);
                    generate(combos, digits, curr + 'i', index + 1);
                }
            }
            else if (c == '5')
            {
                if (index + 1 == digits.length())
                {
                    combos.add(curr + 'j');
                    combos.add(curr + 'k');
                    combos.add(curr + 'l');
                }
                else
                {
                    generate(combos, digits, curr + 'j', index + 1);
                    generate(combos, digits, curr + 'k', index + 1);
                    generate(combos, digits, curr + 'l', index + 1);
                }
            }
            else if (c == '6')
            {
                if (index + 1 == digits.length())
                {
                    combos.add(curr + 'm');
                    combos.add(curr + 'n');
                    combos.add(curr + 'o');
                }
                else
                {
                    generate(combos, digits, curr + 'm', index + 1);
                    generate(combos, digits, curr + 'n', index + 1);
                    generate(combos, digits, curr + 'o', index + 1);
                }
            }
            else if (c == '7')
            {
                if (index + 1 == digits.length())
                {
                    combos.add(curr + 'p');
                    combos.add(curr + 'q');
                    combos.add(curr + 'r');
                    combos.add(curr + 's');
                }
                else
                {
                    generate(combos, digits, curr + 'p', index + 1);
                    generate(combos, digits, curr + 'q', index + 1);
                    generate(combos, digits, curr + 'r', index + 1);
                    generate(combos, digits, curr + 's', index + 1);
                }
            }
            else if (c == '8')
            {
                if (index + 1 == digits.length())
                {
                    combos.add(curr + 't');
                    combos.add(curr + 'u');
                    combos.add(curr + 'v');
                }
                else
                {
                    generate(combos, digits, curr + 't', index + 1);
                    generate(combos, digits, curr + 'u', index + 1);
                    generate(combos, digits, curr + 'v', index + 1);
                }
            }
            else if (c == '9')
            {
                if (index + 1 == digits.length())
                {
                    combos.add(curr + 'w');
                    combos.add(curr + 'x');
                    combos.add(curr + 'y');
                    combos.add(curr + 'z');
                }
                else
                {
                    generate(combos, digits, curr + 'w', index + 1);
                    generate(combos, digits, curr + 'x', index + 1);
                    generate(combos, digits, curr + 'y', index + 1);
                    generate(combos, digits, curr + 'z', index + 1);
                }
            }
        }
        else
        {
            combos.add(curr);
        }
            
    }
    public List<String> letterCombinations(String digits) {
        // O(3^N×4^M) time where N is the number of numbers that map to 3 letters
        // M is the number of digits that map to 4 letters
        // O(3^N×4^M) space for the number of recursive calls and the size of the list
        // that stores ths solution
        ArrayList<String> combos = new ArrayList<String>();
        if (digits.length() == 0)
        {
            return combos;
        }
        generate(combos, digits, "", 0);
        return combos;
    }
}
