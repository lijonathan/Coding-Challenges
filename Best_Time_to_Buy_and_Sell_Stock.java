public class Best_Time_to_Buy_and_Sell_Stock {

/*
Rating: Easy

Say you have an array for which the ith
element is the price of a given stock on day i.

If you were only permitted to complete at
most one transaction (i.e., buy one and sell
one share of the stock), design an algorithm
to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.

Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

*/

    public int maxProfit(int[] prices) {
        //O(n) time, O(1) space
        
        // one pass 
        // keep track of min_value currently seen and subtract every current 
        // index with min value for max diff
        int max_diff = 0;
        int min_value = Integer.MAX_VALUE;
        for(int i = 0; i < prices.length; ++i)
        {
            if(prices[i] < min_value)
            {
                min_value = prices[i];
            }
            if (prices[i] - min_value > max_diff)
            {
                max_diff = prices[i] - min_value;
            }
        }
        return max_diff;
        
    }
}
