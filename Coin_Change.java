public class Coin_Change {

/*
Rating: Medium

You are given coins of different denominations and
a total amount of money amount. Write a function
to compute the fewest number of coins that you need to
make up that amount. If that amount of money cannot be
made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1

Example 2:

Input: coins = [2], amount = 3
Output: -1

Note:
You may assume that you have an infinite number of each kind of coin.


*/
    public int coinChange(int[] coins, int amount) {
        // n = amount
        // c = # of different coin values
        // O(n) space, O(cn) runtime
        if (amount == 0)
        {
            return 0;
        }
        int[] least_amount = new int[amount + 1];
        for (int i = 0; i <= amount; ++i)
        {   // build up number aways for each amount up from 0
            // starts at 1 way for each value that is exactly a coin and builds
            // up from there
            for (int c = 0; c < coins.length; ++c)
            {
                if (coins[c] == i)
                {
                    least_amount[i] = 1;
                    break;
                }
                // check for current value - each coin value and add 1 to number
                // of coins if the smaller value is greater than 0(smaller value was
                // possible combination of coins) and current number of coins is greater
                int diff = i - coins[c];
                if (diff >= 0)
                {
                    if (least_amount[diff] > 0)
                    {
                        if(least_amount[diff] + 1 < least_amount[i] || least_amount[i] == 0)
                        {
                            least_amount[i] = least_amount[diff] + 1;
                        }
                    }
                }
            }
            
        }
        
        if (least_amount[amount] > 0)
        {
            return least_amount[amount];
        }
        else
        {
            return -1;
        }
}
