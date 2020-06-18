public class Add_Strings {

/*
Rating: Easy

Given two non-negative integers num1 and num2
represented as string, return the sum of num1 and num2.

Note:

    The length of both num1 and num2 is < 5100.
    Both num1 and num2 contains only digits 0-9.
    Both num1 and num2 does not contain any leading zero.
    You must not use any built-in BigInteger library or
    convert the inputs to integer directly.

*/
    public String addStrings(String num1, String num2) {
        //O(n) time, O(n) space where n = length of longest string
        StringBuffer sb = new StringBuffer();
        int l1 = num1.length();
        int l2 = num2.length();
        int length = Math.max(l1, l2);
        int i = 0;
        boolean carry = false;
        while (i < length)
        {
            int num1_val = 0;
            int num2_val = 0;
            if (l1 - i - 1 >= 0)
            {
                num1_val = Integer.parseInt(Character.toString(num1.charAt(l1 - i - 1)));
            }
            if (l2 - i - 1 >= 0)
            {
                num2_val = Integer.parseInt(Character.toString(num2.charAt(l2 - i - 1)));
            }
            int sum = 0;
            if (carry)
            {
                sum = 1;
            }
            sum = sum + num1_val + num2_val;
            if (sum > 9)
            {
                sum -= 10;
                carry = true;
            }
            else
            {
                carry = false;
            }
            sb.append(Integer.toString(sum));
            ++i;
        }
        if (carry)
        {
            sb.append("1");
        }
        return sb.reverse().toString();
    }
}
