
import java.util.*;

public class Exactchange2
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();

        while (numCases-- > 0)
        {
            int sum = sc.nextInt();
            int numCoins = sc.nextInt();
            int[] coins = new int[numCoins];
            //fill up coin array
            for (int i = 0; i < numCoins; i++) coins[i] = sc.nextInt();
            Arrays.sort(coins); //sort in increasing order of coin size

            int[] dp = new int[10001]; //keep track of #coins used for every possible sum
            Set<Integer> opt = new HashSet<>(); //make sure we don't have duplicate coins as we build up the price to pay
            for (int i = numCoins - 1; i >= 0; i--)
            {
                int coin = coins[i];
                dp[coin] = 1; // base case (takes 1 coin to pay its value)
                Set<Integer> temp = new HashSet<>();
                temp.add(coin);
                //try one coin at a time without reusing it
                for (int c : opt) {
                    int total = c + coin; //apply a coin
                    if (total <= 10000)
                    {
                        if (dp[total] == 0) //if we have not yet calculated this sum just add the value
                        {
                            dp[total] = dp[c] + 1;
                            temp.add(total);
                        }
                        else if (dp[total] > (dp[c] + 1)) //if we already calculated this total previously
                            dp[total] = dp[c] + 1; //update count to smaller #coins it takes to get this total

                    }
                }

                for (int c : temp)  opt.add(c); //add the coin used to list
            }

            //Print result for our sum
            for (int i = sum; i < 10001; i++)
            {
                if (dp[i] != 0)
                { //prin out the first possible combination of coins that can be paid
                    System.out.println(i + " " + dp[i]);
                    break;
                }
            }
        }
    }
}
