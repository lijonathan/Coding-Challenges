import java.util.HashMap;

public class Integer_to_English_Words {
/*
Rating: Hard

Convert a non-negative integer to its english words representation.
Given input is guaranteed to be less than 2^31 - 1.

Example 1:

Input: 123
Output: "One Hundred Twenty Three"

Example 2:

Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"

Example 3:

Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

Example 4:

Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
*/

/*
    Approach: Put possible string values for numbers into a dictionary. Start modding off
              current magnitude after appending the appropriate string and continue until the string is 0.
*/


    // O(1) time
    // O(1) space
    // Runtime: 3 ms, faster than 90.38% of Java online submissions
    // Memory Usage: 39.7 MB, less than 43.55% of Java online submissions
    private void create_dict(HashMap<Integer, String> dict)
    {
        dict.put(1, "One ");
        dict.put(2, "Two ");
        dict.put(3, "Three ");
        dict.put(4, "Four ");
        dict.put(5, "Five ");
        dict.put(6, "Six ");
        dict.put(7, "Seven ");
        dict.put(8, "Eight ");
        dict.put(9, "Nine ");

        dict.put(10, "Ten ");
        dict.put(11, "Eleven ");
        dict.put(12, "Twelve ");
        dict.put(13, "Thirteen ");
        dict.put(14, "Fourteen ");
        dict.put(15, "Fifteen ");
        dict.put(16, "Sixteen ");
        dict.put(17, "Seventeen ");
        dict.put(18, "Eighteen ");
        dict.put(19, "Nineteen ");

        dict.put(20, "Twenty ");
        dict.put(30, "Thirty ");
        dict.put(40, "Forty ");
        dict.put(50, "Fifty ");
        dict.put(60, "Sixty ");
        dict.put(70, "Seventy ");
        dict.put(80, "Eighty ");
        dict.put(90, "Ninety ");
        dict.put(100, "Hundred ");
        dict.put(1000, "Thousand ");
        dict.put(1000000, "Million ");
        dict.put(1000000000, "Billion ");
    }
    public String numberToWords(int num) {
        if (num == 0)
        {
            return "Zero";
        }
        StringBuffer sb = new StringBuffer();
        HashMap<Integer, String> dict = new HashMap<Integer, String>();
        create_dict(dict);
        if (num >= 1000000000) // billions
        {
            int mult = num / 1000000000;
            sb.append(dict.get(mult));
            sb.append(dict.get(1000000000));
            num = num % 1000000000;
        }
        if (num >= 1000000) // millions
        {
            if (num >= 100000000)
            {
                int mult = num / 100000000;
                sb.append(dict.get(mult));
                sb.append(dict.get(100));
                num = num % 100000000;
            }
            if (num >= 20000000)
            {
                int mult = num / 10000000 * 10;
                sb.append(dict.get(mult));
                num = num % 10000000;
            }
            else if (num >= 10000000)
            {
                int mult = num / 1000000;
                sb.append(dict.get(mult));
                num = num % 1000000;
            }
            if (num >= 1000000)
            {
                int mult = num / 1000000;
                sb.append(dict.get(mult));
                num = num % 1000000;
            }
            sb.append(dict.get(1000000));
        }

        if (num >= 1000) // thousands
        {
            if (num >= 100000)
            {
                int mult = num / 100000;
                sb.append(dict.get(mult));
                sb.append(dict.get(100));
                num = num % 100000;
            }
            if (num >= 20000)
            {
                int mult = num / 10000 * 10;
                sb.append(dict.get(mult));
                num = num % 10000;
            }
            else if (num >= 10000)
            {
                int mult = num / 1000;
                sb.append(dict.get(mult));
                num = num % 1000;
            }
            if (num >= 1000)
            {
                int mult = num / 1000;
                sb.append(dict.get(mult));
                num = num % 1000;
            }
            sb.append(dict.get(1000));
        }
        if (num >= 100) // hundreds
        {
            int mult = num / 100;
            sb.append(dict.get(mult));
            sb.append(dict.get(100));
            num = num % 100;
        }
        if (num >= 20)
        {
            int mult = num / 10 * 10;
            sb.append(dict.get(mult));
            num = num % 10;
        }
        else if (num >= 10)
        {
            sb.append(dict.get(num));
            return sb.toString().trim();
        }
        if (num > 0)
        {
            sb.append(dict.get(num));
        }
        return sb.toString().trim();
    }
}
